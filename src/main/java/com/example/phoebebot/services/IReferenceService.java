package com.example.phoebebot.services;

import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;
import com.example.phoebebot.models.IReference;

public interface IReferenceService {

    IReference retrieveReference(IReferenceCommandDTO referenceCommandDTO);

    IReferenceService mirrorVertically(IReferenceCommandDTO referenceCommandDTO, String url);

}
