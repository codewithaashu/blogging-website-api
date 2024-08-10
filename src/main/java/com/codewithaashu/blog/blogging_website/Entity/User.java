package com.codewithaashu.blog.blogging_website.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// this class is used to mapping the users table i.e. create a table with column name and their constraints
@Entity // it will create class to be entity class
@Getter // to access private data it automatically generate getter method
@Setter // to set value of private data it automatically generate setter method
@Table(name = "users") // to create table with given name
public class User {
    // to create primary key of table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // it will generate automatically value. and generation
                                                        // strategey is unique
    private Integer id;
    @Column(name = "Full Name", length = 30, nullable = false) // you can also define column name
    private String name;
    // you can provide constraint on the field name i.e. on Column
    @Column(nullable = false, length = 50, unique = true) // provide constraint on the email value
    private String email;
    @Column(name = "Email Verified") // you can also define column name
    private Boolean emailVerified;
    @Column(name = "Account Type")
    private String accountType;
    private String image;
    private String password;
    private Integer followers;

    // to define the default value. if you do not pass these field. it automatically
    // take these values
    @PrePersist
    public void prePersist() {
        if (emailVerified == null) { // if user do not provide emailVerified value then it takes default value
            setEmailVerified(false);
        }
        if (accountType == null) {
            setAccountType("user");
        }
        if (image == null) {
            setImage("https://cdn-icons-png.freepik.com/512/3177/3177440.png?ga=GA1.1.1230693165.1717659966");
        }
        if (followers == null) {
            setFollowers(0);
        }
    }
}
