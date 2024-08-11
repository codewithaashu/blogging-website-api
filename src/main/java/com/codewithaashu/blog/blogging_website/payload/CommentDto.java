package com.codewithaashu.blog.blogging_website.payload;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {
    private Integer id;
    @NotNull
    private String content;
    private LocalDateTime createdAt;
    private UserDto user;
}
