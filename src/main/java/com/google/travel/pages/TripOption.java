package com.google.travel.pages;

import java.util.Map;

public interface TripOption {
    void selectTicketType();
    void inputSearchParameters(Map<String,String> searchDetail);
}
