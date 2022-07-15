package com.Assignment.ProductService.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Assignment.ProductService.entity.Product;
import com.Assignment.ProductService.repository.ProductRepository;
import com.Assignment.ProductService.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	 Logger logger = LoggerFactory.getLogger(ProductController.class);


	@RequestMapping("/fetchProducts")
	public List<Product> fetchProducts() {
		return productService.fetchAllProducts();
	}
	
	
	@RequestMapping("/fetchProductById/{prdId}")
	public Product fetchById(@PathVariable int prdId) {
		logger.info("Loading product details with id "+prdId);
		return productService.fetchbyId(prdId);
	}
	
	@PutMapping("/setAvailability/{prdId}")
	public ResponseEntity<Boolean> updateAvailabitity(@PathVariable int prdId) {
		productRepository.setAvailabitity(prdId);
		return ResponseEntity.ok(true);
		
	}
	
	@PutMapping("/setQuantity/{prdId}/{quantity}")
	public ResponseEntity<Boolean> updateQuantity(@PathVariable int prdId,@PathVariable int quantity) {
		productRepository.setQuantity(prdId,quantity);
		logger.info("Updating product quantity with id "+prdId+" to "+quantity);
		return ResponseEntity.ok(true);
		
	}

	@RequestMapping({ "/fetchProducts/{filter}", "fetchProducts/{filter}/{order}" })
	public List<Product> filterProducts(@PathVariable String filter,
			@PathVariable(name = "order", required = false) String order) {

		if (order == null) {
			order = "asc";
		}
		if (filter.equalsIgnoreCase("price")) {
			List<Product> filteredProducts = productService.filterProductsByPrice(filter, order);
			return filteredProducts;
		} else if(filter.equalsIgnoreCase("rating")){
			List<Product> filteredProducts = productService.filterProductsByRating(filter, order);
			return filteredProducts;
		}
		else {
			List<Product> filteredProducts = productService.filterProductsByCategory(filter);
			return filteredProducts;
		}
		
	}

}