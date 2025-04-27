package com.wisitor.project.service;

import com.wisitor.project.model.User;
import com.wisitor.project.model.UserPrincipal;
import com.wisitor.project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findById(username);
        if(user.isEmpty()){
            System.out.println("No user found");
            throw new UsernameNotFoundException("No user found 404");
        }

        return new UserPrincipal(user.orElse(null));
    }
}
