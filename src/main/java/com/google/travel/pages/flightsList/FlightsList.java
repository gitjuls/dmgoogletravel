package com.google.travel.pages.flightsList;

import com.google.travel.pages.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.OptionalInt;

public class FlightsList extends BasePageObject {

    By flightsList = By.xpath("");
    By flightPrice = By.xpath("//ul/li//span[contains(text(),'$')]");

    public FlightsList(WebDriver driver) {
        super(driver);
    }

    public int getTheFirstFlightPriceFromTheList(){
        List<WebElement> flightPrice = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightPrice, 2));
        int firstPrice = flightPrice.stream()
                .filter(el -> el.getText().contains("$"))
                .findFirst()
                .map(el -> el.getText().trim())
                .map(el -> el.replace("$", ""))
                .map(price -> Integer.parseInt(price))
                .get();
        return firstPrice;
    }

    public int getTheMinFlightPrice(){
        List<WebElement> flightPrice = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightPrice, 4));
        OptionalInt minPrice = flightPrice.stream()
                .filter(el -> el.getText().contains("$"))
                .map(el -> el.getText().trim())
                .map(price -> price.replace("$", ""))
                .mapToInt(price -> Integer.parseInt(price))
                .min();
        return minPrice.getAsInt();
    }

}
