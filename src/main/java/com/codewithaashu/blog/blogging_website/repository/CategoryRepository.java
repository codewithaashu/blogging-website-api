package com.codewithaashu.blog.blogging_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaashu.blog.blogging_website.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
