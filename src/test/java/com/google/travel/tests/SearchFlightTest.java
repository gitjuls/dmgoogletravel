package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @DataProvider
    public Object[][] getData(){
        Map<String, String> oneWayData = new HashMap<>();
        oneWayData.put("whereFrom", "DCA");
        oneWayData.put("whereTo", "TPA");

        Map<String, String> roundTripData = new HashMap<>();
        roundTripData.put("whereFrom", "DCA");
        roundTripData.put("whereTo", "LHR");

        Map<String, String> multiCityData = new HashMap<>();
        multiCityData.put("whereFrom1", "DCA");
        multiCityData.put("whereTo1", "LHR");
        multiCityData.put("whereFrom2", "TPA");
        multiCityData.put("whereTo2", "LHR");
        /*multiCityData.put("whereFrom3", "TPA");
        multiCityData.put("whereTo3", "DCA");
        multiCityData.put("whereFrom4", "TPA");
        multiCityData.put("whereTo4", "DCA");*/

        return new Object[][]{
             //   {"oneWay", oneWayData},
                {"roundTrip", roundTripData}//,
               // {"multiCity", multiCityData}
        };
    }

    @Test(dataProvider = "getData")
    public void test1(String expectedTicketType, Map<String, String> searchData){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(expectedTicketType, driver));
        searchFlight.inputSearchData(searchData);
        searchResult = searchFlight.clickSearchButton();
        String actualTicketType = searchResult.getTicketType();
        Assert.assertEquals(actualTicketType.toLowerCase(), expectedTicketType.toLowerCase());
       // Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

    @Test(dataProvider = "getData")
    public void test2(String expectedTicketType, Map<String, String> searchData){
        searchFlight = new SearchFlight(driver);
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(expectedTicketType, driver));
        searchFlight.inputSearchData(searchData);
        searchResult = searchFlight.clickSearchButton();
        List<String> actualCodeList = searchResult.getInputSearchData().stream().collect(Collectors.toList());
        String actual = null;
        String expected = null;
        for (String code : searchData.values()){
            expected = code;
            System.out.println("expected " + expected);
        }
        for(String code : actualCodeList){
            actual = code;
            System.out.println("actual " + actual);
        }
        Assert.assertEquals(actual,expected);
    }

    @Test(dataProvider = "getData")
    public void test3(String expectedTicketType, Map<String, String> searchData){
        for (String code : searchData.values())
            System.out.println(code);
    }

}
