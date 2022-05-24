package com.solvd.xml_parsers.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        try {
            Person person = objectMapper.readValue(new File("src/main/resources/jackson/info.json"), Person.class);
            objectMapper.writeValue(new File("src/main/resources/jackson/personInfo.json"), person);

            List<Person> personList = List.of(new Person("Dima", 190, 62, new FieldOfDrugs(6 , List.of("weed, poppy"))),
                    new Person("Artur", 141, 76, new FieldOfDrugs(8 , List.of("cannabis, poppy"))),
                    new Person("Lera", 176, 65, new FieldOfDrugs(19 , List.of("cannabis, poppy", "weed"))));
            objectMapper.writeValue(new File("src/main/resources/jackson/personList.json"), personList);
            List<Person> personListFromJson = objectMapper.readValue(new File("src/main/resources/jackson/personList.json"),
                    new TypeReference<>() {});
            LOGGER.info(personListFromJson);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
