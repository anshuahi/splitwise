package com.anshuahi.splitwise.service;

import com.anshuahi.splitwise.exception.ResourceNotFoundException;
import com.anshuahi.splitwise.model.User;
import com.anshuahi.splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> searchUsers(String prefix){
        return userRepository.searchUsers(prefix);
    }

    public User getUser(String phone){
        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with phone: " + phone));
    }
}
