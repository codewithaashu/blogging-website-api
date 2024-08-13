package com.codewithaashu.blog.blogging_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaashu.blog.blogging_website.Entity.Category;
import com.codewithaashu.blog.blogging_website.Entity.Post;
import com.codewithaashu.blog.blogging_website.Entity.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    // define a method
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    // it must be a meaningfull method name
    List<Post> findByTitleContains(String keyword);

    // List<Post> findByTitleContaining(String keyword);
    // List<Post> findByTitleNotContaining(String keyword);

    // List<Post> findByTitleNotContains(String keyword);

    // List<Post> findByDescriptionContaining(String keyword);
}