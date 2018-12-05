package com.score.es.first.escorefirst.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product_index", type = "product", shards = 1)
public class Product implements Serializable {
    private static final long serialVersionUID = 3221700752972709820L;
    @Id
    private String id;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
    private String name;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
    private String desc;
    private Double price;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
    private String producer;
    private String img;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word", type = FieldType.text)
    private List<String> tags;

}
