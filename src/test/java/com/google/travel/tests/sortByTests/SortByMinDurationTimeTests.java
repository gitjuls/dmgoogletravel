package com.google.travel.tests.sortByTests;

import com.google.travel.data.GetTestData;
import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import com.google.travel.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class SortByMinDurationTimeTests extends TestBase {

    private SearchFlight searchFlight;
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
    public void verifyIfSortedByMinDurationTimeIsMatch(String ticketType, List<String> searchByCode) {
        searchFlight = new SearchFlight(driver, log);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        searchResult.clickSortByButton();
        searchResult.sortBy("duration");
        String firstDurationTimeFromTheSortedList = searchResult.getTheFirstDurationTimeFromTheList();
        String minDurationTimeFromTheList = searchResult.getTheMinDurationTime();
        Assert.assertEquals(firstDurationTimeFromTheSortedList, minDurationTimeFromTheList);
    }
}
