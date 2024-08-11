package com.codewithaashu.blog.blogging_website.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.Comment;
import com.codewithaashu.blog.blogging_website.Entity.Post;
import com.codewithaashu.blog.blogging_website.Entity.User;
import com.codewithaashu.blog.blogging_website.exceptions.ResourceNotFoundException;
import com.codewithaashu.blog.blogging_website.payload.CommentDto;
import com.codewithaashu.blog.blogging_website.repository.CommentRepository;
import com.codewithaashu.blog.blogging_website.repository.PostRepository;
import com.codewithaashu.blog.blogging_website.repository.UsersRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    // create comment
    public CommentDto createComment(CommentDto commentDto, Integer userId, Integer postId) {
        System.out.println(userId + "-rohan--" + postId);
        // change in comment
        Comment comment = modelMapper.map(commentDto, Comment.class);
        System.out.println(comment.toString());
        // get user
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", userId));
        // save in comment entity
        comment.setUser(user);
        // get post
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        // save in comment entity
        comment.setPost(post);
        // save in db
        Comment savedComment = commentRepository.save(comment);
        // change in commentDto
        CommentDto commentDto1 = modelMapper.map(savedComment, CommentDto.class);
        return commentDto1;
    }
}
