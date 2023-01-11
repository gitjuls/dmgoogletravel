package com.google.travel.tests.searchFlightTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.GetTestData;
import com.google.travel.pages.Flights;
import com.google.travel.pages.SearchResult;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class NegativeSearchFlightTest extends TestBase {

    private Flights searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"Round trip", GetTestData.getTripData("rtAltSug"), GetTestData.getSearchResultMessage("altSugMessage")},
                {"Round trip", GetTestData.getTripData("rtNoRes"), GetTestData.getSearchResultMessage("noResMessage")}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturnedNoResultsOrAlternativeSuggestions(String ticketType, List<String> searchByCode, String expectedAlert) {
        searchFlight = new Flights(driver, log);
        searchFlight.navigate(EndPoint.FLIGHTS.endPoint);
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertTrue(ExpectedResult.equalsIgnoreCase(expectedAlert));
    }
}
