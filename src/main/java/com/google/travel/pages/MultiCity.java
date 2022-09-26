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
    By addFlight = By.xpath("//button[@jsname='htvI8d']//*[contains(text(), 'Add flight')]");

    public MultiCity(WebDriver driver) {
        super(driver);
    }

    @Override
    public void selectTicketType() {
        WebElement changeTicketType = driver.findElement(this.changeTicketType);
        wait.until(driver1 -> changeTicketType.isDisplayed());
        changeTicketType.click();

        WebElement multiCity = driver.findElement(this.multiCity);
        wait.until(driver1 -> multiCity.isDisplayed());
        multiCity.click();
    }

    @Override
    public void inputSearchData(Map<String, String> searchData) {
        WebElement addFlight = driver.findElement(this.addFlight);
        wait.until(driver1 -> addFlight.isDisplayed());

        Actions actions = new Actions(driver);
        int rowSize = searchData.size()/2;

        for (int i = 1; i <= rowSize; i++) {
            WebElement whereFrom = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='FDWhSe']//input[@type='text'][@aria-labelledby]")));
            whereFrom.clear();
            actions.sendKeys(whereFrom, searchData.get("whereFrom" + i)).build().perform();

            WebElement whereFromAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ searchData.get("whereFrom" + i) +"']")));
            actions.moveToElement(whereFromAirportOption).click().build().perform();

            WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@jsname='MOPQS']/div[" + i + "]//*[@jsname='iOyk4d']//input[@value][@aria-labelledby]")));
            whereTo.clear();
            actions.sendKeys(whereTo, searchData.get("whereTo" + i)).build().perform();

            WebElement whereToAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ searchData.get("whereTo" + i) +"']")));
            actions.moveToElement(whereToAirportOption).click().build().perform();

            //if rows with input fields equal 2 or more (2 by default)
            //and rows don't equal the actual rowSize of searchData
            //click addFlight button to add one more row for input searchData
            if(i >= 2 && i != rowSize){
                addFlight.click();
            }
        }
    }

}
