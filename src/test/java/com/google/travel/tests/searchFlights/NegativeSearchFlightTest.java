package com.google.travel.tests.searchFlights;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.GetTestData;
import com.google.travel.pages.fligthsPage.FlightsPage;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class NegativeSearchFlightTest extends TestBase {

    private FlightsPage flightsPage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"Round trip", GetTestData.getTripData("rtAltSug"), GetTestData.getSearchResultMessage("altSugMessage")},
                {"Round trip", GetTestData.getTripData("rtNoRes"), GetTestData.getSearchResultMessage("noResMessage")}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturnedNoResultsOrAlternativeSuggestions(String ticketType, List<String> searchByCode, String expectedAlert) {
        flightsPage = new FlightsPage(driver);
        flightsPage.navigate(EndPoint.FLIGHTS.endPoint);
        flightsPage.searchFlightsFeature.selectTicketType(ticketType);
        flightsPage.searchFlightsFeature.inputSearchData(searchByCode);
        searchPage = flightsPage.clickSearchButton();
        String ExpectedResult = searchPage.searchResult();
        Assert.assertTrue(ExpectedResult.equalsIgnoreCase(expectedAlert));
    }
}
