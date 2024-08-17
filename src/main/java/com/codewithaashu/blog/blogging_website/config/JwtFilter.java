package com.codewithaashu.blog.blogging_website.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codewithaashu.blog.blogging_website.security.JWTService;
import com.codewithaashu.blog.blogging_website.security.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    ApplicationContext context;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        // get authorization token from request header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        // is token comes from request header is valid . for this, we check
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            // extract username from token
            username = jwtService.extractUserName(token);
        }
        // check username is extract from token and SecurityContextHolder will not
        // authenticate another request
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // now we validate token with user
            // so, we fetch userDetails
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
            // validate the token with token and userDetails
            if (jwtService.validateToken(token, userDetails)) {
                // get the authoToken and set details in it
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                // set authToken in context holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // go to next filter chain
        filterChain.doFilter(request, response);
    }
}
