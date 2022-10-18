package com.google.travel.tests.searchFlightTests;

import com.google.travel.data.TestData;
import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.List;

public class NegativeSearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"Round trip", TestData.getTripData("rtAltSug"), TestData.getSearchResultMessage("altSugMessage")},
                {"Round trip", TestData.getTripData("rtNoRes"), TestData.getSearchResultMessage("noResMessage")}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSearchResultsReturnedNoResultsOrAlternativeSuggestions(String ticketType, List<String> searchByCode, String expectedAlert) {
        searchFlight = new SearchFlight(driver, log);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String ExpectedResult = searchResult.searchResult();
        Assert.assertTrue(ExpectedResult.equalsIgnoreCase(expectedAlert));
    }
}
