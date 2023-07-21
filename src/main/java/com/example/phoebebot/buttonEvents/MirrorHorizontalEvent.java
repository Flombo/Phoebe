package com.example.phoebebot.buttonEvents;

import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.dtos.ReferenceCommandDTO;
import com.example.phoebebot.commands.referenceCommands.enums.ReferenceCommands;
import com.example.phoebebot.models.IReference;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class MirrorHorizontalEvent extends ReferenceButtonEventImpl {

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        System.out.println("mirror horizontal");
        List<String> options = new ArrayList<>();
        options.add("female");
        options.add("nude&partiallyNude");
        IReferenceCommandDTO referenceCommandDTO = new ReferenceCommandDTO(ReferenceCommands.pose, options);
        IReference reference = getReferenceService().retrieveReference(referenceCommandDTO);
        return getReferenceMessageBuilder().buildReferenceMessage(event, reference);
    }
}
