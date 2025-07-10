package com.placeOrder.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.placeOrder.entity.ProductDetails;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails, Long>{

}
