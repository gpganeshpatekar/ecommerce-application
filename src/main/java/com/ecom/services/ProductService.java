package com.ecom.services;

import java.util.List;

import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;

public interface ProductService {
	
	ProductDto createProduct(ProductDto productDto,Integer categoryId);
	
	List<ProductDto>  getListOfProducts();
	
	ProductDto getProductById(Integer productId);
	
	ProductDto updateProduct(Integer productId, ProductDto productDto);
	
	void deleteProduct(Integer productId);
	
	List<ProductDto>  getProductsByCategory(Integer categoryId);
	
	ProductResponse  getProductsByCategoryByPage(Integer categoryId,int pageNumber,int pageSize,String sortBy,String sortDir);
	
	ProductResponse  getListOfProductsByPage(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	
	
	
	
	

}
