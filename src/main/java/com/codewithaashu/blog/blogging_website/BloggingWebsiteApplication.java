package com.codewithaashu.blog.blogging_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloggingWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingWebsiteApplication.class, args);
	}

}

// 1. The first step is to setup the database by importing the database link in
// application.properties
// 2. To create entity class i.e. create table by specifying table name and
// column field
// 3. To create repository interface to perform CRUD operation on database