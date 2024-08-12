package com.codewithaashu.blog.blogging_website.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.Category;
import com.codewithaashu.blog.blogging_website.exceptions.ResourceNotFoundException;
import com.codewithaashu.blog.blogging_website.payload.CategoryDto;
import com.codewithaashu.blog.blogging_website.repository.CategoryRepository;

@Service
public class CategoryService {
    // to access all the operation on the category table, we use category repository
    // create repository instance
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    // create category
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // change in entity
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        // save category in database
        Category savedCategory = categoryRepository.save(category);
        // change in entity
        CategoryDto categoryDto1 = new CategoryDto();
        BeanUtils.copyProperties(savedCategory, categoryDto1);
        return categoryDto1;
    }

    // update category
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        // get the previous category data by using id
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        // update the category data
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        // save the category data in database
        Category updatedCategory = categoryRepository.save(category);
        // change in dto form and return it
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    // delete category
    public List<CategoryDto> deleteCategory(Integer id) {
        // get the category by id
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        // delete the category
        categoryRepository.delete(category);
        // get remaining category
        List<Category> remCategories = categoryRepository.findAll();
        // change in dto form and return it
        return remCategories.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
    }

    // get category
    public CategoryDto getCategory(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        // change in dto form and return it
        return modelMapper.map(category, CategoryDto.class);
    }

    // get all category
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        // change in dto form and return it
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

}
