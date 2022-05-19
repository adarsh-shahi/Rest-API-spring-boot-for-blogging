package com.example.blog.Security;

import com.example.blog.Entities.User;
import com.example.blog.Exceptions.ResourceNotFoundException;
import com.example.blog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Loading User from database ny username

        User user = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("user", "with : " + username, 0));

        return user;
    }
}
