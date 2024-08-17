package com.codewithaashu.blog.blogging_website.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//By default, Springboot provides form based authentication. But, you can create or configuration own custom authentication i.e. Web-based authentication

//when you add spring security dependency,it automatically makes APIs to be private. 
//Behind the scence,in servlet container there is a one more component add i.e. filter chain. When client send a request then firstly it goes to  filter chain(responsible for changes or filtering the request or response)    
//spring security dependency add a security filter chain. By default, it filter the request by using form based authentication (at time of login. credential are user and password are in terminal. but you can use custom credential and password) and session id(when you logged in).

//now we define own spring filter chain to remove default behaviour of form based authentication and session id validation. because session id validation causes CSRF(Cross Site Request forgery:steel the session id. to prevent this we generate session id always) 
@Configuration // to make it a configuration class
@EnableWebSecurity // to enable web basic authon
public class SecurityConfig {
    // create the user-details service object
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    @Bean
    // define the Security filter chain
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // disble csrf
        // http.csrf(Customizer -> Customizer.disable());
        // define security filter . authenticate every request or you can make any
        // request to be public by using requestMatchers
        // http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        // define own authentication way
        // http.formLogin(Customizer.withDefaults());//it is default form based
        // authentication. but we want to remove this
        // define web basic authentication
        // http.httpBasic(Customizer.withDefaults());
        // session id will create every time
        // http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // return the object of security filter chain
        // return http.build();

        // this can write in short
        return http.csrf(Customizer -> Customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("api/user/register", "api/user/login").permitAll()
                        // these apiURL does not nedd authentication
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                // before the usernamepasswordAuthentication filter it do jwt filter
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    // there is a problem that it authenticate those user which is in application
    // properties. if we want to authenticate all the user by using db. that means
    // we define own Authentication Provider i.e. DaoAuthenticationProvider

    // define own Authentication Provider.
    @Bean // it can be autowired
    public AuthenticationProvider authenticationProvider() {
        // authentication is done by db .i.e DaoAuthenticationProvider
        // create an object of daoAuthenticationProvider. we load the data and
        // configure password encoder
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // set the user service i.e. credential.. we load data by using
        // userDetailsService
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // userDetailsService is an interface so we have to implement class and overload
        // the loadByUsername method
        // set the password encoding service.. to define the password encoding
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return daoAuthenticationProvider;
    }

    // Global AuthenticationManager configured with an AuthenticationProvider bean
    // to configure authentication manager
    @Bean // it can be autowired
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
