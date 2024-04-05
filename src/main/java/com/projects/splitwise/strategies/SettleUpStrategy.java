package com.projects.splitwise.strategies;

import com.projects.splitwise.models.Transaction;
import com.projects.splitwise.models.User;

import java.util.List;
import java.util.Map;

public interface SettleUpStrategy {

    List<Transaction> settleUp(Map<User, Double> userTotal);

}
