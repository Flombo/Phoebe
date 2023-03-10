package com.example.phoebebot.commands.referenceCommands.dtos;

import com.example.phoebebot.commands.referenceCommands.enums.ReferenceCommands;

import java.util.List;

public class ReferenceCommandDTO implements IReferenceCommandDTO {

    private final ReferenceCommands commandName;
    private final List<String> commandOptions;

    public ReferenceCommandDTO(ReferenceCommands commandName, List<String> commandOptions) {
        this.commandName = commandName;
        this.commandOptions = commandOptions;
    }

    @Override
    public ReferenceCommands getCommandName() {
        return commandName;
    }

    @Override
    public List<String> getCommandOptions() {
        return commandOptions;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(commandName.name());
        commandOptions.forEach(option -> stringBuilder.append(" ").append(option));
        return stringBuilder.toString();
    }
}
