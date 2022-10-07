package com.google.travel.pages.flightsList;

import com.google.travel.pages.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FlightsList extends BasePageObject {

    By flightPrice = By.xpath("//ul/li//span[contains(text(),'$')]");
    By flightDuration = By.xpath("//ul/li//div[contains(@aria-label, 'Total duration')]");

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

    public String getTheMinDurationTime(){
        List<WebElement> totalDurationList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        List<String> listOfDurationContainsHoursAndMinutes = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":").replace(" ", "").replace("min", "").trim())
                .collect(Collectors.toList());

        List<String> listOfDurationContainsOnlyHours = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> !el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":00").replace(" ", "").trim())
                .collect(Collectors.toList());

        List<List<String>> listOfDurationTime = new ArrayList<>();
        listOfDurationTime.add(listOfDurationContainsHoursAndMinutes);
        listOfDurationTime.add(listOfDurationContainsOnlyHours);

        OptionalLong minDurationTimeInMilliSeconds = listOfDurationTime.stream()
                .flatMap(l->l.stream())
                .map(el -> {
                    Date date = new Date();
                    try {date = format.parse(el);}
                    catch (ParseException e) {e.printStackTrace();}
                    return date.getTime(); // get time in milliSeconds;
                })
                .mapToLong(time -> time)
                .max();

        return new SimpleDateFormat("H:mm").format( new Date(minDurationTimeInMilliSeconds.getAsLong())).toString();
    }

    public String getTheFirstDurationTimeFromTheList(){
        List<WebElement> totalDurationList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));

        String firstDurationTimeThatContainsHoursAndMinutes = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":").replace(" ", "").replace("min", "").trim())
                .findFirst()
                .get();

        String firstDurationTimeThatContainsOnlyHours = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> !el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":00").replace(" ", "").trim())
                .findFirst()
                .get();

        return firstDurationTimeThatContainsHoursAndMinutes != null ? firstDurationTimeThatContainsHoursAndMinutes : firstDurationTimeThatContainsOnlyHours;
    }

}
