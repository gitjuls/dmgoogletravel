package com.google.travel.pages.searchPage.sortByFeature.sortByOption;

import com.google.travel.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Price extends BasePage implements SortByInterface {

    By flightPrice = By.xpath("//ul/li//span[contains(text(),'$')]");

    public Price(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getFirstItem() {
        List<WebElement> flightPrice = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightPrice, 2));
        String firstPrice = flightPrice.stream()
                .filter(el -> el.getText().contains("$"))
                .findFirst()
                .map(el -> el.getText().trim())
                .map(el -> el.replace("$", "").replace(",","."))
                .get();

        return firstPrice;
    }

    @Override
    public String getMinItem() {
        List<WebElement> flightPrice = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightPrice, 4));
        List<String> priceList = flightPrice.stream()
                .filter(el -> el.getText().contains("$"))
                .limit(10)
                .map(el -> el.getText().trim())
                .map(price -> price.replace("$", ""))
                .collect(Collectors.toList());

        /** price equal 999 and less **/
        OptionalInt minPriceInt = priceList.stream()
                .filter(price -> price.length() <= 3)
                .mapToInt(price -> Integer.valueOf(price))
                .min();

        /** price equal 1,000 and more **/
        OptionalDouble minPriceDouble = priceList.stream()
                .filter(price -> price.length() >= 5)
                .map(el -> el.replace(",","."))
                .mapToDouble(price -> Double.parseDouble(price))
                .min();

        DecimalFormat df = new DecimalFormat("#.000");

        return minPriceInt.isPresent() ? String.valueOf(minPriceInt.getAsInt()) : String.valueOf(df.format(minPriceDouble.getAsDouble()));
    }
}
