package com.codewithaashu.blog.blogging_website.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.codewithaashu.blog.blogging_website.Entity.User;
import com.codewithaashu.blog.blogging_website.repository.UsersRepository;

//user -details service, so we have to create a class that implement userDetails service
@Service // to make this class as a service class
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    // override the method
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // here username is email.
        // so we load users details from db.
        User user = usersRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        // UserDetails is an interface so we have to create a class and override all the
        // method
        return new UserDetails() {
            // override all the method
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singleton(new SimpleGrantedAuthority("USER"));
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

        };
    }
}
