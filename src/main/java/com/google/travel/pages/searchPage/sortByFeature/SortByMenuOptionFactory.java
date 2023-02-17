package com.google.travel.pages.searchPage.sortByFeature;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SortByMenuOptionFactory {

    private static Predicate<WebElement> bestFlights = element -> element.getText().equalsIgnoreCase("Best Flights");
    private static Predicate<WebElement> price = element -> element.getText().equalsIgnoreCase("Price");
    private static Predicate<WebElement> departureTime = element -> element.getText().equalsIgnoreCase("Departure time");
    private static Predicate<WebElement> arrivalTime = element -> element.getText().equalsIgnoreCase("Arrival time");
    private static Predicate<WebElement> duration = element -> element.getText().equalsIgnoreCase("Duration");
    private static Predicate<WebElement> emissions = element -> element.getText().equalsIgnoreCase("Emissions");

    private static final Map<String, Predicate<WebElement>> MAP = new HashMap<>();
    static {
        MAP.put("best flights", bestFlights);
        MAP.put("price", price);
        MAP.put("departure time", departureTime);
        MAP.put("arrival time", arrivalTime);
        MAP.put("duration", duration);
        MAP.put("emissions", emissions);
    }

    public static Predicate<WebElement> selectMenuItem(String type){
        return MAP.get(type);
    }
}
