package com.example.phoebebot.messagebuilder;

import com.example.phoebebot.CommandRegistry;
import com.example.phoebebot.models.IReference;
import discord4j.common.JacksonResources;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReferenceMessageBuilder implements IReferenceMessageBuilder {

    private final Logger LOGGER = LoggerFactory.getLogger(CommandRegistry.class);

    @Override
    public Mono<Void> buildReferenceMessage(ChatInputInteractionEvent event, IReference reference) {
        return event.reply()
                .withEphemeral(true)
                .withEmbeds(buildEmbedCreateSpec(reference))
                .withComponents(buildReferenceActionRow());
    }

    private EmbedCreateSpec buildEmbedCreateSpec(IReference reference) {
        return EmbedCreateSpec.builder()
                .color(Color.MAGENTA)
                .title("Reference for command: *" + reference.getUsedCommand().toString() + "*")
                .author(reference.getPlatform().name(), reference.getPlatformMainPage(), reference.getPlatformIconUrl())
                .description("Reference retrieved from " + reference.getPlatformMainPage() + ". All rights reserve to them.")
                .addField("Owner", reference.getAuthor(), true)
                .addField("Resource link", reference.getUrl(), true)
                .image(reference.getUrl())
                .timestamp(Instant.now())
                .footer("Copyright:", "https://quickposes.com/apple-touch-icon.png")
                .build();
    }

    private ActionRow buildReferenceActionRow() {

        List<Button> buttons = new ArrayList<>();

        try {
            final PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
            final JacksonResources d4jMapper = JacksonResources.create();

            for (Resource resource : pathResolver.getResources("referenceButtons/*.json")) {
                ReferenceButton referenceButton = d4jMapper.getObjectMapper()
                        .readValue(resource.getInputStream(), ReferenceButton.class);
                buttons.add(referenceButton.getButton());
            }
        } catch (IOException exception) {
            LOGGER.warn("Failed to initialize reference buttons due to: " + exception.getMessage());
        }

        return ActionRow.of(buttons);
    }

}
