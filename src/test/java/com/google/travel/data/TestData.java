package com.google.travel.data;

import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class TestData {

    private String getJsonData()throws IOException {
        File file = new File(
                this.getClass().getClassLoader().getResource("testData.json").getFile()
        );

        byte[] bytes = Files.readAllBytes(file.toPath());
        String str = new String(bytes);
        return str;
    }

    public static List<Object> getOneWayData (String typeOfData){
        String str = null;
        JsonPath jsonPath = new JsonPath(str).using(new JsonPathConfig("UTF-8"));
        jsonPath.get("data");
        List<Object> list =jsonPath.getList("data.tripOption." + typeOfData + ".collect{it.value}");
        list.stream().forEach(System.out::println);
        return list;
    }

}
