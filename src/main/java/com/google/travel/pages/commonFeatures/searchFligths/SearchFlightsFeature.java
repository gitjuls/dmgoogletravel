package com.google.travel.pages.commonFeatures.searchFligths;

import com.google.travel.pages.commonFeatures.searchFligths.tripOption.TripOption;
import com.google.travel.pages.commonFeatures.searchFligths.tripOption.ticketType.TicketType;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class SearchFlightsFeature {

    private TripOption tripOption;
    private TicketType ticketType;

    public SearchFlightsFeature(WebDriver driver) {
        this.ticketType = new TicketType(driver);
    }

    public void selectTicketType(String ticketType){
        this.tripOption = this.ticketType.selectTicketType(ticketType);
    }

    public void inputSearchData(List<String> searchData){
        tripOption.inputSearchData(searchData);
    }

}
