package com.Assignment.ProductService.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Assignment.ProductService.entity.Product;
import com.Assignment.ProductService.exception.UnableToProcessException;
import com.Assignment.ProductService.repository.ProductRepository;



@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public List<Product> fetchAllProducts() {
		List<Product> allProducts = productRepository.findAll();
		return allProducts;

	}
	
	
	public Product fetchbyId(int prdId) {
		return productRepository.findByprdId(prdId);
		
	}
	
	public List<Product> filterProductsByPrice(String filter, String order) {

		List<Product> allProducts = productRepository.findAll();
		List<Product> filteredList = new ArrayList<>();
		logger.info("Filtering productus based on price..");
		if (order.equalsIgnoreCase("asc")) {
			Comparator<Product> compareByPrice = (Product o1, Product o2) -> o1.getPrice().compareTo(o2.getPrice());
			filteredList = allProducts.stream().sorted(compareByPrice).collect(Collectors.toList());
			
		} else if (order.equalsIgnoreCase("desc")) {
			Comparator<Product> compareByPrice = (Product o1, Product o2) -> o2.getPrice().compareTo(o1.getPrice());
			filteredList = allProducts.stream().sorted(compareByPrice).collect(Collectors.toList());
			

		}
		return filteredList;
	}

	public List<Product> filterProductsByRating(String filter, String order) {

		List<Product> allProducts = productRepository.findAll();
		List<Product> filteredList = new ArrayList<>();
		logger.info("Filtering productus based on rating..");
		if (order.equalsIgnoreCase("asc")) {
			Comparator<Product> compareByPrice = (Product o1, Product o2) -> o1.getRating().compareTo(o2.getRating());
			filteredList = allProducts.stream().sorted(compareByPrice).collect(Collectors.toList());
		} else if (order.equalsIgnoreCase("desc")) {
			Comparator<Product> compareByPrice = (Product o1, Product o2) -> o2.getRating().compareTo(o1.getRating());
			filteredList = allProducts.stream().sorted(compareByPrice).collect(Collectors.toList());

		}
		return filteredList;
	}
	
	
	
	public List<Product> filterProductsByCategory(String filter) {

		List<Product> allProducts = productRepository.findAll();
		List<Product> filteredList = new ArrayList<>();
		logger.info("Filtering productus based on category..");
		filteredList=allProducts.stream().filter(s->s.getCategory().equalsIgnoreCase(filter)).collect(Collectors.toList());
		if(!filteredList.isEmpty()) {
			return filteredList;
		}
		else {
			logger.error("No products available under this category");
            throw new UnableToProcessException("No products available under this category");
        }
		
	}



}
