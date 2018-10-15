package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	List<Product> findAllByNameAndPriceGreaterThanAndPriceLessThan(String name, Integer startprice, Integer endprice);
}
