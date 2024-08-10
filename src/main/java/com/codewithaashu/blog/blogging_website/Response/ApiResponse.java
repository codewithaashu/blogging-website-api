package com.codewithaashu.blog.blogging_website.Response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private T data;
    private String message;
    private Boolean status;

    public ApiResponse(T data, Boolean status, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
