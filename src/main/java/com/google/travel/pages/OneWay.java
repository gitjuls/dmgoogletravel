package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OneWay extends RoundTrip implements TripOption{

    By changeTicketType = By.xpath("//*[@jsname='kj0dLd']//button[@aria-label='Round trip, Change ticket type.']");
    By oneWay = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li[2]");

    public OneWay(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectTicketType() {
        WebElement changeTicketType = driver.findElement(this.changeTicketType);
        wait.until(driver1 -> changeTicketType.isDisplayed());
        changeTicketType.click();

        WebElement oneWay = driver.findElement(this.oneWay);
        wait.until(driver1 -> oneWay.isDisplayed());
        oneWay.click();
    }

}
