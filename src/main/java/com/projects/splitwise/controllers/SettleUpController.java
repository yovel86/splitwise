package com.projects.splitwise.controllers;

import com.projects.splitwise.dtos.Response;
import com.projects.splitwise.dtos.SettleGroupRequestDTO;
import com.projects.splitwise.dtos.SettleGroupResponseDTO;
import com.projects.splitwise.exceptions.InvalidRequestException;
import com.projects.splitwise.models.Transaction;
import com.projects.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    public SettleGroupResponseDTO settleGroup(SettleGroupRequestDTO requestDTO) {
        int groupId = requestDTO.getGroupId();
        SettleGroupResponseDTO responseDTO = new SettleGroupResponseDTO();
        try {
            if(groupId <= 0) throw new InvalidRequestException("Invalid User Id");
            List<Transaction> transactions = this.settleUpService.settleGroup(groupId);
            responseDTO.setTransactions(transactions);
            responseDTO.setResponse(Response.getSuccessResponse());
        } catch (Exception e) {
            responseDTO.setResponse(Response.getFailureResponse(e.getMessage()));
        }
        return responseDTO;
    }

}
