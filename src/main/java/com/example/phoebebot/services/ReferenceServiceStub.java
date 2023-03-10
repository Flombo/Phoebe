package com.example.phoebebot.services;

import com.example.phoebebot.commands.referenceCommands.dtos.IReferenceCommandDTO;
import com.example.phoebebot.models.IReference;
import com.example.phoebebot.models.Platforms;
import com.example.phoebebot.models.Reference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReferenceServiceStub implements IReferenceService {
    @Override
    public IReference retrieveReference(IReferenceCommandDTO referenceCommandDTO) {
        String url = "";

        switch (referenceCommandDTO.getCommandName()) {
            case hand -> {
                if (referenceCommandDTO.getCommandOptions().get(0).equals("female")) {
                    url = "https://quickposes.com/assets/poses/e33bb96ef719c2f13c68ae0f16fdfd08.jpga";
                } else {
                    url = "https://quickposes.com/assets/poses/5a04d949f94cfdccab4724596b18bb7e.jpg";
                }
            }
            case face -> {
                if (referenceCommandDTO.getCommandOptions().get(0).equals("female")) {
                    url = "https://quickposes.com/assets/poses/bd93f71e4faf161f6ab99a8db78edc63.jpg";
                } else {
                    url = "https://quickposes.com/assets/poses/8ada3850a92d10fc15b62ee5fe6cb19d.jpg";
                }
            }
            case animal -> url = "https://quickposes.com/assets/poses/7945d1d47401e315965b22f7a9ec9ca5.jpg";
            case pose -> url = retrievePoseUrl(referenceCommandDTO.getCommandOptions());
            case urban -> url = "https://quickposes.com/assets/poses/0e07aaeec7ae850bf40024db1f6455cd.jpg";
        }


        return new Reference(referenceCommandDTO, Platforms.Quickposes, "https://quickposes.com/en", "https://quickposes.com/apple-touch-icon.png", "Herbert", url, "", 100, 100);
    }

    private String retrievePoseUrl(List<String> options) {
        String url = "";
        String[] femalePoses = new String[] {
                "https://quickposes.com/assets/poses/f245afcdd267a166b28f4e1b87ed030f.jpg",
                "https://quickposes.com/assets/poses/a940dc0cae27c67ae946435e961390fc.jpg"
        };

        String[] malePoses = new String[] {
                "https://quickposes.com/assets/poses/53b2ca65e7759c6168c2600a5f7ef63a.jpg",
                "https://quickposes.com/assets/poses/141b4f0dbc6a7a900633435ccfd934a7.jpg"
        };

        if(options.get(0).equals("male")) {
            switch (options.get(1)) {
                case "all" -> url = malePoses[(int) Math.floor(malePoses.length - 1 * Math.random())];
                case "nude&partiallyNude" -> url = malePoses[1];
                case "clothes&costumes" -> url = malePoses[0];
            }
        } else {
            url = switch (options.get(1)) {
                case "all" -> femalePoses[(int) Math.floor(femalePoses.length - 1 * Math.random())];
                case "nude&partiallyNude" -> femalePoses[1];
                case "clothes&costumes" -> femalePoses[0];
                default -> url;
            };
        }
        return url;
    }

}
