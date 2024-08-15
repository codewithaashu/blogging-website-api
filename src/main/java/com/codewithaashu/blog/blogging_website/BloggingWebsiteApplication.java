package com.codewithaashu.blog.blogging_website;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingWebsiteApplication.class, args);
	}

	// 1. add dependency of ModelMapper
	// 2. create a modelMapper method in main file
	// 3. Use it by creating an instance
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

// 1. The first step is to setup the database by importing the database link in
// application.properties
// 2. To create entity class i.e. create table by specifying table name and
// column field
// 3. To create repository interface to perform CRUD operation on database
