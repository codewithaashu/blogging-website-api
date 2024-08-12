package com.codewithaashu.blog.blogging_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.Response.ApisResponse;
import com.codewithaashu.blog.blogging_website.payload.CommentDto;
import com.codewithaashu.blog.blogging_website.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
        @Autowired
        private CommentService commentService;

        // create comment controller
        @PostMapping("/user/{userId}/post/{postId}")
        public ResponseEntity<ApiResponse<CommentDto>> createCommentController(@PathVariable("userId") Integer userId,
                        @PathVariable("postId") Integer postId, @RequestBody CommentDto commentDto) {
                System.out.println(userId + "---" + postId);
                CommentDto comment = commentService.createComment(commentDto, userId, postId);
                return new ResponseEntity<>(new ApiResponse<CommentDto>(comment, true, "Successfully created"),
                                HttpStatus.CREATED);
        }

        // delete comment
        @DeleteMapping("/{id}")
        public ResponseEntity<ApisResponse<CommentDto>> deleteCommentController(@PathVariable("id") Integer id) {
                List<CommentDto> comments = commentService.deleteComment(id);
                return new ResponseEntity<>(new ApisResponse<CommentDto>(comments, "Deleted successfully", true),
                                HttpStatus.OK);
        }

        // get all comment by post
        @GetMapping("/post/{postId}")
        public ResponseEntity<ApisResponse<CommentDto>> getAllCommentByPostController(
                        @PathVariable("postId") Integer postId) {
                List<CommentDto> comments = commentService.getCommentByPost(postId);
                return new ResponseEntity<>(
                                new ApisResponse<CommentDto>(comments, "Comment retrieved successfully", true),
                                HttpStatus.OK);
        }

        // get all comment by user
        @GetMapping("/user/{userId}")
        public ResponseEntity<ApisResponse<CommentDto>> getAllCommentByUserController(
                        @PathVariable("userId") Integer userId) {
                List<CommentDto> comments = commentService.getCommentByUser(userId);
                return new ResponseEntity<>(
                                new ApisResponse<CommentDto>(comments, "Comment retrieved successfully", true),
                                HttpStatus.OK);
        }

        // get all comments
        @GetMapping("")
        public ResponseEntity<ApisResponse<CommentDto>> getAllCommentsController() {
                List<CommentDto> comments = commentService.getAllComments();
                return new ResponseEntity<>(
                                new ApisResponse<CommentDto>(comments, "Comment retrieved successfully", true),
                                HttpStatus.OK);
        }

}
