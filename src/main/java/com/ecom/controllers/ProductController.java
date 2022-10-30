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
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.services.ProductService;

@RestController
@RequestMapping("/ecom/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
		ProductDto saveProduct = this.productService.createProduct(productDto);
		return new ResponseEntity<ProductDto>(saveProduct,HttpStatus.CREATED);

	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = this.productService.getListOfProducts();
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);

	}
	@GetMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {
		ProductDto product = this.productService.getProductById(productId);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);

	}

	@PutMapping(value = "/{productId}",consumes = "application/json",produces = "application/json")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Integer productId,@RequestBody ProductDto productDto) {
		ProductDto updateProduct = this.productService.updateProduct(productId, productDto);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);

	}
	
	@DeleteMapping(value = "/{productId}", produces = "application/json")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) {
			this.productService.deleteProduct(productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("product deleted successfully ", true),HttpStatus.OK);

	}	
}
