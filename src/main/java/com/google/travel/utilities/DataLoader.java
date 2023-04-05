package com.google.travel.utilities;

import com.google.travel.factory.TicketTypeDataFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;
    private static String fileName;

    private DataLoader(){
        String userDir = System.getProperty("user.dir");
        String pathToFile = userDir.concat("/src/test/resources/data/"+fileName+".properties");
        properties = PropertyUtils.propertyLoader(pathToFile);
    }

    public static DataLoader getInstance(String fn){
        if(dataLoader == null){
            fileName = fn;
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    public List<String> getData(String tripType){
        String prop = properties.getProperty(tripType);
        if(prop != null) {
            List<String> list = splitPath(prop);
            return list;
        } else throw new RuntimeException("property "+tripType+" is not specified in the searchTestPositiveData.properties file");
    }


    private static List<String> splitPath(String str){
        String[] splitPath = str.split("=>");
        List<String> list = Arrays.stream(splitPath)
                .map(s -> s.trim())
                .collect(Collectors.toList());
        return list;
    }
}
