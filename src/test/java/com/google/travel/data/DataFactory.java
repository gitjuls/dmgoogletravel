package com.google.travel.data;

import io.restassured.path.json.JsonPath;
import java.util.HashMap;
import java.util.function.Function;

public class DataFactory {

    private static final Function<JsonPath, String> oneWay = jsonPath -> jsonPath.get("data.oneWay.tripPath");
    private static final Function<JsonPath, String> roundTrip = jsonPath -> jsonPath.get("data.roundTrip.tripPath");
    private static final Function<JsonPath, String> multiCity = jsonPath -> jsonPath.get("data.multiCity.tripPath");

    private static final Function<JsonPath, String> roundTripAlternativeSuggestions = jsonPath -> jsonPath.get("data.roundTrip.negativeData[0].tripPath");
    private static final Function<JsonPath, String> roundTripNoResults = jsonPath -> jsonPath.get("data.roundTrip.negativeData[1].tripPath");

    private static final Function<JsonPath, String> alternativeSuggestionsMessage = jsonPath -> jsonPath.get("data.roundTrip.negativeData[0].message");
    private static final Function<JsonPath, String> noResultsMessage = jsonPath -> jsonPath.get("data.roundTrip.negativeData[1].message");

    private static final HashMap<String, Function<JsonPath, String>> MAP = new HashMap<>();

    static {
        MAP.put("oneWay", oneWay);
        MAP.put("roundTrip", roundTrip);
        MAP.put("multiCity", multiCity);
        MAP.put("rtAltSug", roundTripAlternativeSuggestions);
        MAP.put("rtNoRes", roundTripNoResults);
        MAP.put("altSugMessage", alternativeSuggestionsMessage);
        MAP.put("noResMessage", noResultsMessage);
    }

    public static String getData(String typeTrip, JsonPath jsonPath){
        return MAP.get(typeTrip).apply(jsonPath);
    }

}
