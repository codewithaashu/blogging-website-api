package com.codewithaashu.blog.blogging_website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.payload.CommentDto;
import com.codewithaashu.blog.blogging_website.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // create comment controller
    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<ApiResponse<CommentDto>> createComment(@PathVariable("userId") Integer userId,
            @PathVariable("postId") Integer postId, @RequestBody CommentDto commentDto) {
        System.out.println(userId + "---" + postId);
        CommentDto comment = commentService.createComment(commentDto, userId, postId);
        return new ResponseEntity<>(new ApiResponse<CommentDto>(comment, true, "Successfully created"),
                HttpStatus.CREATED);
    }

}
