package com.google.travel.tests;

import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class SearchFlightAction {

    public static final Consumer<SearchFlight> oneWay = searchFlight -> searchFlight.selectTicketType("One way");
    public static final Consumer<SearchFlight> roundTrip = searchFlight -> searchFlight.selectTicketType("Round trip");
    public static final Consumer<SearchFlight> multiCity = searchFlight -> searchFlight.selectTicketType("Multi-city");

    public static final Consumer<SearchFlight>  oneWayData = searchFlight -> searchFlight.inputSearchData(dataProvider("oneWayData"));
    public static final Consumer<SearchFlight>  roundTripData = searchFlight -> searchFlight.inputSearchData(dataProvider("roundTrip"));
    public static final Consumer<SearchFlight>  multiCityData = searchFlight -> searchFlight.inputSearchData(dataProvider("multiCityData"));
    public static final Consumer<SearchFlight> clickSearchButton = searchFlight -> searchFlight.clickSearchButton();

    public static final Consumer<SearchResult> getTicketType = searchResult -> searchResult.getTicketType();
    public static final Consumer<SearchResult> getSearchData = searchResult -> searchResult.getSearchData();
    public static final Consumer<SearchResult> clickSortByButton = searchResult -> searchResult.clickSortByButton();
    public static final Consumer<SearchResult> sortByPrice = searchResult -> searchResult.sortBy("price");
    public static final Consumer<SearchResult> assertMinPrice = searchResult -> Assert.assertEquals(searchResult.getTheFirstFlightPriceFromTheList(), searchResult.getTheMinFlightPrice());

    public static List<String> dataProvider(String str){
        List<String> oneWayData = new ArrayList<>();
        Collections.addAll(oneWayData, "DCA", "TPA");
        return oneWayData;
    }
}
