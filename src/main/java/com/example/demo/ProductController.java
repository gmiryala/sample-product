package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/")
	public String hello() {
		return "Hello World!";
	}
	
	@PostMapping("/product")
	public String postProduct(@RequestBody ProductDTO productDTO) {
		return productService.postProduct(productDTO);
	}
	
	@GetMapping("/product/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
}
