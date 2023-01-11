package com.google.travel.pages.tripOption.ticketType;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.tripOption.TripOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class RoundTrip extends BasePage implements TripOption {

    By whereFrom = By.xpath("//*[@jsname='snKmsc']//*[@jsname='FDWhSe']//input[@value][@aria-labelledby]");
    By whereTo = By.xpath("//*[@jsname='iOyk4d']//*[@placeholder='Where to?']");

    public RoundTrip(WebDriver driver) {
        super(driver);
    }

    @Override
    public void inputSearchData(List<String> searchData) {
        Actions actions = new Actions(driver);
        String airportDataCode1 = searchData.get(0);
        String airportDataCode2 = searchData.get(1);

        WebElement whereFrom = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereFrom));
        whereFrom.clear();
        actions.sendKeys(whereFrom,airportDataCode1).build().perform();

        WebElement whereFromDropDownOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode1 +"']")));
        actions.moveToElement(whereFromDropDownOption).click().build().perform();

        WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereTo));
        whereTo.clear();
        actions.sendKeys(whereTo,airportDataCode2).build().perform();

        WebElement whereToDropDownOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode2 +"']")));
        actions.moveToElement(whereToDropDownOption).click().build().perform();
    }

}
