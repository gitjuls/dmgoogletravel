package com.google.travel.pages;

import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TripOptionFactory {

    private static final Function<WebDriver, TripOption> ONE_WAY = (driver) -> new OneWay(driver);
    private static final Function<WebDriver, TripOption> ROUND_TRIP = (driver) -> new RoundTrip(driver);
    private static final Function<WebDriver, TripOption> MULTI_CITY = (driver) -> new MultiCity(driver);

    private static final Map<String,Function<WebDriver, TripOption>> MAP = new HashMap<>();

    static {
        MAP.put("One way", ONE_WAY);
        MAP.put("Round trip", ROUND_TRIP);
        MAP.put("Multi-city", MULTI_CITY);
    }

    public static TripOption get(String option, WebDriver driver){
        return MAP.get(option).apply(driver);
    }
}
