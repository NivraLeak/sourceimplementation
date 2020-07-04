package com.acme.ideogo.service;


import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.model.User;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.TagRepository;
import com.acme.ideogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user );
        return ResponseEntity.ok().build();
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        //user.setSex(userRequest.getSex());
        //user.setOccupation(userRequest.getOccupation());
        //user.setExperience(userRequest.getExperience());
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
