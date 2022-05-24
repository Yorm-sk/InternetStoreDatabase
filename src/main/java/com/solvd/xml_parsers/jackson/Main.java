package com.solvd.xml_parsers.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            Dimas dimas = objectMapper.readValue(new File("src/main/resources/jackson/info.json"), Dimas.class);
            objectMapper.writeValue(new File("src/main/resources/jackson/dimasInfo.json"), dimas);

            List<Dimas> dimasList = List.of(new Dimas(190, 62, new FieldOfDrugs(6 , List.of("weed, poppy"))),
                    new Dimas(141, 76, new FieldOfDrugs(8 , List.of("cannabis, poppy"))),
                    new Dimas(176, 65, new FieldOfDrugs(19 , List.of("cannabis, poppy", "weed"))));
            objectMapper.writeValue(new File("src/main/resources/jackson/dimasList.json"), dimasList);
            List<Dimas> dimasListFromJson = objectMapper.readValue(new File("src/main/resources/jackson/dimasList.json"),
                    new TypeReference<>() {});
            System.out.println(dimasListFromJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
