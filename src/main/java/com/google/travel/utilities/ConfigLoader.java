package com.google.travel.utilities;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String userDir = System.getProperty("user.dir");
        String pathToFile = userDir.concat("/src/test/resources/properties/config.properties");
        properties = PropertyUtils.propertyLoader(pathToFile);
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the config.properties file");
    }
}
