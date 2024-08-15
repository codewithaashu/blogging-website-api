package com.codewithaashu.blog.blogging_website.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//By default, Springboot provides form based authentication. But, you can create or configuration own custom authentication i.e. Web-based authentication

@Configuration // to make it a configuration class
@EnableWebSecurity // to enable web basic authon
public class SecurityConfig {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request.anyRequest()
                .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
