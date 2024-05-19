package com.ItemGenerator.ItemGenerator.service;

import com.ItemGenerator.ItemGenerator.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
