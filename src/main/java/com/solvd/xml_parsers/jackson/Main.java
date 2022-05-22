package com.solvd.xml_parsers.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Dimas dimas = objectMapper.readValue(new File("src/main/resources/jackson/info.json"), Dimas.class);
            objectMapper.writeValue(new File("src/main/resources/jackson/dimasInfo.json"), dimas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
