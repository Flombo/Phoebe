package com.example.phoebebot.messagebuilder;

import com.example.phoebebot.models.IReference;
import discord4j.core.spec.EmbedCreateSpec;

public interface IReferenceMessageBuilder {

    EmbedCreateSpec buildReferenceMessage(IReference reference);

}
