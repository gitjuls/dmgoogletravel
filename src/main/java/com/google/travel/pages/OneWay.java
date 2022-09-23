package com.google.travel.pages;

import org.openqa.selenium.WebDriver;

public class OneWay extends RoundTrip implements TripOption{

    public OneWay(WebDriver driver) {
        super(driver);
    }
}
