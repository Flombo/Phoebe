package com.example.discordtestbot.commands.referenceCommands;

import com.example.discordtestbot.commands.SlashCommand;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PoseCommand implements SlashCommand {

    @Override
    public String getName() {
        return "pose";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        String gender = event.getOption("gender")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get(); //This is warning us that we didn't check if its present, we can ignore this on required options

        String clothing = event.getOption("clothing")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .get();

        //Reply to the slash command, with the name the user supplied
        return  event.reply()
                .withEphemeral(true)
                .withContent("Gender: " + gender + " / Clothing: " + clothing);
    }
}
