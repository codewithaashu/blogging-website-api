package com.codewithaashu.blog.blogging_website.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    Integer value;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer value) {
        // create a custom exception message
        super(String.format("%s is not exist with %s : %d ", resourceName, fieldName, value));
    }
}
