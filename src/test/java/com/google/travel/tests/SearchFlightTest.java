package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.RoundTrip;
import com.google.travel.pages.SearchFlight;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;

    @BeforeTest
    public void setSearchFlight(){
       searchFlight = new SearchFlight(driver);
    }

    @Test
    public void test(){
        searchFlight.navigate();
        searchFlight.setTripOption(new RoundTrip(driver));
        searchFlight.clickBt();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

}
