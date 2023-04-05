package com.google.travel.tests.sortByTests;

import com.google.travel.constants.EndPoint;
import com.google.travel.data.GetTestData;
import com.google.travel.pages.fligthsPage.FlightsPage;
import com.google.travel.pages.searchPage.SearchPage;
import com.google.travel.tests.TestBase;
import com.google.travel.utilities.DataLoader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SortByMinTest extends TestBase {

    private FlightsPage flightsPage;
    private SearchPage searchPage;

    @DataProvider
    public Object[][] getData(){
        String fileName = "searchTestPositiveData";
        return new Object[][]{
                {"One way", DataLoader.getInstance(fileName).getPath("oneWay"),"price"},
                {"Round trip", DataLoader.getInstance(fileName).getPath("roundTrip"),"price"},
                {"Multi-city", DataLoader.getInstance(fileName).getPath("multiCity"),"price"},
                {"One way", DataLoader.getInstance(fileName).getPath("oneWay"),"duration"},
                {"Round trip", DataLoader.getInstance(fileName).getPath("roundTrip"),"duration"},
                {"Multi-city", DataLoader.getInstance(fileName).getPath("multiCity"),"duration"}
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
