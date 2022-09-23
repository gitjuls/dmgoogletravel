package com.google.travel.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchFlight {

    private WebDriver driver;
    private TripOption tripOption;

    public SearchFlight(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate(){
        driver.get("https://www.google.com/travel/flights");
    }

    public void setTripOption(TripOption tripOption){
        this.tripOption = tripOption;
        PageFactory.initElements(driver, this.tripOption);
    }

    public void clickBt(){
        tripOption.clickSearchButton();
    }
}
