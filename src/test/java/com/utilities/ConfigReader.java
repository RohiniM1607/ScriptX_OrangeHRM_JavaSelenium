package com.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader(String fileName) {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/properties/" + fileName);
            prop = new Properties();
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getData(String key) {
        return prop.getProperty(key);
    }

    // New method — sets value in memory only, does NOT write to file
    public void setData(String key, String value) {
        prop.setProperty(key, value);
    }
}