package com.codewithaashu.blog.blogging_website.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.Category;
import com.codewithaashu.blog.blogging_website.payload.CategoryDto;
import com.codewithaashu.blog.blogging_website.repository.CategoryRepository;

@Service
public class CategoryService {
    // to access all the operation on the category table, we use category repository
    // create repository instance
    @Autowired
    private CategoryRepository categoryRepository;

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
}
