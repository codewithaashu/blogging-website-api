package com.codewithaashu.blog.blogging_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaashu.blog.blogging_website.Entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
