package com.codewithaashu.blog.blogging_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithaashu.blog.blogging_website.Entity.Comment;
import com.codewithaashu.blog.blogging_website.Entity.Post;
import com.codewithaashu.blog.blogging_website.Entity.User;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
