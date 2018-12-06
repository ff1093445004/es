package com.score.es.first.escorefirst.model;

import lombok.Data;

@Data
public class BaseRequest {
	private String queryParam;
	private int size;
	private int page;
}
