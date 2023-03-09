package com.example.discordtestbot.configurations;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class BotConfiguration {

    private final Environment env;

    public BotConfiguration(Environment environment) {
        env = environment;
    }

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        String token = env.getProperty("token");
        GatewayDiscordClient client = null;
        if(token != null) {
            client = DiscordClientBuilder.create(token).build()
                    .gateway()
                    .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.listening("to /commands")))
                    .login()
                    .block();
        }
        return client;
    }

    @Bean
    public RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }

}
