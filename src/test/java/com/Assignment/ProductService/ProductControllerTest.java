package com.Assignment.ProductService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import com.Assignment.ProductService.controller.ProductController;
import com.Assignment.ProductService.entity.Product;
import com.Assignment.ProductService.repository.ProductRepository;
import com.Assignment.ProductService.service.ProductService;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

	
	@MockBean
	ProductService productService;
	
	@MockBean
	ProductRepository productRepository;
	
	
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void fetchProducts() throws Exception {
		List<Product> prodList=new ArrayList<>();
		   Product prod = new Product(0, null, null, 0, null, null, false);
		   prodList.add(prod);
			 when(productService.fetchAllProducts()).thenReturn(prodList);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/fetchProducts").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
		
		
	}
	
	@Test
	void fetchById() throws Exception {
		
		  Product prod = new Product(0, null, null, 0, null, null, false);
		  when(productService.fetchbyId(anyInt())).thenReturn(prod);

		mockMvc.perform(MockMvcRequestBuilders.get("/products/fetchProductById/5").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void setAvailability() throws Exception {
		
		 doNothing().when(productRepository).setAvailabitity(anyInt());

		mockMvc.perform(MockMvcRequestBuilders.put("/products/setAvailability/5").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void setQuantity() throws Exception {
		
		 doNothing().when(productRepository).setQuantity(anyInt(),anyInt());

		mockMvc.perform(MockMvcRequestBuilders.put("/products/setQuantity/5/5").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	void filterProductsprice() throws Exception {
		List<Product> prodList=new ArrayList<>();
		   Product prod = new Product(0, null, null, 0, null, null, false);
		   prodList.add(prod);
		  when(productService.filterProductsByPrice(anyString(),anyString())).thenReturn(prodList);

		mockMvc.perform(MockMvcRequestBuilders.put("/products/fetchProducts/price").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());	

	}
	
	@Test
	void filterProductsrating() throws Exception {
		List<Product> prodList=new ArrayList<>();
		   Product prod = new Product(0, null, null, 0, null, null, false);
		   prodList.add(prod);
		  when(productService.filterProductsByPrice(anyString(),anyString())).thenReturn(prodList);

		mockMvc.perform(MockMvcRequestBuilders.put("/products/fetchProducts/rating").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());	

	}
	
	@Test
	void filterProductscategory() throws Exception {
		List<Product> prodList=new ArrayList<>();
		   Product prod = new Product(0, null, null, 0, null, null, false);
		   prodList.add(prod);
		  when(productService.filterProductsByPrice(anyString(),anyString())).thenReturn(prodList);

		mockMvc.perform(MockMvcRequestBuilders.put("/products/fetchProducts/category").with(csrf()))
				.andExpect(MockMvcResultMatchers.status().isOk());	

	}
	

	
}

