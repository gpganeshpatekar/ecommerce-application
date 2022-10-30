package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Product;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.ProductDto;
import com.ecom.repositories.ProductRepository;
import com.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto) {
		Product product = this.modelMapper.map(productDto, Product.class);
		Product savedProduct = this.productRepository.save(product);
		return this.modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getListOfProducts() {
		List<Product> products = this.productRepository.findAll();
		List<ProductDto> productDtos = products.stream()
				.map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id : " + productId));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id : " + productId));
		product.setProductName(productDto.getProductName());
		product.setProductDesc(productDto.getProductDesc());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductQty(productDto.getProductQty());
		product.setLiveStatus(productDto.isLiveStatus());
		product.setStockStatus(productDto.isStockStatus());
		product.setProductImage(productDto.getProductImage());
		Product updatedProduct = this.productRepository.save(product);
		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(Integer productId) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with id : " + productId));
		this.productRepository.delete(product);

	}

}
