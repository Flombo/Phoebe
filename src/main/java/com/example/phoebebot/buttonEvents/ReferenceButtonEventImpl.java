package com.example.phoebebot.buttonEvents;

import com.example.phoebebot.messagebuilder.IReferenceMessageBuilder;
import com.example.phoebebot.messagebuilder.ReferenceMessageBuilder;
import com.example.phoebebot.services.IReferenceService;
import com.example.phoebebot.services.ReferenceServiceStub;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import reactor.core.publisher.Mono;

public class ReferenceButtonEventImpl implements ReferenceButtonEvent {

    protected IReferenceService referenceService;

    protected IReferenceMessageBuilder referenceMessageBuilder;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Mono<Void> handle(ButtonInteractionEvent event) {
        return null;
    }

    protected IReferenceMessageBuilder getReferenceMessageBuilder() {
        if(referenceMessageBuilder == null) {
            referenceMessageBuilder = new ReferenceMessageBuilder();
        }
        return referenceMessageBuilder;
    }

    protected IReferenceService getReferenceService() {
        if(referenceService == null) {
            referenceService = new ReferenceServiceStub();
        }
        return referenceService;
    }

}
