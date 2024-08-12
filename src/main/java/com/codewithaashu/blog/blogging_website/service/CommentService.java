package com.codewithaashu.blog.blogging_website.service;

import java.util.List;
import java.util.stream.Collectors;

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
                // change in comment
                Comment comment = modelMapper.map(commentDto, Comment.class);
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

        // delete comment
        public List<CommentDto> deleteComment(Integer id) {
                // search comment by id
                Comment comment = commentRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
                // delete comment
                commentRepository.delete(comment);
                // return remaining comments
                List<Comment> comments = commentRepository.findAll();
                // change in dto and return it
                return comments.stream().map(cmnt -> modelMapper.map(cmnt, CommentDto.class))
                                .collect(Collectors.toList());
        }

        // get all comment by postId
        public List<CommentDto> getCommentByPost(Integer postId) {
                // get post
                Post post = postRepository.findById(postId)
                                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
                // search comments by postId
                List<Comment> comments = commentRepository.findByPost(post);
                // change it and return it
                return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                                .collect(Collectors.toList());
        }

        // get all comment by user
        public List<CommentDto> getCommentByUser(Integer userId) {
                // get the user
                User user = usersRepository.findById(userId)
                                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
                // get all comments by user
                List<Comment> comments = commentRepository.findByUser(user);
                // change it and return it
                return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                                .collect(Collectors.toList());
        }

        // get all comments
        public List<CommentDto> getAllComments() {
                // get all comments by user
                List<Comment> comments = commentRepository.findAll();
                // change it and return it
                return comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class))
                                .collect(Collectors.toList());
        }

}
