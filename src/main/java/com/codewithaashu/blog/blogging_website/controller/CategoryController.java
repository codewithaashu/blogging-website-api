package com.codewithaashu.blog.blogging_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.payload.CategoryDto;
import com.codewithaashu.blog.blogging_website.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    // to use the method of category service
    @Autowired
    private CategoryService categoryService;

    // create category controller
    @PostMapping("")
    public ResponseEntity<ApiResponse<CategoryDto>> createCategoryController(@RequestBody CategoryDto categoryDto) {
        // pass to service
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(new ApiResponse<CategoryDto>(savedCategory, true, "Category created successfully"),
                HttpStatus.CREATED);
    }
}
