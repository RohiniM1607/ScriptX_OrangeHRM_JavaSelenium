package com.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties properties;

    public ConfigReader(String fileName) {

        properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream("src/test/resources/properties/" + fileName);
            properties.load(fis);
            fis.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load property file: " + fileName);
        }
    }

    public String getData(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            return "";
        }
        return value.trim();
    }
}