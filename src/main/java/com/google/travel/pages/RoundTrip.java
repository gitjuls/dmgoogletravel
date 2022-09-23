package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Map;

public class RoundTrip extends AbstractComponent implements TripOption{

    By whereFrom = By.xpath("//*[@jsname='snKmsc']//*[@jsname='FDWhSe']//input[@value][@aria-labelledby]");
    By whereTo = By.xpath("//*[@jsname='iOyk4d']//*[@placeholder='Where to?']");
    By button = By.xpath("//button[@jsname='vLv7Lb']");

    public RoundTrip(WebDriver driver) {
        super(driver);
    }

    @Override
    public void inputSearchDetails(Map<String, String> searchDetail) {
        WebElement whereFrom = driver.findElement(this.whereFrom);
        wait.until(driver -> whereFrom.isDisplayed());
        whereFrom.clear();
        whereFrom.sendKeys(searchDetail.get("whereFrom"));

        WebElement whereTo = driver.findElement(this.whereTo);
        wait.until(driver -> whereTo.isDisplayed());
        whereTo.clear();
        whereTo.sendKeys(searchDetail.get("whereTo"));
    }

    @Override
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
    }

}
