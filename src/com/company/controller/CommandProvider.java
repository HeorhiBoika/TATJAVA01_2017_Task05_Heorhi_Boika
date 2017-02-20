package com.company.controller;

import com.company.controller.exception.ControllerException;
import com.company.controller.impl.DomParser;
import com.company.controller.impl.SaxParser;
import com.company.controller.impl.StaxParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PC on 19.02.2017.
 */
public class CommandProvider {
    private Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {

        repository.put(CommandName.DOM, new DomParser());
        repository.put(CommandName.SAX, new SaxParser());
        repository.put(CommandName.StAX, new StaxParser());

    }

    Command getCommand(String name) throws ControllerException {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name);
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new ControllerException("Wrong command");
        }
        return command;
    }
}
