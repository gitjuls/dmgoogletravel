package com.google.travel.tests;

import com.google.travel.pages.OneWay;
import com.google.travel.pages.RoundTrip;
import com.google.travel.pages.TripOption;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TripOptionFactory {

   /* private static final Supplier<TripOption> ONE_WAY = () -> new OneWay();
    private static final Supplier<TripOption> ROUND_TRIP = () -> new RoundTrip();

    private static final Map<String,Supplier<TripOption>> MAP = new HashMap<>();

    static {
        MAP.put("ow", ONE_WAY);
        MAP.put("rt", ROUND_TRIP);
    }

    public static TripOption get(String option){
        return MAP.get(option).get();
    }*/
}
