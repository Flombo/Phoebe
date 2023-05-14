package com.example.phoebebot.commands.referenceCommands;

import com.example.phoebebot.commands.SlashCommand;
import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.dtos.ReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.enums.ReferenceCommands;
import com.example.phoebebot.messagebuilder.IReferenceMessageBuilder;
import com.example.phoebebot.models.IReference;
import com.example.phoebebot.services.IReferenceService;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
public class UrbanCommand implements SlashCommand {

    private final ReferenceCommands commandName = ReferenceCommands.urban;
    private final IReferenceService referenceService;
    private final IReferenceMessageBuilder referenceMessageBuilder;

    public UrbanCommand(IReferenceService referenceService, IReferenceMessageBuilder referenceMessageBuilder) {
        this.referenceService = referenceService;
        this.referenceMessageBuilder = referenceMessageBuilder;
    }

    @Override
    public String getName() {
        return ReferenceCommands.urban.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        //Reply to the slash command, with the name the user supplied
        IReferenceCommandDTO referenceCommandDTO = new ReferenceCommandDTO(this.commandName, new ArrayList<>());
        IReference reference = referenceService.retrieveReference(referenceCommandDTO);

        return referenceMessageBuilder.buildReferenceMessage(event, reference);
    }
}
