package com.score.es.first.escorefirst.controller;

import com.score.es.first.escorefirst.model.BaseResult;
import com.score.es.first.escorefirst.model.Product;
import com.score.es.first.escorefirst.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductSearchController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product/save", method = RequestMethod.POST)
	public void save(@RequestBody Product product) {
		productService.saveProduct(product);
	}

	@RequestMapping(value = "/product/saveBach", method = RequestMethod.POST)
	public void saveProductBath(@RequestBody List<Product> products) {
		productService.saveProductBath(products);
	}

	@RequestMapping(value = "/product/id", method = RequestMethod.GET)
	public Product findById(@RequestParam("id") String id) {
		return productService.findById(id);
	}

	@RequestMapping(value = "/product/name", method = RequestMethod.GET)
	public List<Product> findByName(@RequestParam("name") String name) {
		return productService.findByName(name);
	}

	@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	public void deleteProduct(@RequestBody Product product) {
		productService.deleteProduct(product);
	}

	@RequestMapping(value = "/product/findByNameHighLight", method = RequestMethod.GET)
	public BaseResult findByNameHighLight(@RequestParam("page") int page, @RequestParam("size") int size,
			@RequestParam("name") String name) {
		return productService.findByNameHighLight(page, size, name);
	}

}
