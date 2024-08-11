package com.codewithaashu.blog.blogging_website.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//it is basically modal. it is subset of entity
//it is class in which we bind all the attributes of user which is used in request and response which is provide by and to the user

@Getter // automatically create getter method to access these attributes
@Setter // automatically create setter method to set these attributes
@Data
public class UserDto {
    // id will automatically generate. so, it is not provided by user
    private Integer id;
    @NotNull // name must not be null
    @Size(min = 3, max = 20, message = "name must be 3 to 20 characters") // we also validate on the length of name.if
                                                                          // it is not valid, then it throw custom
                                                                          // message
    private String name;
    @NotNull // email must not be null
    @Email(message = "Invalid email") // email must be email. if it is not valid email, we send a error message
    private String email;
    private boolean emailVerified;
    private String accountType;
    private String image;
    // password must be follow regular expression otherwise throw custom message
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Password must be 8 characcters long containing atleast one lower-case,upper-case letter and atleast one digit and special-character")
    private String password;
    private Integer followers;
}

// Validation is done by hibernate validator which is implementation of java
// validation api.
// it is used to validate the data before it is persisted in the database.
// Therefore, we always validate payload data, not the entity data.
// for working validation functionality in controller, we use @Valid annotaion
// in controller. otherwise it doesn't work