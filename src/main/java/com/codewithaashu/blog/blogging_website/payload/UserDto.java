package com.codewithaashu.blog.blogging_website.payload;

import lombok.Getter;
import lombok.Setter;

//it is basically modal. it is subset of entity
//it is class in which we bind all the attributes of user which is used in request and response which is provide by and to the user

@Getter // automatically create getter method to access these attributes
@Setter // automatically create setter method to set these attributes
public class UserDto {
    // id will automatically generate. so, it is not provided by user
    private Integer id;
    private String name;
    private String email;
    private boolean emailVerified;
    private String accountType;
    private String image;
    private String password;
    private Integer followers;
}
