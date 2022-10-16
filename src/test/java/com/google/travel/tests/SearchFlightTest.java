package com.google.travel.tests;

import com.google.travel.TestBase;
import com.google.travel.pages.*;
import com.google.travel.data.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getPositiveTestData(){
        return new Object[][]{
                {"One way", TestData.getTripData("oneWay")},
                {"Round trip", TestData.getTripData("roundTrip")},
                {"Multi-city", TestData.getTripData("multiCity")}
        };
    }

    @DataProvider
    public Object[][] getNegativeTestData(){
        return new Object[][]{
                {"Round trip", TestData.getTripData("rtAltSug"), TestData.getSearchResultMessage("altSugMessage")},
                {"Round trip", TestData.getTripData("rtNoRes"), TestData.getSearchResultMessage("noResMessage")}
        };
    }

    @Test(dataProvider = "getPositiveTestData")
    public void verifyIfSearchResultsReturned(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertFalse(ExpectedResult.contains("No results"));
    }

    @Test(dataProvider = "getNegativeTestData")
    public void verifyIfSearchResultsReturnedNoResultsOrAlternativeSuggestions(String ticketType, List<String> searchByCode, String expectedAlert) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertTrue(ExpectedResult.equalsIgnoreCase(expectedAlert));
    }

    @Test(dataProvider = "getPositiveTestData")
    public void verifyIfSortedByMinPriceIsMatch(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        searchResult.clickSortByButton();
        searchResult.sortBy("price");
        String firstPriceFromTheSortedList = searchResult.getTheFirstFlightPriceFromTheList();
        String minPriceFromTheList = searchResult.getTheMinFlightPrice();
        Assert.assertEquals(firstPriceFromTheSortedList, minPriceFromTheList);
    }

    @Test(dataProvider = "getPositiveTestData")
    public void verifyIfSortedByMinDurationTimeIsMatch(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        searchResult.clickSortByButton();
        searchResult.sortBy("duration");
        String firstDurationTimeFromTheSortedList = searchResult.getTheFirstDurationTimeFromTheList();
        String minDurationTimeFromTheList = searchResult.getTheMinDurationTime();
        Assert.assertEquals(firstDurationTimeFromTheSortedList, minDurationTimeFromTheList);
    }

}
