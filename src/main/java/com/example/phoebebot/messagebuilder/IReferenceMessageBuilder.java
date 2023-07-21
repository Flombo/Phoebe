package com.example.phoebebot.messagebuilder;

import com.example.phoebebot.models.IReference;
import discord4j.core.event.domain.interaction.InteractionCreateEvent;
import reactor.core.publisher.Mono;

public interface IReferenceMessageBuilder {

    Mono<Void> buildReferenceMessage(InteractionCreateEvent event, IReference reference);

}
