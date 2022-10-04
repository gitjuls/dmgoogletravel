package com.google.travel.pages;

import java.util.List;

public interface TripOption {
    void inputSearchData(List<String> searchData);
    List<String> getSearchData();
}
