package com.example.phoebebot.messagebuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import discord4j.core.object.component.Button;
import discord4j.core.object.reaction.ReactionEmoji;

public class ReferenceButton {
    @JsonProperty("unicode")
    private String unicode;
    @JsonProperty("customId")
    private String customId;

    public Button getButton() {
        return Button.secondary(customId, ReactionEmoji.unicode(unicode));
    }

}
