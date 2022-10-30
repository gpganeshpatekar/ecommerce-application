package com.ecom.services;

import java.util.List;

import com.ecom.payload.ProductDto;

public interface ProductService {
	
	ProductDto createProduct(ProductDto productDto);
	
	List<ProductDto>  getListOfProducts();
	
	ProductDto getProductById(Integer productId);
	
	ProductDto updateProduct(Integer productId, ProductDto productDto);
	
	void deleteProduct(Integer productId);
	
	
	
	

}
