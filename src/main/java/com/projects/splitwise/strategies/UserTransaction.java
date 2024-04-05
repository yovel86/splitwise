package com.projects.splitwise.strategies;

import com.projects.splitwise.models.User;
import lombok.Data;

@Data
public class UserTransaction {

    private User user;
    private double amount;

    public UserTransaction(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

}
