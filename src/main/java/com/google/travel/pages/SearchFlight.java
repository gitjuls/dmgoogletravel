package com.google.travel.pages;

import com.google.travel.pages.tripOption.ticketType.TicketType;
import com.google.travel.pages.tripOption.TripOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SearchFlight extends BasePageObject {

    By button = By.xpath("//button[@jsname='vLv7Lb']");

    private TripOption tripOption;
    private TicketType ticketType;

    public SearchFlight(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
    }

    public void navigate(){
        driver.get("https://www.google.com/travel/flights");
    }

    public void selectTicketType(String ticketType){
        this.tripOption = this.ticketType.selectTicketType(ticketType);
    }

    public void inputSearchData(List<String> searchData){
        tripOption.inputSearchData(searchData);
    }

    public SearchResult clickSearchButton() {
        WebElement searchButton = driver.findElement(button);
        wait.until(driver1 -> searchButton.isDisplayed());
        searchButton.click();
        return new SearchResult(driver, tripOption);
    }
}
