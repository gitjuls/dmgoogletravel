package com.google.travel.pages.searchPage;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.commonFeatures.searchFligths.SearchFlightsFeature;
import com.google.travel.pages.searchPage.sortByFeature.SortByFeature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends BasePage {

    By searchResult = By.xpath("//*[@role='main']//*[@role='alert']");

    public SearchFlightsFeature searchFlightsFeature;
    public SortByFeature sortByFeature;

    public SearchPage(WebDriver driver) {
        super(driver);
        this.searchFlightsFeature = new SearchFlightsFeature(driver);
        this.sortByFeature = new SortByFeature(driver);
    }

    public String searchResult(){
        WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(this.searchResult));
        return searchResult.getText();
    }

}
