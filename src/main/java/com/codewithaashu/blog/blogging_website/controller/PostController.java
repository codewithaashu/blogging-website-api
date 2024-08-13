package com.codewithaashu.blog.blogging_website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codewithaashu.blog.blogging_website.Response.ApiResponse;
import com.codewithaashu.blog.blogging_website.Response.ApisResponse;
import com.codewithaashu.blog.blogging_website.payload.PostDto;
import com.codewithaashu.blog.blogging_website.service.PostService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController // to create class to be rest controller class
@RequestMapping("/api/post") // to define inital endpoints
public class PostController {
    @Autowired // to inject the service class
    PostService postService;// to use all the service method

    // create a post controller
    @PostMapping("/user/{userId}/category/{categoryId}") // to create a post mapping
    public ResponseEntity<ApiResponse<PostDto>> createPostController(@RequestBody PostDto post,
            @PathVariable(name = "userId", required = true) Integer userId,
            @PathVariable(name = "categoryId", required = true) Integer categoryId) {
        // pass data to service to handle it
        PostDto createdPost = postService.createPost(post, userId, categoryId);
        // create the response
        ApiResponse<PostDto> apiResponse = new ApiResponse<PostDto>(createdPost, true, "Post created successfully");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    // get all posts controller
    @GetMapping("")
    public ResponseEntity<ApisResponse<PostDto>> getAllPostsController(
            @RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "createdAt") String sortBy) {
        // pass data to service to handle it
        List<PostDto> posts = postService.getAllPost(pageNumber, pageSize, sortBy);

        return new ResponseEntity<>(new ApisResponse<PostDto>(posts, "Successfully fetched", true), HttpStatus.OK);
    }

    // get post by user controller
    @GetMapping("/user/{id}")
    public ResponseEntity<ApisResponse<PostDto>> getPostByUserController(@PathVariable("id") Integer id) {
        // pass data to service to handle it
        List<PostDto> posts = postService.getPostByUser(id);
        return new ResponseEntity<>(new ApisResponse<PostDto>(posts, "Successfully fetched", true), HttpStatus.OK);
    }

    // get post by category controller
    @GetMapping("/category/{id}")
    public ResponseEntity<ApisResponse<PostDto>> getPostByCategoryController(@PathVariable("id") Integer id) {
        // pass data to service to handle it
        List<PostDto> posts = postService.getPostByCategory(id);
        return new ResponseEntity<>(new ApisResponse<PostDto>(posts, "Successfully fetched.", true), HttpStatus.OK);
    }

    // get post controller
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> getPostController(@PathVariable("id") Integer id) {
        // pass data to service to handle it
        PostDto postDto = postService.getPost(id);
        return new ResponseEntity<>(new ApiResponse<PostDto>(postDto, true, "Post fetched successfully"),
                HttpStatus.OK);
    }

    // update post controller
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> updatePostController(@PathVariable("id") Integer id,
            @RequestBody PostDto postDto) {
        // pass to service
        PostDto updatedPost = postService.updatePost(postDto, id);
        return new ResponseEntity<>(new ApiResponse<PostDto>(updatedPost, true, "Post updated successfully"),
                HttpStatus.OK);
    }

    // delete post controller
    @DeleteMapping("/{id}")
    public ResponseEntity<ApisResponse<PostDto>> deletePostController(@PathVariable("id") Integer id) {
        // pass data to service to handle it
        List<PostDto> postDtos = postService.deletePost(id);
        return new ResponseEntity<>(new ApisResponse<>(postDtos, "Deleted successfully", true), HttpStatus.OK);
    }

    // search post controller
    @GetMapping("/search/{keyword}")
    public ResponseEntity<ApisResponse<PostDto>> searchPost(
            @PathVariable(name = "keyword", required = true) String keyword) {
        List<PostDto> posts = postService.searchPost(keyword);
        return new ResponseEntity<>(new ApisResponse<>(posts, "Post searched successfully", true), HttpStatus.OK);
    }

}
