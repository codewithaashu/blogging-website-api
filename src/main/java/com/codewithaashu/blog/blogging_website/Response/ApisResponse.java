package com.codewithaashu.blog.blogging_website.Response;

import java.util.List;

import lombok.Getter;

@Getter
public class ApisResponse<T> {
    private List<T> data;
    private String message;
    private Boolean status;

    public ApisResponse(List<T> data, String message, Boolean status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }
}
