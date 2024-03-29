package com.example.phoebebot;

import discord4j.common.JacksonResources;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandRegistry implements ApplicationRunner {

    private final RestClient client;

    private final Logger LOGGER = LoggerFactory.getLogger(CommandRegistry.class);

    //Use the rest client provided by our Bean
    public CommandRegistry(RestClient client) {
        this.client = client;
    }

    //This method will run only once on each start up and is automatically called with Spring so blocking is okay.
    @Override
    public void run(ApplicationArguments args) throws IOException {
        //Create an ObjectMapper that supported Discord4J classes
        final JacksonResources d4jMapper = JacksonResources.create();

        // Convenience variables for the sake of easier to read code below.
        PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = client.getApplicationService();

        try {
            final long applicationId = client.getApplicationId().block();

            //Get our commands json from resources as command data
            List<ApplicationCommandRequest> commands = new ArrayList<>();

            for (Resource resource : pathResolver.getResources("commands/*.json")) {
                ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                        .readValue(resource.getInputStream(), ApplicationCommandRequest.class);
                commands.add(request);
            }

        /* Bulk overwrite commands. This is now idempotent, so it is safe to use this even when only 1 command
        is changed/added/removed
        */
            applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                    .doOnNext(ignore -> LOGGER.info("Successfully registered Global Commands"))
                    .doOnError(e -> LOGGER.warn("Failed to register global commands"))
                    .subscribe();
        } catch (NullPointerException exception) {
            LOGGER.warn("applicationID of client is null");
        }
        }
}