package com.example.discordtestbot.commands.referenceCommands;

import com.example.discordtestbot.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AnimalCommand implements SlashCommand {
    @Override
    public String getName() {
        return "animal";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        //Reply to the slash command, with the name the user supplied
        return  event.reply()
                .withEphemeral(true)
                .withContent("Miau!");
    }
}
