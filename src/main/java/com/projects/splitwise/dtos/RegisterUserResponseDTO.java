package com.projects.splitwise.dtos;

import com.projects.splitwise.models.User;
import lombok.Data;

@Data
public class RegisterUserResponseDTO {

    private User user;
    private Response response;

}
