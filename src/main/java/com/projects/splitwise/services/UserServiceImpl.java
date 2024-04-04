package com.projects.splitwise.services;

import com.projects.splitwise.exceptions.InvalidUserException;
import com.projects.splitwise.exceptions.RegisterUserException;
import com.projects.splitwise.models.User;
import com.projects.splitwise.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException {
        Optional<User> existingUser = this.userRepository.findUserByUserNameOrPhoneNumber(userName, phoneNumber);
        if(existingUser.isPresent()) throw new RegisterUserException("User already exists");
        User user = new User();
        user.setUserName(userName);
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setPhoneNumber(phoneNumber);
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return this.userRepository.save(user);
    }

    public void login(String userName, String password) throws InvalidUserException {
        Optional<User> optionalUser = this.userRepository.findUserByUserName(userName);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(encoder.matches(password, user.getPassword())) {
                // Proceed to login
                System.out.println("Proceeded to login");
            } else {
                // throw error
                throw new InvalidUserException("Password is wrong, try again");
            }
        } else {
            throw new InvalidUserException("User is not registered");
        }
    }

}
