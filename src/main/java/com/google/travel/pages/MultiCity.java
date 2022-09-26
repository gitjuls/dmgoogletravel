package com.google.travel.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        Actions actions = new Actions(driver);

        int size = 2;
        for (int i = 1; i <= size; i++) {
            WebElement whereFrom = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='FDWhSe']//input[@type='text'][@aria-labelledby]")));
            whereFrom.clear();
            actions.sendKeys(whereFrom, searchDetail.get("whereFrom" + i)).build().perform();

            WebElement whereFromAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ searchDetail.get("whereFrom" + i) +"']")));
            actions.moveToElement(whereFromAirportOption).click().build().perform();

            WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='iOyk4d']//input[@value][@aria-labelledby]")));
            whereTo.clear();
            actions.sendKeys(whereTo, searchDetail.get("whereTo" + i)).build().perform();

            WebElement whereToAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ searchDetail.get("whereTo" + i) +"']")));
            actions.moveToElement(whereToAirportOption).click().build().perform();
        }
    }

}
