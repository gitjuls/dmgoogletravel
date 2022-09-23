package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class MultiCity implements TripOption{

    WebDriver driver;
    WebDriverWait wait;

    By addFlightButton = By.xpath("//button[@jsname='htvI8d']//*[contains(text(), 'Add flight')]");
    By button = By.xpath("//button[@jsname='vLv7Lb']");

    @Override
    public void inputSearchDetails(Map<String, String> searchDetail) {
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

    @Override
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        this.wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
    }

}
