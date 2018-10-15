package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ProductSearchDTO;
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


	public Long postProduct(ProductDTO productDTO) {
		Product product = modelMapper.map(productDTO, Product.class);
		product = productRepository.save(product);
		return product.getId();
	}


	public ProductDTO getProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);
			return productDTO;
		}else {
			throw new RuntimeException("Product not availbale for id: "+id);
		}
	}


	public List<ProductDTO> search(ProductSearchDTO productSearchDTO) {
		List<Product> products = productRepository.findAllByNameAndPriceGreaterThanAndPriceLessThan(productSearchDTO.getName(), 
				productSearchDTO.getStartPerice(), productSearchDTO.getEndPrice());
		List<ProductDTO> productDTOs = modelMapper.map(products,  new TypeToken<List<ProductDTO>>() {}.getType());
		return productDTOs;
	}


	public String deleteProduct(Long id) {
		if(getProduct(id) != null) {
			productRepository.deleteById(id);
			return "success";
		}else {
			return "false";
		}
	}
	


}
