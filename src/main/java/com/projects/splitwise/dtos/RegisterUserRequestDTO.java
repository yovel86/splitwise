package com.projects.splitwise.dtos;

import lombok.Data;

@Data
public class RegisterUserRequestDTO {

    private String userName;
    private String password;
    private String phoneNumber;

}
