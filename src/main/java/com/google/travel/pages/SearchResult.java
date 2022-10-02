package com.google.travel.pages;

import org.openqa.selenium.WebDriver;

public class SearchResult extends AbstractComponent{

    private TicketType ticketType;

    public SearchResult(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
    }

    public void getTripOption(){
        String tripOption = this.ticketType.getTicketType();
        System.out.println(tripOption);
    }
}
