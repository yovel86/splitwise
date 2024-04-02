package com.projects.splitwise.services;

import com.projects.splitwise.exceptions.RegisterUserException;
import com.projects.splitwise.models.User;

public interface UserService {

    User registerUser(String userName, String password, String phoneNumber) throws RegisterUserException;

}
