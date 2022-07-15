package com.Assignment.ProductService.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Assignment.ProductService.entity.Product;



@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer>{

	
//	@Query(value= "select prd_id,prd_name,category,quantity,price,rating,null from product")
//	List<Product> findAllForCustomer();
	
	Product findByprdId(int prd_id);

	@Modifying
	@Query(value="UPDATE Product SET is_available = FALSE where prd_id = :prd_id",nativeQuery=true)
	void setAvailabitity(@Param("prd_id") int prdId);

	@Modifying
	@Query(value="UPDATE Product SET quantity = :quantity where prd_id = :prd_id",nativeQuery=true)
	void setQuantity(@Param("prd_id") int prdId,@Param("quantity")  int quantity);

}
