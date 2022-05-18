package com.example.blog.Controllers;

import com.example.blog.Payloads.ApiResponse;
import com.example.blog.Payloads.CategoryDto;
import com.example.blog.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id){
        CategoryDto categoryDto = categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    private ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    private ResponseEntity<CategoryDto> updateCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto,id);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse("Category deleted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
