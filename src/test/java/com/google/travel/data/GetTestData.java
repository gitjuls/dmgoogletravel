package com.google.travel.data;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GetTestData {
    private static JsonPath jsonPath;

    private static void setFileToRead(){
        File file = new File(
                GetTestData.class.getClassLoader().getResource("testData.json").getFile()
        );

        byte[] bytes = readAllBytes(file);
        String responseInBytes = new String(bytes);
        jsonPath = new JsonPath(responseInBytes).using(new JsonPathConfig("UTF-8"));
        jsonPath.get("data");
    }

    private static byte[] readAllBytes(File file){
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> splitPath(String str){
        String[] splitPath = str.split("=>");
        List<String> list = Arrays.stream(splitPath)
                .map(s -> s.trim())
                .collect(Collectors.toList());
        return list;
    }

    public static List<String> getTripData(String tripType){
        setFileToRead();
        String tripPath = DataFactory.getData(tripType, jsonPath);
        List<String> list = splitPath(tripPath);
        return list;
    }

    public static String getSearchResultMessage(String tripType){
        setFileToRead();
        String message = DataFactory.getData(tripType, jsonPath);
        return message;
    }




}
