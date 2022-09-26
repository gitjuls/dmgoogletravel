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
        oneWayData.put("whereFrom", "DCA");
        oneWayData.put("whereTo", "TPA");

        Map<String, String> roundTrip = new HashMap<>();
        roundTrip.put("whereFrom", "DCA");
        roundTrip.put("whereTo", "LHR");

        Map<String, String> multiCity = new HashMap<>();
        multiCity.put("whereFrom1", "DCA");
        multiCity.put("whereTo1", "LHR");
        multiCity.put("whereFrom2", "TPA");
        multiCity.put("whereTo2", "LHR");

        return new Object[][]{
               // {"oneWay", oneWayData},
               // {"roundTrip", roundTrip},
                {"multiCity", multiCity}
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
