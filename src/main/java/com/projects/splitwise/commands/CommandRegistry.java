package com.projects.splitwise.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {

    private Map<String, Command> registry;

    private CommandRegistry() {
        registry = new HashMap<>();
    }

    private static CommandRegistry INSTANCE = new CommandRegistry();

    public static CommandRegistry getInstance() {
        return INSTANCE;
    }

    public void addCommand(String key, Command command) {
        registry.put(key, command);
    }

    public Command getCommand(String input) {
        for(Map.Entry<String, Command> entry: registry.entrySet()) {
            String key = entry.getKey();
            if(input.contains(key)) {
                return entry.getValue();
            }
        }
        throw new UnsupportedOperationException("Not a valid command, please check the spelling of command");
    }

}
