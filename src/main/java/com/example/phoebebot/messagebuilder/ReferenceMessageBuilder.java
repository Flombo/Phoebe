package com.example.phoebebot.messagebuilder;

import com.example.phoebebot.models.IReference;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class ReferenceMessageBuilder implements IReferenceMessageBuilder {
    @Override
    public EmbedCreateSpec buildReferenceMessage(IReference reference) {
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

}
