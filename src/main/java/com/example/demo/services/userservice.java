package com.example.demo.services;

import com.example.demo.models.usermodel;
import com.example.demo.repositories.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class userservice implements UserDetailsService {

    @Autowired
    private userrepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        usermodel user = repo.findByUsername(username);

        if (user != null) {
            var springUser = User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();

            return springUser;
        }

        return null;
    }
}
