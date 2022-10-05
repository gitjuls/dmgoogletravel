package com.google.travel.pages;

import com.google.travel.pages.sortBy.SortBy;
import com.google.travel.pages.tripOption.ticketType.TicketType;
import com.google.travel.pages.tripOption.TripOption;
import org.openqa.selenium.WebDriver;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResult extends BasePageObject {

    private TicketType ticketType;
    private TripOption tripOption;
    private SortBy sortBy;

    public SearchResult(WebDriver driver, TripOption tripOption) {
        super(driver);
        this.ticketType = new TicketType(driver);
        this.tripOption = tripOption;
        this.sortBy = new SortBy(driver);
    }

    public String getTicketType(){
        return this.ticketType.getTicketType();
    }
    public List<String> getSearchData(){
        return tripOption.getSearchData().stream().collect(Collectors.toList());
    }
    public void clickSortByButton(){sortBy.clickSortByButton();}
    public void sortBy(String menuItem){sortBy.sortByMenuItem(menuItem);}

}
