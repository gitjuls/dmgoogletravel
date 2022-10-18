package com.google.travel.pages.sortBy;

import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SortByOptionFactory {

    private static Predicate<WebElement> bestFlights = element -> element.getAttribute("data-value").equalsIgnoreCase("1");
    private static Predicate<WebElement> price = element -> element.getAttribute("data-value").equalsIgnoreCase("2");
    private static Predicate<WebElement> departureTime = element -> element.getAttribute("data-value").equalsIgnoreCase("3");
    private static Predicate<WebElement> arrivalTime = element -> element.getAttribute("data-value").equalsIgnoreCase("4");
    private static Predicate<WebElement> duration = element -> element.getAttribute("data-value").equalsIgnoreCase("5");
    private static Predicate<WebElement> emissions = element -> element.getAttribute("data-value").equalsIgnoreCase("6");

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
