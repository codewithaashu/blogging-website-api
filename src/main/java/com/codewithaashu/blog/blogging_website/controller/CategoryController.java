package com.codewithaashu.blog.blogging_website.controller;

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

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.Response.ApisResponse;
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

    // update category controller
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategoryController(@RequestBody CategoryDto categoryDto,
            @PathVariable("id") Integer id) {
        // pass to service
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(
                new ApiResponse<CategoryDto>(updatedCategory, true, "Category updated successfully"), HttpStatus.OK);
    }

    // delete category controller
    @DeleteMapping("/{id}")
    public ResponseEntity<ApisResponse<CategoryDto>> deleteCategoryController(@PathVariable("id") Integer id) {
        // pass to service
        List<CategoryDto> categories = categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApisResponse<CategoryDto>(categories, "Category deleted successfully", true),
                HttpStatus.OK);
    }

    // get all category
    @GetMapping("")
    public ResponseEntity<ApisResponse<CategoryDto>> getAllCategoryController() {
        List<CategoryDto> categories = categoryService.getAllCategory();
        return new ResponseEntity<>(new ApisResponse<CategoryDto>(categories, "Category retrieved successfully", true),
                HttpStatus.OK);
    }

    // get category
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryController(@PathVariable("id") Integer id) {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return new ResponseEntity<>(
                new ApiResponse<CategoryDto>(categoryDto, true, "Categories retrived successfullly"), HttpStatus.OK);
    }
}
