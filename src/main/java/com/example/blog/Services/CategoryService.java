package com.example.blog.Services;

import com.example.blog.Payloads.CategoryDto;
import com.example.blog.Payloads.UserDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

    CategoryDto getCategoryById(Integer categoryId);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Integer categoryId);


}
