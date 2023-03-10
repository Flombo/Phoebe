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
public class AnimalCommand implements SlashCommand {

    private final IReferenceService referenceService;
    private final IReferenceMessageBuilder referenceMessageBuilder;
    private final ReferenceCommands commandName = ReferenceCommands.animal;

    public AnimalCommand(IReferenceService referenceService, IReferenceMessageBuilder referenceMessageBuilder) {
        this.referenceService = referenceService;
        this.referenceMessageBuilder = referenceMessageBuilder;
    }

    @Override
    public String getName() {
        return commandName.name();
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        IReferenceCommandDTO referenceCommandDTO = new ReferenceCommandDTO(commandName, new ArrayList<>());
        IReference animalReference = referenceService.retrieveReference(referenceCommandDTO);
        //Reply to the slash command, with the name the user supplied
        return  event.reply()
                .withEphemeral(true)
                .withEmbeds(referenceMessageBuilder.buildReferenceMessage(animalReference));
    }
}
