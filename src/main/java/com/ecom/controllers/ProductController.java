package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.config.AppConstants;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.services.ProductService;

@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService productService;

//	to create new product
	@PostMapping(value = "categories/{categoryId}/products/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto, @PathVariable Integer categoryId) {
		ProductDto saveProduct = this.productService.createProduct(productDto,categoryId);
		return new ResponseEntity<ProductDto>(saveProduct,HttpStatus.CREATED);

	}
//  to get all products
	@GetMapping(value = "/products", produces = "application/json")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = this.productService.getListOfProducts();
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);

	}
//  to get all products by page
	@GetMapping(value = "/products/page", produces = "application/json")
	public ResponseEntity<ProductResponse> getAllProductsByPage(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
			) {
		ProductResponse products = this.productService.getListOfProductsByPage(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<ProductResponse>(products,HttpStatus.OK);

	}

//	to get all products by category
	@GetMapping(value = "/categories/{categoryId}/products/", produces = "application/json")
	public ResponseEntity<List<ProductDto>> getProductsByCategory( @PathVariable Integer categoryId) {
		List<ProductDto> products = this.productService.getProductsByCategory(categoryId);
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);

	}
//	to get all products by category
	@GetMapping(value = "/categories/{categoryId}/products/page", produces = "application/json")
	public ResponseEntity<ProductResponse> getProductsByCategoryByPage(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
			) {
		ProductResponse products = this.productService.getProductsByCategoryByPage(categoryId, pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<ProductResponse>(products,HttpStatus.OK);

	}
//	to get single product by product di
	@GetMapping(value = "/products/{productId}", produces = "application/json")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {
		ProductDto product = this.productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);

	}
//	to update product
	@PutMapping(value = "/products/{productId}",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId,@RequestBody ProductDto productDto) {
		ProductDto updateProduct = this.productService.updateProduct(productId, productDto);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);

	}
//	to  delete product
	@DeleteMapping(value = "/products/{productId}", produces = "application/json")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) {
			this.productService.deleteProduct(productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("product deleted successfully ", true),HttpStatus.OK);

	}	
}
