package com.google.travel.pages.flightsList;

import com.google.travel.pages.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FlightsList extends BasePageObject {

    By flightPrice = By.xpath("//ul/li//span[contains(text(),'$')]");
    By flightDuration = By.xpath("//ul/li//div[contains(@aria-label, 'Total duration')]");

    public FlightsList(WebDriver driver) {
        super(driver);
    }

    public String getTheFirstFlightPriceFromTheList(){
        List<WebElement> flightPrice = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightPrice, 2));
        String firstPrice = flightPrice.stream()
                .filter(el -> el.getText().contains("$"))
                .findFirst()
                .map(el -> el.getText().trim())
                .map(el -> el.replace("$", "").replace(",","."))
                .get();

        return firstPrice;
    }

    public String getTheMinFlightPrice(){
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

        return minPriceInt.isPresent() ? minPriceInt.getAsInt()+"" : df.format(minPriceDouble.getAsDouble());
    }

    public String getTheMinDurationTime(){
        List<WebElement> totalDurationList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));

        List<String> listOfDurationContainsHoursAndMinutes = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> el.getText().contains("min"))
                .limit(5)
                .map(el -> el.getText().replace("hr", ":").replace(" ", "").replace("min", "").trim())
                .collect(Collectors.toList());

        List<String> listOfDurationContainsOnlyHours = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> !el.getText().contains("min"))
                .limit(5)
                .map(el -> el.getText().replace("hr", ":00").replace(" ", "").trim())
                .collect(Collectors.toList());

        List<List<String>> listOfDurationTime = new ArrayList<>();
        listOfDurationTime.add(listOfDurationContainsHoursAndMinutes);
        listOfDurationTime.add(listOfDurationContainsOnlyHours);

        Optional<Long> minDurationTimeInMilliSeconds = listOfDurationTime.stream()
                .flatMap(l->l.stream())
                .map(el -> {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    format.setTimeZone(TimeZone.getTimeZone("UTC"));
                    Date date = new Date();
                    try {
                        date = format.parse(el);
                    }
                    catch (ParseException e) {e.printStackTrace();}
                    return date;
                })
                .map(time -> time.getTime()) // get time in milliSeconds;
                .sorted()
                .findFirst();

        long tmilli = minDurationTimeInMilliSeconds.get();
        long h = TimeUnit.MILLISECONDS.toHours(tmilli);
        long m = TimeUnit.MILLISECONDS.toMinutes(tmilli) % 60;

        return new String(h+":"+m);
    }

    public String getTheFirstDurationTimeFromTheList(){
        List<WebElement> totalDurationList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));

        Optional<String> firstDurationTimeThatContainsHoursAndMinutes = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":").replace(" ", "").replace("min", "").trim())
                .findFirst();

        Optional<String> firstDurationTimeThatContainsOnlyHours = totalDurationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> !el.getText().contains("min"))
                .map(el -> el.getText().replace("hr", ":00").replace(" ", "").trim())
                .findFirst();

        return firstDurationTimeThatContainsHoursAndMinutes.isPresent() ? firstDurationTimeThatContainsHoursAndMinutes.get()+"" : firstDurationTimeThatContainsOnlyHours.get()+"";
    }

}
