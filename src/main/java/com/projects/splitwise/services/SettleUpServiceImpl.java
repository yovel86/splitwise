package com.projects.splitwise.services;

import com.projects.splitwise.exceptions.InvalidRequestException;
import com.projects.splitwise.models.*;
import com.projects.splitwise.repositories.GroupExpenseRepository;
import com.projects.splitwise.repositories.GroupRepository;
import com.projects.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SettleUpServiceImpl implements SettleUpService {

    private GroupRepository groupRepository;
    private GroupExpenseRepository groupExpenseRepository;
    private SettleUpStrategy settleUpStrategy;

    @Autowired
    public SettleUpServiceImpl(GroupRepository groupRepository, GroupExpenseRepository groupExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    @Override
    public List<Transaction> settleGroup(int groupId) throws InvalidRequestException {
        /*
        1. Validate Group ID using DB
        2. Fetch list of expenses from group expenses using group id
        3. Condense all the expenses down to user and their final total
        4. Use Strategy pattern to get the list of Transactions need to be made
         */
        Group group = this.groupRepository.findById(groupId).orElseThrow(() -> new InvalidRequestException("Invalid Group ID"));
        List<GroupExpense> groupExpenses = this.groupExpenseRepository.findAllByGroupId(groupId);
        List<Expense> expenses = groupExpenses.stream().map(GroupExpense::getExpense).toList();
        Map<User, Double> userTotal = getCondensedTotal(expenses); // Used to store the Condensed total
        return settleUpStrategy.settleUp(userTotal);
    }

    @Override
    public List<Transaction> settleUser(int userId) throws InvalidRequestException {
        /*
        1. Validate the user
        2. Fetch the list of expenses that the user is part of (query the ExpenseUser table)
        3. Condense all the expenses down to user and their final total
        4. Use Strategy pattern to get the list of Transactions need to be made
         */
        return null;
    }

    public Map<User, Double> getCondensedTotal(List<Expense> expenses) {
        Map<User, Double> userTotal = new HashMap<>();
        for(Expense expense: expenses) {
            for(ExpenseUser expenseUser: expense.getExpenseUsers()) {
                User user = expenseUser.getUser();
                userTotal.put(user, userTotal.getOrDefault(user, 0d) +
                        (expenseUser.getExpenseType().equals(ExpenseType.PAID) ? 1 : -1) * expenseUser.getAmount());
            }
        }
        return userTotal;
    }

}
