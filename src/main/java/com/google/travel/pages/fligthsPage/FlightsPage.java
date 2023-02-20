package com.google.travel.pages.fligthsPage;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.commonFeatures.searchFligths.SearchFlightsFeature;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.utilities.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FlightsPage extends BasePage {

    By button = By.xpath("//button[@jsname='vLv7Lb']");

    public SearchFlightsFeature searchFlightsFeature;

    public FlightsPage(WebDriver driver) {
        super(driver);
        this.searchFlightsFeature = new SearchFlightsFeature(driver);
    }

    public void navigate(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
        WebElement body = driver.findElement(By.xpath("//body"));
        body.click();
    }

    public SearchPage clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
        return new SearchPage(driver);
    }
}
