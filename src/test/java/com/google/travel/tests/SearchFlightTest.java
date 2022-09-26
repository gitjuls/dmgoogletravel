package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SearchFlightTest extends TestBase {

    private SearchFlight searchFlight;

    @BeforeTest
    public void setSearchFlight(){
       searchFlight = new SearchFlight(driver);
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"oneWay"},
                {"roundTrip"},
                //{"multiCity"}
        };
    }

    @Test(dataProvider = "getData")
    public void test(String tripOption){
        searchFlight.navigate();
        searchFlight.setTripOption(TripOptionFactory.get(tripOption, driver));
        searchFlight.clickSearchButton();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
    }

}
