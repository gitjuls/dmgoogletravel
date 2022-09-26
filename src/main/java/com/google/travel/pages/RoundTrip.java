package com.google.travel.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RoundTrip extends AbstractComponent implements TripOption{

    By whereFrom = By.xpath("//*[@jsname='snKmsc']//*[@jsname='FDWhSe']//input[@value][@aria-labelledby]");
    By whereTo = By.xpath("//*[@jsname='iOyk4d']//*[@placeholder='Where to?']");
    By changeTicketType = By.xpath("//*[@jsname='kj0dLd']//button[@aria-label='Round trip, Change ticket type.']");
    By roundTrip = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li[1]");
    By fromAirport = By.xpath("//ul[@role='listbox']/li[@data-code = 'DCA']");
    By toAirport = By.xpath("//ul[@role='listbox']/li[@data-code = 'TPA']");


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
    public void inputSearchParameters(Map<String, String> searchDetail) {
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(this.whereFrom));
        WebElement whereFrom = driver.findElement(this.whereFrom);
        whereFrom.clear();
        actions.sendKeys(whereFrom,searchDetail.get("whereFrom")).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(this.fromAirport));
        WebElement fromAirport = driver.findElement(this.fromAirport);
        actions.moveToElement(fromAirport).click().build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(this.whereTo));
        WebElement whereTo = driver.findElement(this.whereTo);
        whereTo.clear();
        actions.sendKeys(whereTo,searchDetail.get("whereTo")).build().perform();

        wait.until(ExpectedConditions.presenceOfElementLocated(this.toAirport));
        WebElement toAirport = driver.findElement(this.toAirport);
        actions.moveToElement(toAirport).click().build().perform();
    }

}
