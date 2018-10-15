package com.example.demo;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductSearchDTO;
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
	public Long postProduct(@RequestBody ProductDTO productDTO, BindingResult result) {
		if(result.hasErrors()) {
			throw new RuntimeException("validation errors");
		}
		return productService.postProduct(productDTO);
	}
	
	@GetMapping("/product/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
	@PostMapping("/search")
	public List<ProductDTO> searchProducts(@RequestBody ProductSearchDTO productSearchDTO) {
		return productService.search(productSearchDTO);
	}
	
	@DeleteMapping("/product/{id}")
	public String deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}
}
