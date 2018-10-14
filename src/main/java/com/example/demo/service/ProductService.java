package com.example.demo.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	
	public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;

	}


	public String postProduct(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		product = productRepository.save(product);
		return product.getId().toString();
	}


	public ProductDTO getProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			return modelMapper.map(product.get(), ProductDTO.class);
		}else {
			throw new RuntimeException("Product not availbale for id: "+id);
		}
	}

}
