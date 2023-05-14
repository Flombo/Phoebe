package com.example.phoebebot.messagebuilder;

import com.example.phoebebot.models.IReference;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import reactor.core.publisher.Mono;

public interface IReferenceMessageBuilder {

    Mono<Void> buildReferenceMessage(ChatInputInteractionEvent event, IReference reference);

}
