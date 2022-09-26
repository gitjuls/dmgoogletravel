package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class RoundTrip extends AbstractComponent implements TripOption{

    By whereFrom = By.xpath("//*[@jsname='snKmsc']//*[@jsname='FDWhSe']//input[@value][@aria-labelledby]");
    By whereTo = By.xpath("//*[@jsname='iOyk4d']//*[@placeholder='Where to?']");
    By changeTicketType = By.xpath("//*[@jsname='kj0dLd']//button[@aria-label='Round trip, Change ticket type.']");
    By roundTrip = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li[1]");

    public RoundTrip(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectTicketType() {
        WebElement changeTicketType = driver.findElement(this.changeTicketType);
        wait.until(driver1 -> changeTicketType.isDisplayed());
        changeTicketType.click();

        WebElement roundTrip = driver.findElement(this.roundTrip);
        wait.until(driver1 -> roundTrip.isDisplayed());
        roundTrip.click();
    }

    @Override
    public void inputSearchDetails(Map<String, String> searchDetail) {
        WebElement whereFrom = driver.findElement(this.whereFrom);
        wait.until(driver1 -> whereFrom.isDisplayed());
        whereFrom.clear();
        whereFrom.sendKeys(searchDetail.get("whereFrom"));

        WebElement whereTo = driver.findElement(this.whereTo);
        wait.until(driver1 -> whereTo.isDisplayed());
        whereTo.clear();
        whereTo.sendKeys(searchDetail.get("whereTo"));
    }

}
