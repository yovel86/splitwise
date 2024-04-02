package com.projects.splitwise.controllers;

import com.projects.splitwise.dtos.RegisterUserRequestDTO;
import com.projects.splitwise.dtos.RegisterUserResponseDTO;
import com.projects.splitwise.dtos.Response;
import com.projects.splitwise.exceptions.InvalidRequestException;
import com.projects.splitwise.models.User;
import com.projects.splitwise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public RegisterUserResponseDTO registerUser(RegisterUserRequestDTO requestDTO) {
        RegisterUserResponseDTO responseDTO = new RegisterUserResponseDTO();
        try {
            validateRequestDTO(requestDTO);
            User user = this.userService.registerUser(requestDTO.getUserName(), requestDTO.getPassword(), requestDTO.getPhoneNumber());
            responseDTO.setUser(user);
            responseDTO.setResponse(Response.getSuccessResponse());
        } catch (Exception e) {
            responseDTO.setResponse(Response.getFailureResponse(e.getMessage()));
        }
        return responseDTO;
    }

    public void validateRequestDTO(RegisterUserRequestDTO requestDTO) throws InvalidRequestException {
        if(requestDTO.getUserName() == null || requestDTO.getPassword() == null || requestDTO.getPhoneNumber() == null) {
            throw new InvalidRequestException("Username, Password or Phone Number cannot be empty");
        }
    }

}
