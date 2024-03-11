package com.example.splitwiseLLD.controllers;

import com.example.splitwiseLLD.dtos.SettleUpGroupRequestDTO;
import com.example.splitwiseLLD.dtos.SettleUpResponseDTO;
import com.example.splitwiseLLD.models.Group;
import com.example.splitwiseLLD.models.Transaction;
import com.example.splitwiseLLD.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GroupController {
    @Autowired
    private GroupService groupService;

    public SettleUpResponseDTO settleUp(SettleUpGroupRequestDTO requestDTO){
        List<Transaction> transactions = groupService.settleUp(requestDTO.getGroupId());

        SettleUpResponseDTO responseDTO = new SettleUpResponseDTO();
        responseDTO.setTransactions(transactions);

        return responseDTO;
    }
}
