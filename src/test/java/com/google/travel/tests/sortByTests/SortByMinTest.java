package com.google.travel.tests.sortByTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.GetTestData;
import com.google.travel.pages.fligthsPage.FlightsPage;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SortByMinTest extends TestBase {

    private FlightsPage flightsPage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"One way", GetTestData.getTripData("oneWay"), "price"},
                {"Round trip", GetTestData.getTripData("roundTrip"), "price"},
                {"Multi-city", GetTestData.getTripData("multiCity"), "price"},
                {"One way", GetTestData.getTripData("oneWay"), "duration"},
                {"Round trip", GetTestData.getTripData("roundTrip"), "duration"},
                {"Multi-city", GetTestData.getTripData("multiCity"), "duration"}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSortByNameIsMatchMinValue(String ticketType, List<String> searchByCode, String sortBy) {
        flightsPage = new FlightsPage(driver);
        flightsPage.navigate(EndPoint.FLIGHTS.endPoint);
        flightsPage.searchFlightsFeature.selectTicketType(ticketType);
        flightsPage.searchFlightsFeature.inputSearchData(searchByCode);
        searchPage = flightsPage.clickSearchButton();
        searchPage.sortByFeature.sortBy(sortBy);
        String firstItem = searchPage.sortByFeature.getFirstItem();
        String minItem = searchPage.sortByFeature.getMinItem();
        Assert.assertEquals(firstItem, minItem);
    }

}
