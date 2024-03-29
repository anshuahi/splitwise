package com.example.splitwiseLLD.commands;

import com.example.splitwiseLLD.controllers.SettleUpController;
import com.example.splitwiseLLD.dtos.SettleUpUserRequestDTO;
import com.example.splitwiseLLD.dtos.SettleUpUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUser implements Command{
    // SettleUpUser is a command from the user input
    /*
        Expected Input : SettleUpUser <user_id>
     */
    private SettleUpController settleUpController;

    @Autowired
    public SettleUpUser(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(0).equals(CommandKeywords.SettleUpUser);
    }

    @Override
    public void execute(String input) {

        List<String> words = List.of(input.split(" "));
        SettleUpUserRequestDTO settleUpUserRequestDTO = new SettleUpUserRequestDTO();
        settleUpUserRequestDTO.setUserId(Long.valueOf(words.get(1)));

        SettleUpUserResponseDTO responseDTO = settleUpController.settleUpUser(settleUpUserRequestDTO);
    }
}
