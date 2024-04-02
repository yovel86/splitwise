package com.projects.splitwise.commands;

import com.projects.splitwise.controllers.UserController;
import com.projects.splitwise.dtos.RegisterUserRequestDTO;
import com.projects.splitwise.dtos.RegisterUserResponseDTO;
import com.projects.splitwise.dtos.ResponseType;
import com.projects.splitwise.exceptions.InvalidCommandFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserCommand implements Command {

    private static final String REGISTER_USER_KEY = "Register";
    private UserController userController;

    @Autowired
    public RegisterUserCommand(UserController userController) {
        this.userController = userController;
        // Adding "RegisterUserCommand" to the Registry
        CommandRegistry.getInstance().addCommand(REGISTER_USER_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandFormatException {
        // Input format - Register (username) (phoneNumber) (password)
        String[] data = input.split(" ");
        if(data.length != 4) {
            throw new InvalidCommandFormatException("Not a valid command format, Check the syntax");
        }
        RegisterUserRequestDTO requestDTO = new RegisterUserRequestDTO();
        requestDTO.setUserName(data[1]);
        requestDTO.setPassword(data[3]);
        requestDTO.setPhoneNumber(data[2]);
        RegisterUserResponseDTO responseDTO = this.userController.registerUser(requestDTO);
        if(responseDTO.getResponse().getType().equals(ResponseType.FAILURE)) {
            System.out.println("Error: " + responseDTO.getResponse().getErrorMsg());
        } else {
            System.out.println(responseDTO.getUser());
        }
    }

}
