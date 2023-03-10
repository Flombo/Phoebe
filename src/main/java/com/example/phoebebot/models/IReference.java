package com.example.phoebebot.models;

import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;

public interface IReference {

    IReferenceCommandDTO getUsedCommand();

    String getPlatformIconUrl();

    String getPlatformMainPage();

    Platforms getPlatform();

    String getAuthor();

    String getUrl();

}
