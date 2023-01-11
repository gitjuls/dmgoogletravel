package com.google.travel.pages;

import com.google.travel.pages.tripOption.ticketType.TicketType;
import com.google.travel.pages.tripOption.TripOption;
import com.google.travel.utilities.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.*;

public class Flights extends BasePage {

    By button = By.xpath("//button[@jsname='vLv7Lb']");

    private TripOption tripOption;
    private TicketType ticketType;
    protected Logger log;

    public Flights(WebDriver driver, Logger log) {
        super(driver);
        this.ticketType = new TicketType(driver);
        this.log = log;
    }

    public void navigate(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void selectTicketType(String ticketType){
        this.tripOption = this.ticketType.selectTicketType(ticketType);
        log.info(this.getClass().getName() + ": Select Ticket Type " + ticketType);
    }

    public void inputSearchData(List<String> searchData){
        tripOption.inputSearchData(searchData);
        log.info(this.getClass().getName() + ": Input Search Data " + searchData.stream().collect(Collectors.toList()));
    }

    public SearchResult clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
        log.info(this.getClass().getName() + ": Click Search Button");
        return new SearchResult(driver, log);
    }
}
