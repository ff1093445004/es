package com.score.es.first.escorefirst.scheduler;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.score.es.first.escorefirst.model.Product;
import com.score.es.first.escorefirst.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductScheduler extends BaseScheduler<Product> {

    @Autowired
    private ProductRepository productRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(ProductScheduler.class);

//    @Scheduled(cron = "0/30 * * * * *")
    public void sycProduct() {
        LOGGER.info("==============job start!!!!!!!!!!!==============");
        Set<String> s = super.getKeyList();
        List<Product> list = Lists.newArrayList();
        Gson gson = new Gson();
        for (String key : s) {
            Product product = gson.fromJson(super.getCode(key), Product.class);
            LOGGER.info("==============" + product.getId() + "==============");
            list.add(product);
        }
        productRepository.saveAll(list);
        LOGGER.info("==============job end!!!!!!!!!!!==============");
    }
}
