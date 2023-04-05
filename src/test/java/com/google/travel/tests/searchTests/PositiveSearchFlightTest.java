package com.google.travel.tests.searchTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.*;
import com.google.travel.pages.fligthsPage.FlightsPage;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class PositiveSearchFlightTest extends TestBase {

    private FlightsPage flightsPage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"One way", GetTestData.getTripData("oneWay")},
                {"Round trip", GetTestData.getTripData("roundTrip")},
                {"Multi-city", GetTestData.getTripData("multiCity")}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturned(String ticketType, List<String> searchByCode) {
        flightsPage = new FlightsPage(driver);
        flightsPage.navigate(EndPoint.FLIGHTS.endPoint);
        flightsPage.searchFlightsFeature.selectTicketType(ticketType);
        flightsPage.searchFlightsFeature.inputSearchData(searchByCode);
        searchPage = flightsPage.clickSearchButton();
        String ExpectedResult = searchPage.searchResult();
        Assert.assertFalse(ExpectedResult.contains("No results"));
    }
}
