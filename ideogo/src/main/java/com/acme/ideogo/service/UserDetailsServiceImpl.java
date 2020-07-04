package com.acme.ideogo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class UserDetailsServiceImpl implements UserDetailsService {
//    private  static final  String DEFAULT_USERNAME = "john.doe@gmail.com";
//    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES =new ArrayList<>();
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        String defaultPassword = passwordEncoder.encode("password");
//        if(DEFAULT_USERNAME.equals(username)) {
//            return new User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
//        }
//        throw new UsernameNotFoundException("User not found with username " + username);
//    }
//
//    public List<User> getAll() {
//        return Arrays.asList(
//                new User("john.doe@gmail.com", passwordEncoder.encode("password"), DEFAULT_AUTHORITIES),
//                new User("jason.bourne@gmail.com", passwordEncoder.encode("1234"), DEFAULT_AUTHORITIES)
//        );
//    }
//}
//