package com.Assignment.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Assignment.ProductService.entity.Product;
import com.Assignment.ProductService.repository.ProductRepository;
import com.Assignment.ProductService.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductService productService;

	Product prod = new Product(0, null, null, 0, null, null, false);

	@Test
	public void fetchProducts() throws Exception {
		List<Product> prodList = new ArrayList<>();

		prodList.add(prod);
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> prd = productService.fetchAllProducts();

		assertEquals(prodList, prd);

	}

	@Test
	void findById() throws Exception {

		when(productRepository.findByprdId(anyInt())).thenReturn(prod);
		Product p = productService.fetchbyId(anyInt());
		assertEquals(prod, p);

	}

	@Test
	void filterProductsprice() throws Exception {
		List<Product> prodList = new ArrayList<>();
		Product p1 = new Product(1, "mobile", "food", 5, 1999, 5, true);
		Product p2 = new Product(2, "modgbile", "food", 5, 5000, 5, true);
		prodList.add(p1);
		prodList.add(p2);
		
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> filter=productService.filterProductsByPrice("price", "asc");
		assertEquals(prodList, filter);
		
	}

	@Test
	void filterProductsrating() throws Exception {
		List<Product> prodList = new ArrayList<>();
		Product p1 = new Product(1, "mobile", "food", 5, 1999, 5, true);
		Product p2 = new Product(2, "modgbile", "food", 5, 5000, 10, true);
		prodList.add(p1);
		prodList.add(p2);
		
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> filter=productService.filterProductsByRating("rating", "asc");
		assertEquals(prodList, filter);
		
	}

	@Test
	void filterProductscategory() throws Exception {
		List<Product> prodList = new ArrayList<>();
		Product p1 = new Product(1, "mobile", "food", 5, 1999, 5, true);
		Product p2 = new Product(2, "modgbile", "nfood", 5, 5000, 10, true);
		prodList.add(p1);
		prodList.add(p2);
		
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> filter=productService.filterProductsByCategory("category");
		assertEquals(prodList, filter);

	}
	
	
	@Test
	void filterProductspricedesc() throws Exception {
		ArrayList<Product> prodList = new ArrayList<>();
		Product p1 = new Product(1, "mobile", "food", 5, 1999, 5, true);
		Product p2 = new Product(2, "modgbile", "nfood", 5, 5000, 10, true);
		prodList.add(p1);
		prodList.add(p2);
		
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> filter=productService.filterProductsByPrice("price", "desc");
		ArrayList<Product> reverse=(ArrayList<Product>) prodList.clone();
		Collections.reverse(reverse);
		assertEquals(reverse, filter);

	}
	
	@Test
	void filterProductsratingdesc() throws Exception {
		ArrayList<Product> prodList = new ArrayList<>();
		Product p1 = new Product(1, "mobile", "food", 5, 1999, 5, true);
		Product p2 = new Product(2, "modgbile", "nfood", 5, 5000, 10, true);
		prodList.add(p1);
		prodList.add(p2);
		
		when(productRepository.findAll()).thenReturn(prodList);
		List<Product> filter=productService.filterProductsByRating("raing", "desc");
		ArrayList<Product> reverse=(ArrayList<Product>) prodList.clone();
		Collections.reverse(reverse);
		assertEquals(reverse, filter);

	}

}
