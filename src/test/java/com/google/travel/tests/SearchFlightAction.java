package com.google.travel.tests;

import com.google.travel.pages.SearchFlight;
import java.util.function.Consumer;

public class SearchFlightAction {

    public static final Consumer<SearchFlight> oneWay = searchFlight -> searchFlight.selectTicketType("One way");
    public static final Consumer<SearchFlight> roundTrip = searchFlight -> searchFlight.selectTicketType("Round trip");
    public static final Consumer<SearchFlight> multiCity = searchFlight -> searchFlight.selectTicketType("Multi-city");
}
