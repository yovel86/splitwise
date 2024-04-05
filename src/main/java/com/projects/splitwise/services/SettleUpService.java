package com.projects.splitwise.services;


import com.projects.splitwise.exceptions.InvalidRequestException;
import com.projects.splitwise.models.Transaction;

import java.util.List;

public interface SettleUpService {

    List<Transaction> settleGroup(int groupId) throws InvalidRequestException;

    List<Transaction> settleUser(int userId) throws InvalidRequestException;

}
