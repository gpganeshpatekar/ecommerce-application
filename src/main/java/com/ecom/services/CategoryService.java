package com.ecom.services;

import java.util.List;

import com.ecom.payload.CategoryDto;

public interface CategoryService {
//	create category
	CategoryDto createCategory(CategoryDto categoryDto);
//	get all categories
	List<CategoryDto> getAllCategories();
//	get by category id
	CategoryDto getCategoryById(Integer categoryId);
//	update category
	CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto);
//	delete category by id
	void deleteCategory(Integer categoryId);

}
