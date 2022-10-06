package com.google.travel.tests;

import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class SearchFlightAction {

    private static final Consumer<SearchFlight> oneWay = searchFlight -> searchFlight.selectTicketType("One way");
    private static final Consumer<SearchFlight> roundTrip = searchFlight -> searchFlight.selectTicketType("Round trip");
    private static final Consumer<SearchFlight> multiCity = searchFlight -> searchFlight.selectTicketType("Multi-city");

    private static final Consumer<SearchFlight>  oneWayData = searchFlight -> searchFlight.inputSearchData(dataProvider("oneWayData"));
    private static final Consumer<SearchFlight>  roundTripData = searchFlight -> searchFlight.inputSearchData(dataProvider("roundTrip"));
    private static final Consumer<SearchFlight>  multiCityData = searchFlight -> searchFlight.inputSearchData(dataProvider("multiCityData"));

    public static final Consumer<SearchFlight> selectOneWayTripAndInputData = oneWay.andThen(oneWayData);
    public static final Consumer<SearchFlight> clickSearchButton = searchFlight -> searchFlight.clickSearchButton();

    public static final Consumer<SearchResult> getTicketType = searchResult -> searchResult.getTicketType();
    public static final Consumer<SearchResult> getSearchData = searchResult -> searchResult.getSearchData();

    private static final Consumer<SearchResult> clickSortByButton = searchResult -> searchResult.clickSortByButton();
    private static final Consumer<SearchResult> sortByPrice = searchResult -> searchResult.sortBy("price");

    public static final Consumer<SearchResult> selectSortByPrice = clickSortByButton.andThen(sortByPrice);

    public static final Consumer<SearchResult> assertMinPrice = searchResult -> Assert.assertEquals(searchResult.getTheFirstFlightPriceFromTheList(), searchResult.getTheMinFlightPrice());

    public static List<String> dataProvider(String str){
        List<String> oneWayData = new ArrayList<>();
        Collections.addAll(oneWayData, "DCA", "TPA");
        return oneWayData;
    }
}
