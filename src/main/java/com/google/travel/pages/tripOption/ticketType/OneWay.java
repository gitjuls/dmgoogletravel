package com.google.travel.pages.tripOption.ticketType;

import com.google.travel.pages.tripOption.TripOption;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class OneWay extends RoundTrip implements TripOption {

    public OneWay(WebDriver driver) {
        super(driver);
    }

    @Override
    public void inputSearchData(List<String> searchData) {
        super.inputSearchData(searchData);
    }

}
