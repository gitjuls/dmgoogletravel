package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Map;

public class MultiCity extends AbstractComponent implements TripOption{

    By changeTicketType = By.xpath("//*[@jsname='kj0dLd']//button[@aria-label='Round trip, Change ticket type.']");
    By multiCity = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li[3]");
    By addFlightButton = By.xpath("//button[@jsname='htvI8d']//*[contains(text(), 'Add flight')]");

    public MultiCity(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectTicketType() {
        WebElement changeTicketType = driver.findElement(this.changeTicketType);
        this.wait.until(driver1 -> changeTicketType.isDisplayed());
        changeTicketType.click();

        WebElement multiCity = driver.findElement(this.multiCity);
        this.wait.until(driver1 -> multiCity.isDisplayed());
        multiCity.click();
    }

    @Override
    public void inputSearchParameters(Map<String, String> searchDetail) {
        int size = 2;
        for (int i = 1; i < size; i++) {
            WebElement whereFrom = driver.findElement(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='FDWhSe']//input[@type='text'][@aria-labelledby]"));
            this.wait.until(driver -> whereFrom.isDisplayed());
            whereFrom.clear();
            whereFrom.sendKeys(searchDetail.get("whereFrom" + i));

            WebElement whereTo = driver.findElement(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='iOyk4d']//input[@value][@aria-labelledby]"));
            this.wait.until(driver1 -> whereTo.isDisplayed());
            whereTo.clear();
            whereTo.sendKeys(searchDetail.get("whereTo" + i));
        }
    }

}
