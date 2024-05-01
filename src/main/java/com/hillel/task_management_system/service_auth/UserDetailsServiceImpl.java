package com.hillel.task_management_system.service_auth;

import com.hillel.task_management_system.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthService authService;

    @Autowired
    public UserDetailsServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = null;
        try {
            auth = authService.getAuthByLogin(username);
        } catch (SQLException e) {
            throw new RuntimeException("Error! Can't get data from Auth database!", e);
        }
        if (auth == null) {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(auth.getLogin())
                .password(auth.getPassword())
                .roles("USER")
                .build();
    }
}
