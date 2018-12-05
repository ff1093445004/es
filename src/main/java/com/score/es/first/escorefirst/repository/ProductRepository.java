package com.score.es.first.escorefirst.repository;

import com.score.es.first.escorefirst.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends ElasticsearchRepository<Product, String> {

    List<Product> findByName(String params);
}
