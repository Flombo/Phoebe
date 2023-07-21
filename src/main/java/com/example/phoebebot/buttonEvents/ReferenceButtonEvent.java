package com.example.phoebebot.buttonEvents;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import reactor.core.publisher.Mono;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "customId")
@JsonSubTypes({
    @JsonSubTypes.Type(value = MirrorVerticalEvent.class, name = "MirrorVerticalEvent"),
    @JsonSubTypes.Type(value = MirrorHorizontalEvent.class, name = "MirrorHorizontalEvent"),
    @JsonSubTypes.Type(value = RotateClockwiseEvent.class, name = "RotateClockwiseEvent"),
    @JsonSubTypes.Type(value = RotateCounterClockwiseEvent.class, name = "RotateCounterClockwiseEvent")
})
public interface ReferenceButtonEvent {

    String getName();

    Mono<Void> handle(ButtonInteractionEvent event);

}
