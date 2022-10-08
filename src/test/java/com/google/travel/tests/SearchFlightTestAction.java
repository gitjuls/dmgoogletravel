package com.google.travel.tests;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.TestBase;
import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import static com.google.travel.tests.SearchFlightAction.*;

public class SearchFlightTestAction extends TestBase {

    private SearchFlight searchFlight;
    private SearchResult searchResult;

    @BeforeTest
    public void setTest() {
        this.searchFlight = new SearchFlight(driver);
        this.searchResult = new SearchResult(driver);
    }

    @DataProvider(name="provider")
    public Object[][] testData(){
        return new Object[][]{
                {selectOneWayTripAndInputData.andThen(clickSearchButton) }
        };
    }

    @Test(dataProvider = "provider")
    public void verifyIfSortedByMinPriceIsMatch(Consumer<SearchFlight> searchConsumer) {
        searchFlight.navigate();
        searchConsumer.accept(searchFlight);
        searchResult.clickSortByButton();
        searchResult.sortBy("price");
        String firstPriceFromTheSortedList = searchResult.getTheFirstFlightPriceFromTheList();
        String minPriceFromTheList = searchResult.getTheMinFlightPrice();
        Assert.assertEquals(firstPriceFromTheSortedList, minPriceFromTheList);

    }

    @Test(dataProvider = "provider")
    public void verifyIfSortedByMinDurationTimeIsMatch(Consumer<SearchFlight> searchConsumer) {
        searchFlight.navigate();
        searchConsumer.accept(searchFlight);
        searchResult.clickSortByButton();
        searchResult.sortBy("duration");
        String firstDurationTimeFromTheSortedList = searchResult.getTheFirstDurationTimeFromTheList();
        String minDurationTimeFromTheList = searchResult.getTheMinDurationTime();
        Assert.assertEquals(firstDurationTimeFromTheSortedList, minDurationTimeFromTheList);

    }
}
