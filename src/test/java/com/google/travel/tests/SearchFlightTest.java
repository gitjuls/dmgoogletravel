package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;

    @BeforeTest
    public void setSearchFlight(){
       searchFlight = new SearchFlight(driver);
    }

    @DataProvider
    public Object[][] getData(){
        Map<String, String> oneWayData = new HashMap<>();
        oneWayData.put("whereFrom", "Washington");
        oneWayData.put("whereTo", "Tampa");

        Map<String, String> roundTrip = new HashMap<>();
        roundTrip.put("whereFrom", "Washington");
        roundTrip.put("whereTo", "Tampa");

        return new Object[][]{
               // {"oneWay", oneWayData},
                {"roundTrip", roundTrip}
                //{"multiCity"}
        };
    }

    @Test(dataProvider = "getData")
    public void test(String tripOption, Map<String, String> searchParameters){
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(tripOption, driver));
        searchFlight.inputSearchParameters(searchParameters);
        searchFlight.clickSearchButton();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

}
