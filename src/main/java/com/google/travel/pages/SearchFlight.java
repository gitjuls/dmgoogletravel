package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchFlight extends AbstractComponent{

    By button = By.xpath("//button[@jsname='vLv7Lb']");

    private TripOption tripOption;

    public SearchFlight(WebDriver driver) {
        super(driver);
    }

    public void navigate(){
        driver.get("https://www.google.com/travel/flights");
    }

    public void setTripOption(TripOption tripOption){
        this.tripOption = tripOption;
        tripOption.selectTicketType();
    }

    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
    }
}
