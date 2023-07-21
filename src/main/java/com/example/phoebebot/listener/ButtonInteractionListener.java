package com.example.phoebebot.listener;

import com.example.phoebebot.buttonEvents.ReferenceButtonEvent;
import discord4j.common.JacksonResources;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class ButtonInteractionListener {

    private final Collection<ReferenceButtonEvent> referenceButtons;

    public ButtonInteractionListener(GatewayDiscordClient client) {

        referenceButtons = new ArrayList<>();

        try {
            final PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
            final JacksonResources d4jMapper = JacksonResources.create();

            for (Resource resource : pathResolver.getResources("referenceButtons/*.json")) {
                ReferenceButtonEvent referenceButton = d4jMapper.getObjectMapper()
                        .readValue(resource.getInputStream(), ReferenceButtonEvent.class);
                referenceButtons.add(referenceButton);
            }
        } catch (IOException exception) {
            Logger LOGGER = LoggerFactory.getLogger(ButtonInteractionListener.class);
            LOGGER.warn("Failed to initialize reference buttons due to: " + exception.getMessage());
        }

        client.on(ButtonInteractionEvent.class, this::handle).subscribe();
    }


    public Mono<Void> handle(ButtonInteractionEvent event) {
        //Convert our list to a flux that we can iterate through
        return Flux.fromIterable(referenceButtons)
                //Filter out all referenceButtons that don't match the customId this event is for
                .filter(buttonEvent -> buttonEvent.getName().equals(event.getCustomId()))
                //Get the first (and only) item in the flux that matches our filter
                .next()
                //Have our command class handle all logic related to its specific command.
                .flatMap(buttonEvent -> buttonEvent.handle(event));

    }

}
