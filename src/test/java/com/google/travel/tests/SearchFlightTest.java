package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
        Collections.addAll(roundTripData, "DCA", "LHR");

        List<String> roundTripDataNoResults = new ArrayList<>();
        Collections.addAll(roundTripDataNoResults, "DCA", "KBP");

        List<String> multiCityData = new ArrayList<>();
        Collections.addAll(multiCityData, "DCA", "LAX", "LAX", "TPA", "TPA", "DCA");

        return new Object[][]{
                {"One way", oneWayData, "results returned."},
                {"Round trip", roundTripData, "results returned."},
                {"Round trip", roundTripDataNoResults, "No results returned."},
                {"Multi-city", multiCityData, "results returned."}
        };
    }

    @Test(dataProvider = "getData")
    public void verifySearchResult(String ticketType, List<String> searchByCode, String expectedResult) {
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertTrue(ExpectedResult.contains(expectedResult));
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
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
