package com.score.es.first.escorefirst.service;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.score.es.first.escorefirst.model.BaseResult;
import com.score.es.first.escorefirst.model.Product;
import com.score.es.first.escorefirst.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private TransportClient transportClient;

	private static final Gson GSON = new Gson();

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void saveProductBath(List<Product> products) {
		productRepository.saveAll(products);
	}

	public Product findById(String id) {
		return productRepository.findById(id).orElse(new Product());
	}

	public List<Product> findByName(String name) {
		if (StringUtils.isEmpty(name)) {
			return Lists.newArrayList(productRepository.findAll().iterator());
		}
		return productRepository.findByName(name);
	}

	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	public BaseResult findByNameHighLight(int page, int size, String name) {
		BaseResult bs = new BaseResult();
		QueryBuilder matchQuery;
		if (StringUtils.isEmpty(name)) {
			matchQuery = QueryBuilders.matchAllQuery();
		} else {
			// 构造查询条件,使用IK分词器
			matchQuery = QueryBuilders.matchQuery("name", name).analyzer("ik_max_word").operator(Operator.OR);
		}
		// 设置高亮,使用默认的highlighter高亮器
		HighlightBuilder highlightBuilder = new HighlightBuilder().field("name")
				.preTags("<span style=\"color:red;font-weight:bold;\">").postTags("</span>");
		// 设置查询字段 设置一次返回的文档数量
		SearchResponse response = transportClient.prepareSearch("product_index").setTypes("product")
				.setQuery(matchQuery).highlighter(highlightBuilder).setFrom((page - 1) * size).setSize(size).get();
		// 获取命中次数，查询结果有多少对象
		SearchHits hits = response.getHits();
		List<Product> list = Lists.newArrayList();
		bs.setTotal(hits.getTotalHits());
		bs.setList(list);
		for (SearchHit searchHit : hits) {
			// 将查询结果转换为对象
			Product product = GSON.fromJson(searchHit.getSourceAsString(), Product.class);
			list.add(product);
			if (!StringUtils.isEmpty(name)) {
				// 将高亮处理后的内容，替换原有内容（原有内容，可能会出现显示不全）
				Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
				// 获取到原有内容中 每个高亮显示 集中位置fragment就是高亮片段
				HighlightField titleField = highlightFields.get("name");
				Text[] fragments = titleField.fragments();
				StringBuilder names = new StringBuilder();
				for (Text text : fragments) {
					names.append(text);
				}
				// 用高亮后的内容，替换原有内容
				product.setName(names.toString());
			}
		}
		return bs;
	}
}
