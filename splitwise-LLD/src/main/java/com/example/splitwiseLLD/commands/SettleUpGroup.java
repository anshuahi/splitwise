package com.example.splitwiseLLD.commands;

import com.example.splitwiseLLD.controllers.SettleUpController;
import com.example.splitwiseLLD.dtos.SettleUpGroupRequestDTO;
import com.example.splitwiseLLD.dtos.SettleUpGroupResponseDTO;
import com.example.splitwiseLLD.dtos.SettleUpUserRequestDTO;
import com.example.splitwiseLLD.dtos.SettleUpUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpGroup implements Command {
    // SettleUpGroup is a command from the user input
    /*
        Expected Input : SettleUpGroup <group_id>
     */
    private SettleUpController settleUpController;

    @Autowired
    public SettleUpGroup(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(0).equals(CommandKeywords.SettleUpGroup);
    }

    @Override
    public void execute(String input) {

        List<String> words = List.of(input.split(" "));
        SettleUpGroupRequestDTO settleUpGroupRequestDTO = new SettleUpGroupRequestDTO();
        settleUpGroupRequestDTO.setGroupId(Long.valueOf(words.get(1)));

        SettleUpGroupResponseDTO responseDTO = settleUpController.settleUpGroup(settleUpGroupRequestDTO);
    }
}
