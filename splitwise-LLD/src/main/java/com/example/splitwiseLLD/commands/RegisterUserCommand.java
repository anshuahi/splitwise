package com.example.splitwiseLLD.commands;

import com.example.splitwiseLLD.controllers.SettleUpController;
import com.example.splitwiseLLD.dtos.SettleUpUserRequestDTO;
import com.example.splitwiseLLD.dtos.SettleUpUserResponseDTO;

import java.util.List;

public class RegisterUserCommand implements Command {
    // RegisterUserCommand is a command from the user input
    /*
        Expected Input : RegisterUser <username> <phoneNumber> <password>
     */
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));

        return words.size() == 4 && words.get(0).equals(CommandKeywords.RegisterUser);
    }

    @Override
    public void execute(String input) {

    }
}
