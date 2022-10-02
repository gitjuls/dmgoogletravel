package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RoundTrip extends AbstractComponent implements TripOption{

    private TicketType ticketType;
    By whereFrom = By.xpath("//*[@jsname='snKmsc']//*[@jsname='FDWhSe']//input[@value][@aria-labelledby]");
    By whereTo = By.xpath("//*[@jsname='iOyk4d']//*[@placeholder='Where to?']");

    public RoundTrip(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
        ticketType.selectTicketType(1); //roundTrip
    }

    @Override
    public void inputSearchData(Map<String, String> searchData) {
        Actions actions = new Actions(driver);
        String airportDataCode1 = searchData.get("whereFrom");
        String airportDataCode2 = searchData.get("whereTo");

        WebElement whereFrom = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereFrom));
        whereFrom.clear();
        actions.sendKeys(whereFrom,airportDataCode1).build().perform();

        WebElement whereFromAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode1 +"']")));
        actions.moveToElement(whereFromAirportOption).click().build().perform();

        WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereTo));
        whereTo.clear();
        actions.sendKeys(whereTo,airportDataCode2).build().perform();

        WebElement whereToAirportOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode2 +"']")));
        actions.moveToElement(whereToAirportOption).click().build().perform();
    }

    @Override
    public List<String> getInputSearchData() {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "one", "two", "three");
        return list;
    }

}
