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
               // {"One way", oneWayData},
               // {"Round trip", roundTripData},
                {"Multi-city", multiCityData}
        };
    }

    @Test(dataProvider = "getData")
    public void verifyIfSelectedTicketTypeMatchWithResult(String ticketType, List<String> searchByCode){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        String actualTicketType = searchResult.getTicketType();
        Assert.assertTrue(actualTicketType.contains(ticketType));
        //Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "getData")
    public void verifyIfInputDataMatchWithResult(String ticketType, List<String> searchByCode){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.selectTicketType(ticketType);
        searchFlight.inputSearchData(searchByCode);
        searchResult = searchFlight.clickSearchButton();
        List<String> actualCode = searchResult.getSearchData().stream().collect(Collectors.toList());
        for(int i=0; i<actualCode.size(); i++){
            Assert.assertEquals(actualCode.get(i),searchByCode.get(i));
        }
    }

}
