package com.projects.splitwise.commands;

import com.projects.splitwise.exceptions.InvalidCommandFormatException;

public interface Command {

    void execute(String input) throws InvalidCommandFormatException;

}
