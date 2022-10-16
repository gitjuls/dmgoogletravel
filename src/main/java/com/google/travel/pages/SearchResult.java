package com.google.travel.pages;

import com.google.travel.pages.flightsList.SortByResult;
import com.google.travel.pages.sortBy.SortBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.log4j.*;

public class SearchResult extends BasePageObject {

    private SortBy sortBy;
    private SortByResult flightsList;
    protected Logger log;

    By searchResult = By.xpath("//*[@role='main']//*[@role='alert']");

    public SearchResult(WebDriver driver, Logger log) {
        super(driver);
        this.log = log;
        this.sortBy = new SortBy(driver);
        this.flightsList = new SortByResult(driver);
    }

    public String searchResult(){
        WebElement searchResult = wait.until(ExpectedConditions.presenceOfElementLocated(this.searchResult));
        log.info(this.getClass().getName() + " : " + searchResult.getText());
        return searchResult.getText();
    }

    public void clickSortByButton(){
        sortBy.clickSortByButton();
        log.info(this.getClass().getName() + ": Click SortBy button");
    }
    public void sortBy(String menuItem){
        sortBy.sortByMenuItem(menuItem);
        log.info(this.getClass().getName() + ": SortBy " + menuItem);
    }
    public String getTheFirstFlightPriceFromTheList(){ return flightsList.getTheFirstFlightPriceFromTheList();}
    public String getTheMinFlightPrice(){ return flightsList.getTheMinFlightPrice();}
    public String getTheMinDurationTime(){ return flightsList.getTheMinDurationTime();}
    public String getTheFirstDurationTimeFromTheList(){ return flightsList.getTheFirstDurationTimeFromTheList();}

}
