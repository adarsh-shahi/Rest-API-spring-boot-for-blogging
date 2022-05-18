package com.example.blog.Services.Implementation;

import com.example.blog.Entities.Category;
import com.example.blog.Entities.User;
import com.example.blog.Exceptions.ResourceNotFoundException;
import com.example.blog.Payloads.CategoryDto;
import com.example.blog.Payloads.UserDto;
import com.example.blog.Repositories.CategoryRepository;
import com.example.blog.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryRepository.save(category);
        return categoryDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));

        findCategory.setTitle(categoryDto.getTitle());
        findCategory.setDescription(categoryDto.getDescription());
        categoryRepository.save(findCategory);

        return modelMapper.map(findCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        return modelMapper.map(findCategory, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategory = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for(Category category : allCategory){
            categoryDtoList.add(modelMapper.map(category, CategoryDto.class));
        }
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category findCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));
        categoryRepository.delete(findCategory);

    }
}
