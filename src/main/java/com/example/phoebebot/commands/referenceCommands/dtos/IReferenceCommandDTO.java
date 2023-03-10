package com.example.phoebebot.commands.referenceCommands.dtos;

import com.example.phoebebot.commands.referenceCommands.enums.ReferenceCommands;

import java.util.List;

public interface IReferenceCommandDTO {

    ReferenceCommands getCommandName();

    List<String> getCommandOptions();

}
