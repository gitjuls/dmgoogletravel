package com.google.travel.tests.searchTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.*;
import com.google.travel.pages.fligthsPage.FlightsPage;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.tests.TestBase;
import com.google.travel.utilities.DataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class PositiveSearchFlightTest extends TestBase {

    private FlightsPage flightsPage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] getData(){
        String fileName = "searchTestPositiveData";
        return new Object[][]{
                {"One way", DataLoader.getInstance(fileName).getData("oneWay")},
                {"Round trip", DataLoader.getInstance(fileName).getData("roundTrip")},
                {"Multi-city", DataLoader.getInstance(fileName).getData("multiCity")}
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
