package com.google.travel.tests.sortBy;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.GetTestData;
import com.google.travel.pages.fligths.searchFligths.SearchFlights;
import com.google.travel.pages.fligths.searchResult.SearchResult;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SortByMinPriceTests extends TestBase {

    private SearchFlights searchFlight;
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
    public void verifyIfSortedByMinPriceIsMatch(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlights(driver, log);
        searchFlight.navigate(EndPoint.FLIGHTS.endPoint);
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        searchResult.clickSortByButton();
        searchResult.sortBy("price");
        String firstPriceFromTheSortedList = searchResult.getTheFirstFlightPriceFromTheList();
        String minPriceFromTheList = searchResult.getTheMinFlightPrice();
        Assert.assertEquals(firstPriceFromTheSortedList, minPriceFromTheList);
    }
}
