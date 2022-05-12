package com.solvd.internet_store.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyDBProperties {
    public static String url;
    public static String username;
    public static String password;

    static {
        try {
            FileReader reader = new FileReader("src/main/resources/db.properties");

            Properties p = new Properties();
            p.load(reader);

            url = p.getProperty("db.url");
            username = p.getProperty("db.username");
            password = p.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
