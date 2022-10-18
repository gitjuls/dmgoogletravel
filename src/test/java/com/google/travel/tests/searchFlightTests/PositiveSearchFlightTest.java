package com.google.travel.tests.searchFlightTests;

import com.google.travel.pages.*;
import com.google.travel.data.*;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;

public class PositiveSearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"One way", TestData.getTripData("oneWay")},
                {"Round trip", TestData.getTripData("roundTrip")},
                {"Multi-city", TestData.getTripData("multiCity")}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturned(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver, log);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertFalse(ExpectedResult.contains("No results"));
    }
}
