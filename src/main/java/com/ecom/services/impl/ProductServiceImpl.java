package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.exceptions.ResourceNotFoundException;
import com.ecom.payload.CategoryDto;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.repositories.CategoryRepository;
import com.ecom.repositories.ProductRepository;
import com.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("category not found with category id : " + categoryId));
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setCategory(category);
		Product savedProduct = this.productRepository.save(product);
		return this.modelMapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public List<ProductDto> getListOfProducts() {
		List<Product> products = this.productRepository.findAll();
		List<ProductDto> productDtos = products.stream().map(product -> this.modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto getProductById(Integer productId) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with product id : " + productId));
		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(Integer productId, ProductDto productDto) {
		Product product = this.productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("product not found with product id : " + productId));
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
				.orElseThrow(() -> new ResourceNotFoundException("product not found with product id : " + productId));
		this.productRepository.delete(product);

	}

	@Override
	public List<ProductDto> getProductsByCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("category not found with category id : " + categoryId));
		List<Product> categories = this.productRepository.findByCategory(category);
		List<ProductDto> dtos = categories.stream().map((product)-> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public ProductResponse getListOfProductsByPage(int pageNumber, int pageSize,String sortBy,String sortDir) {
		// for sorting
		Sort sort = null;
		if(sortDir.trim().toLowerCase().equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		Page<Product> page = this.productRepository.findAll(pageable);
		List<Product> all = page.getContent();
		List<ProductDto> productDtos = all.stream().map(product -> this.modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(productDtos);
		productResponse.setPageNumber(page.getNumber());
		productResponse.setPageSize(page.getSize());
		productResponse.setTotalElements(page.getTotalElements());
		productResponse.setTotalPages(page.getTotalPages());
		productResponse.setLastPage(page.isLast());
		return productResponse;
	}

	@Override
	public ProductResponse getProductsByCategoryByPage(Integer categoryId, int pageNumber, int pageSize,String sortBy,String sortDir) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(
				() -> new ResourceNotFoundException("category not found with category id : " + categoryId));
		Sort sort = null;
		if(sortDir.trim().toLowerCase().equals("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Product> page = this.productRepository.findByCategory(category, pageable);
		List<Product> categories = page.getContent();
		List<ProductDto> dtos = categories.stream().map((product)-> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		ProductResponse productResponse = new ProductResponse();
		productResponse.setContent(dtos);
		productResponse.setPageNumber(page.getNumber());
		productResponse.setPageSize(page.getSize());
		productResponse.setTotalElements(page.getTotalElements());
		productResponse.setTotalPages(page.getTotalPages());
		productResponse.setLastPage(page.isLast());
		return productResponse;
	}

}
