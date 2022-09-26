package com.google.travel.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    public void inputSearchData(Map<String, String> searchData) {
        Actions actions = new Actions(driver);
        String airportDataCode1 = searchData.get("whereFrom");
        String airportDataCode2 = searchData.get("whereTo");

        WebElement whereFrom = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereFrom));
        whereFrom.clear();
        actions.sendKeys(whereFrom,airportDataCode1).build().perform();

        WebElement whereFromAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode1 +"']")));
        actions.moveToElement(whereFromAirportOption).click().build().perform();

        WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereTo));
        whereTo.clear();
        actions.sendKeys(whereTo,airportDataCode2).build().perform();

        WebElement whereToAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode2 +"']")));
        actions.moveToElement(whereToAirportOption).click().build().perform();
    }

}
