package com.google.travel.pages;

import org.openqa.selenium.WebDriver;
import java.util.List;

public class OneWay extends RoundTrip implements TripOption{

    private TicketType ticketType;

    public OneWay(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
        ticketType.selectTicketType(2); //oneWay
    }

    @Override
    public void inputSearchData(List<String> searchData) {
        super.inputSearchData(searchData);
    }

}
