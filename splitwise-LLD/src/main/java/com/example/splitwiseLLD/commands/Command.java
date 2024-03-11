package com.example.splitwiseLLD.commands;

public interface Command {

    public boolean matches(String input);

    public void execute(String input);
}
