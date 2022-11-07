package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.CategoryDto;
import com.ecom.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

//	create category
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/",consumes = "application/json", produces = "application/json")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto save = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(save,HttpStatus.CREATED);
	}
//	get all categories
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<CategoryDto>> getAllProducts() {
		List<CategoryDto> category = this.categoryService.getAllCategories();
		return new ResponseEntity<List<CategoryDto>>(category,HttpStatus.OK);
//		get category by id
	}
	@GetMapping(value = "/{categoryId}", produces = "application/json")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
		CategoryDto product = this.categoryService.getCategoryById(categoryId);
		return new ResponseEntity<CategoryDto>(product,HttpStatus.OK);

	}
//	update category
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{categoryId}",consumes = "application/json", produces = "application/json")
	public ResponseEntity<CategoryDto> createCategory(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryId,categoryDto);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
	}
//	delete category
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
			this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category deleted successfully ", true),HttpStatus.OK);
	}
}
