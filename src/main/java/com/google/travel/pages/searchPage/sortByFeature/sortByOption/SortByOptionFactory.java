package com.google.travel.pages.searchPage.sortByFeature.sortByOption;

import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SortByOptionFactory {
    private static final Function<WebDriver, SortByInterface> DURATION = (driver) -> new Duration(driver);
    private static final Function<WebDriver, SortByInterface> PRICE = (driver) -> new Price(driver);

    private static final Map<String,Function<WebDriver, SortByInterface>> MAP = new HashMap<>();

    static {
        MAP.put("duration", DURATION);
        MAP.put("price", PRICE);
    }

    public static SortByInterface get(String option, WebDriver driver){
        return MAP.get(option).apply(driver);
    }
}
