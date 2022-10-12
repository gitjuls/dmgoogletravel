package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        List<String> oneWayData = new ArrayList<>();
        Collections.addAll(oneWayData, "DCA", "TPA");

        List<String> roundTripData = new ArrayList<>();
        Collections.addAll(roundTripData, "DCA", "TLV");

        List<String> multiCityData = new ArrayList<>();
        Collections.addAll(multiCityData, "DCA", "LAX", "LAX", "TPA", "TPA", "DCA");

        return new Object[][]{
                {"One way", oneWayData},
                {"Round trip", roundTripData},
                {"Multi-city", multiCityData}
        };
    }

    @DataProvider
    public Object[][] getData2(){
        List<String> roundTripDataAlternativeSuggestionsFound = new ArrayList<>();
        Collections.addAll(roundTripDataAlternativeSuggestionsFound, "DCA", "ISL");

        List<String> roundTripDataNoResults = new ArrayList<>();
        Collections.addAll(roundTripDataNoResults, "DCA", "KBP");

        return new Object[][]{
                {"Round trip", roundTripDataAlternativeSuggestionsFound, "No results returned, alternative suggestions found."},
                {"Round trip", roundTripDataNoResults, "No results returned."}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturned(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertFalse(ExpectedResult.equalsIgnoreCase("No results"));
    }

    @Test(dataProvider = "getData2")
    public void verifyIfSearchResultsReturnedNoResultsOrAlternativeSuggestions(String ticketType, List<String> searchByCode, String expectedAlert) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertTrue(ExpectedResult.equalsIgnoreCase(expectedAlert));
    }

    @Test(dataProvider = "getData")
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

    @Test(dataProvider = "getData")
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
