package com.google.travel.tests;

import com.google.travel.pages.SearchFlight;
import com.google.travel.pages.SearchResult;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class SearchFlightAction {

    private static final Consumer<SearchFlight> oneWay = searchFlight -> searchFlight.selectTicketType("One way");
    private static final Consumer<SearchFlight> roundTrip = searchFlight -> searchFlight.selectTicketType("Round trip");
    private static final Consumer<SearchFlight> multiCity = searchFlight -> searchFlight.selectTicketType("Multi-city");

    private static final Consumer<SearchFlight>  oneWayData = searchFlight -> searchFlight.inputSearchData(dataProvider("oneWayData"));
    private static final Consumer<SearchFlight>  roundTripData = searchFlight -> searchFlight.inputSearchData(dataProvider("roundTrip"));
    private static final Consumer<SearchFlight>  multiCityData = searchFlight -> searchFlight.inputSearchData(dataProvider("multiCityData"));

    public static final Consumer<SearchFlight> selectOneWayTripAndInputData = oneWay.andThen(oneWayData);
    public static final Consumer<SearchFlight> selectRoundTripAndInputData = roundTrip.andThen(roundTripData);
    public static final Consumer<SearchFlight> selectMultiCityTripAndInputData = multiCity.andThen(multiCityData);
    public static final Consumer<SearchFlight> clickSearchButton = searchFlight -> searchFlight.clickSearchButton();

    public static List<String> dataProvider(String str){
        List<String> data = new ArrayList<>();
            if(str == "oneWayData") {
                Collections.addAll(data, "DCA", "TPA");
            }
            if(str == "roundTrip") {
                Collections.addAll(data, "DCA", "LHR");
            }
            if(str == "multiCityData"){
                Collections.addAll(data, "DCA", "LAX", "LAX", "TPA", "TPA", "DCA");
            }
        return data;
    }
}
