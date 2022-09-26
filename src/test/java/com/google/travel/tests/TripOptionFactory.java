package com.google.travel.tests;

import com.google.travel.pages.MultiCity;
import com.google.travel.pages.OneWay;
import com.google.travel.pages.RoundTrip;
import com.google.travel.pages.TripOption;
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
        MAP.put("oneWay", ONE_WAY);
        MAP.put("roundTrip", ROUND_TRIP);
        MAP.put("multiCity", MULTI_CITY);
    }

    public static TripOption get(String option, WebDriver driver){
        return MAP.get(option).apply(driver);
    }
}
