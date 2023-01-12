package com.google.travel.pages.fligths.searchResult;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.fligths.searchResult.sortByOption.Duration;
import com.google.travel.pages.fligths.searchResult.sortByOption.Price;
import com.google.travel.pages.fligths.searchResult.sortBy.SortBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.apache.log4j.*;

public class SearchResult extends BasePage {

    private SortBy sortBy;
    private Price price;
    private Duration duration;
    protected Logger log;

    By searchResult = By.xpath("//*[@role='main']//*[@role='alert']");

    public SearchResult(WebDriver driver, Logger log) {
        super(driver);
        this.log = log;
        this.sortBy = new SortBy(driver);
        this.price = new Price(driver);
        this.duration = new Duration(driver);
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
    public String getTheFirstFlightPriceFromTheList(){ return price.getTheFirstFlightPriceFromTheList();}
    public String getTheMinFlightPrice(){ return price.getTheMinFlightPrice();}
    public String getTheMinDurationTime(){ return duration.getTheMinDurationTime();}
    public String getTheFirstDurationTimeFromTheList(){ return duration.getTheFirstDurationTimeFromTheList();}

}
