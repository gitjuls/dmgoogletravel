package com.google.travel.pages.searchPage.sortByFeature.sortByOption;

import com.google.travel.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Duration extends BasePage implements SortByInterface {

    By flightDuration = By.xpath("//ul/li//div[contains(@aria-label, 'Total duration')]");

    public Duration(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getFirstItem() {
        List<WebElement> durationWebElementsList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));

        Optional<String> firstDurationTime_HHmm_Pattern =
                getDurationTimeWithHoursAndMinutesPattern(durationWebElementsList).stream().findFirst();

        Optional<String> firstDurationTime_HH_Pattern =
                getDurationTimeWithHoursOnlyPattern(durationWebElementsList).stream().findFirst();

        return firstDurationTime_HHmm_Pattern.isPresent() ? firstDurationTime_HHmm_Pattern.get()+"" : firstDurationTime_HH_Pattern.get()+"";
    }

    private List<String> getDurationTimeWithHoursAndMinutesPattern(List<WebElement> durationList){
        List<String> list = durationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> el.getText().contains("min"))
                .limit(5)
                .map(el -> el.getText().replace("hr", ":").replace(" ", "").replace("min", "").trim())
                .collect(Collectors.toList());
        return list;
    }

    private List<String> getDurationTimeWithHoursOnlyPattern(List<WebElement> durationList){
        List<String> list = durationList.stream()
                .filter(el -> el.getText().contains("hr"))
                .filter(el -> !el.getText().contains("min"))
                .limit(5)
                .map(el -> el.getText().replace("hr", ":00").replace(" ", "").trim())
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public String getMinItem() {
        List<WebElement> durationWebElementsList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.flightDuration, 4));

        List<String> durationList_HHmm_Pattern = getDurationTimeWithHoursAndMinutesPattern(durationWebElementsList);
        List<String> durationList_HH_Pattern = getDurationTimeWithHoursOnlyPattern(durationWebElementsList);

        List<List<String>> durationTimeList = new ArrayList<>();
        Collections.addAll(durationTimeList, durationList_HHmm_Pattern, durationList_HH_Pattern);

        Optional<Long> minDurationTimeInMilliSeconds = durationTimeList.stream()
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
                .map(date -> date.getTime()) // get time in milliSeconds;
                .sorted()
                .findFirst();

        long tmilli = minDurationTimeInMilliSeconds.get();
        long h = TimeUnit.MILLISECONDS.toHours(tmilli);
        long m = TimeUnit.MILLISECONDS.toMinutes(tmilli) % 60;

        return new String(h+":"+m);
    }
}
