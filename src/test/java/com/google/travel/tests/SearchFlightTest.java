package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        List<String> oneWayData = new ArrayList<>();
        Collections.addAll(oneWayData, "DCA", "TPA");

        List<String> roundTripData = new ArrayList<>();
        Collections.addAll(roundTripData, "DCA", "LHR");

        List<String> multiCityData = new ArrayList<>();
        Collections.addAll(multiCityData, "DCA", "LHR", "TPA", "LHR", "TPA", "DCA");

        return new Object[][]{
                {"oneWay", oneWayData},
                {"roundTrip", roundTripData},
                {"multiCity", multiCityData}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSelectedTicketTypeMatchWithResult(String expectedTicketType, List<String> searchByCode){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(expectedTicketType, driver));
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String actualTicketType = searchResult.getTicketType();
        Assert.assertEquals(actualTicketType.toLowerCase(), expectedTicketType.toLowerCase());
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "getData")
    public void verifyIfInputDataMatchWithResult(String expectedTicketType, List<String> searchByCode){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(expectedTicketType, driver));
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        List<String> actualCode = searchResult.getSearchData().stream().collect(Collectors.toList());
        for(int i=0; i<actualCode.size(); i++){
            Assert.assertEquals(actualCode.get(i),searchByCode.get(i));
        }
    }

}
