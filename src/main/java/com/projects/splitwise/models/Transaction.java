package com.projects.splitwise.models;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Transaction extends BaseModel {

    @ManyToOne
    private User pairFrom;
    @ManyToOne
    private User paidTo;
    private double amount;

}
