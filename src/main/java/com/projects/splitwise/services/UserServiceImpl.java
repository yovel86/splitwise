package com.projects.splitwise.services;

import com.projects.splitwise.exceptions.RegisterUserException;
import com.projects.splitwise.models.User;
import com.projects.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException {
        Optional<User> existingUser = this.userRepository.findUserByUserNameOrPhoneNumber(userName, phoneNumber);
        if(existingUser.isPresent()) throw new RegisterUserException("User already exists");
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return this.userRepository.save(user);
    }

}
