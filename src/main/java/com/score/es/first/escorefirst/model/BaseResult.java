package com.score.es.first.escorefirst.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {
	private long total;
	private List list;
}
