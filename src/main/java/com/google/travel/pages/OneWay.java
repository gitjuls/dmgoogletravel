package com.google.travel.pages;

import org.openqa.selenium.WebDriver;

public class OneWay extends RoundTrip implements TripOption{

    private TicketType ticketType;

    public OneWay(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
        ticketType.selectTicketType(2); //oneWay
    }

}
