package com.google.travel.pages;

import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResult extends AbstractComponent{

    private TicketType ticketType;
    private TripOption tripOption;

    public SearchResult(WebDriver driver, TripOption tripOption) {
        super(driver);
        this.ticketType = new TicketType(driver);
        this.tripOption = tripOption;
    }

    public String getTicketType(){
        return this.ticketType.getTicketType();
    }

    public List<String> getInputSearchData(){
        return tripOption.getInputSearchData().stream().collect(Collectors.toList());
    }

}
