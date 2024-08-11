package com.codewithaashu.blog.blogging_website.payload;

import java.time.LocalDateTime;

import com.codewithaashu.blog.blogging_website.Entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // to get these data
@Setter // to set these data
@Data
@NoArgsConstructor
public class PostDto {
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    @Size(min = 3, max = 10, message = "Slug must be 3 to 10 characters.")
    private String slug;
    private String description;
    private String image;
    private Boolean status;
    private User user;
    private CategoryDto category;
    private LocalDateTime createdAt;
}
