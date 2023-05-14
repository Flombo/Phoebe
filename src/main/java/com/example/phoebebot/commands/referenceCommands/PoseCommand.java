package com.example.phoebebot.commands.referenceCommands;

import com.example.phoebebot.commands.SlashCommand;
import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.dtos.ReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.enums.ReferenceCommands;
import com.example.phoebebot.messagebuilder.IReferenceMessageBuilder;
import com.example.phoebebot.models.IReference;
import com.example.phoebebot.services.IReferenceService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class PoseCommand implements SlashCommand {

    private final IReferenceService referenceService;
    private final ReferenceCommands commandName = ReferenceCommands.pose;
    private final IReferenceMessageBuilder referenceMessageBuilder;

    public PoseCommand(IReferenceService referenceService, IReferenceMessageBuilder referenceMessageBuilder) {
        this.referenceService = referenceService;
        this.referenceMessageBuilder = referenceMessageBuilder;
    }

    @Override
    public String getName() {
        return commandName.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String gender = event.getOption("gender")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get(); //This is warning us that we didn't check if its present, we can ignore this on required options

        String clothing = event.getOption("clothing")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        List<String> options = new ArrayList<>();
        options.add(gender);
        options.add(clothing);

        IReferenceCommandDTO referenceCommandDTO = new ReferenceCommandDTO(commandName, options);
        IReference reference = referenceService.retrieveReference(referenceCommandDTO);

        //Reply to the slash command, with the name the user supplied
        return referenceMessageBuilder.buildReferenceMessage(event, reference);
    }
}
