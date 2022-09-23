package com.google.travel.pages;

import org.openqa.selenium.WebDriver;

public class FlightsPage {

    private final WebDriver driver;

    public FlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(){
        driver.get("https://www.google.com/travel/flights/");
    }
}
