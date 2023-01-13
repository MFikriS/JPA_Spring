package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.models.Role;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sorry, User not found"));
    }

    @Override
    public Boolean save(User user) {
        //role.setId(3);
        //user.setRole(role);
        userRepository.save(user);
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public Boolean delete(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    /*
    @Override
    public Boolean getByEmail(String email) {
        userRepository.findByEmail(email)
        return null;
    }

    @Override
    public Boolean getByPassword(String password) {
        return null;
    }
    */
    
}
