package com.codewithaashu.blog.blogging_website.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse {
    private String message;
    private Boolean status;
}
