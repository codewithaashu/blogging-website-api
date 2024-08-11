package com.codewithaashu.blog.blogging_website.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.Category;
import com.codewithaashu.blog.blogging_website.Entity.Post;
import com.codewithaashu.blog.blogging_website.Entity.User;
import com.codewithaashu.blog.blogging_website.exceptions.ResourceNotFoundException;
// import com.codewithaashu.blog.blogging_website.payload.CategoryDto;
import com.codewithaashu.blog.blogging_website.payload.PostDto;
import com.codewithaashu.blog.blogging_website.repository.CategoryRepository;
import com.codewithaashu.blog.blogging_website.repository.PostRepository;
import com.codewithaashu.blog.blogging_website.repository.UsersRepository;

@Service
public class PostService {
    // create an instance of post repository to access all the method for curd
    // opertion
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // create a post
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        // create a post entity by copying all the data from frontend
        Post post = this.modelMapper.map(postDto, Post.class);
        // find the details of user
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
        // set in post
        post.setUser(user);
        // find the details of category
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        // set in post
        post.setCategory(category);
        // save the post in db
        Post savedPost = postRepository.save(post);
        // change the saved post to postDto. so, we send to frontend
        PostDto savePostDto = this.modelMapper.map(savedPost, PostDto.class);
        return savePostDto;
    }

    // get all post
    public List<PostDto> getAllPost() {
        // get all post from repository
        List<Post> posts = postRepository.findAll();
        // convert it into postDto form
        List<PostDto> postDtos = posts.stream().map(post -> {
            PostDto postDto = this.modelMapper.map(post, PostDto.class);
            return postDto;
        }).collect(Collectors.toList());
        return postDtos;
    }

    // get post by user
    public List<PostDto> getPostByUser(Integer userId) {
        // first of all we get user from userId
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
        // get all post by user from repository
        List<Post> userPosts = postRepository.findByUser(user);
        // convert it into postDto form
        List<PostDto> postDtos = userPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    // get post by category
    public List<PostDto> getPostByCategory(Integer categoryId) {
        // first of all we get category from categoryId
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        // get all post by category from repository
        List<Post> categoryPosts = postRepository.findByCategory(category);
        // convert it into postDto form
        List<PostDto> postDtos = categoryPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    // update post
    public PostDto updatePost(PostDto postDto, Integer id) {
        // get post from id
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setImage(postDto.getImage());
        post.setDescription(postDto.getDescription());
        post.setSlug(postDto.getSlug());
        post.setTitle(postDto.getTitle());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);
    }

    // delete post
    public List<PostDto> deletePost(Integer id) {
        // get post
        Post deletedPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        // delete post
        postRepository.delete(deletedPost);
        // now get remaining post
        List<Post> remainingPosts = postRepository.findAll();
        // convert it into postDto form
        List<PostDto> postDtos = remainingPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
        return postDtos;
    }

    // get single post
    public PostDto getPost(Integer id) {
        // get post by repository
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        // convert it into postDto form
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }

    // search post

}
