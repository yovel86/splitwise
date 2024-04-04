package com.projects.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data @Entity
public class ExpenseUser extends BaseModel {

    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.ORDINAL)
    private ExpenseType expenseType;
    private double amount;

}
