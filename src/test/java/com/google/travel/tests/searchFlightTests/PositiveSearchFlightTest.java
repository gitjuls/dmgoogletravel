package com.google.travel.tests.searchFlightTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.pages.*;
import com.google.travel.data.*;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.*;

public class PositiveSearchFlightTest extends TestBase {

    private Flights flights;
    private SearchResult searchResult;

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
        flights = new Flights(driver, log);
        flights.navigate(EndPoint.FLIGHTS.endPoint);
        flights.selectTicketType(ticketType);
        flights.inputSearchData(searchByCode);
        searchResult = flights.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertFalse(ExpectedResult.contains("No results"));
    }
}
