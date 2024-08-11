package com.codewithaashu.blog.blogging_website.payload;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotNull
    @Size(min = 3, max = 15, message = "Title must be 3 to 15 characters long.")
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
