package com.boileradvsr.backend.services;

import com.boileradvsr.backend.models.Student;
import com.boileradvsr.backend.models.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private StudentRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student s = repository.findById(username).orElseThrow(RuntimeException::new);
        String dbPassword = s.getPassword();
        String email = s.getEmail();
        String password = s.getPassword();
        return new User(email, password, new ArrayList<>());
    }
}