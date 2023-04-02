package com.google.travel.pages.commonFeatures.searchFligths.tripOption.ticketType;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.commonFeatures.searchFligths.tripOption.TripOption;
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
        actions.moveToElement(whereFrom).click();
        whereFrom.clear();
        inputStringByChar(airportDataCode1, actions, whereFrom);

        WebElement whereFromDropDownOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode1 +"']")));
        actions.moveToElement(whereFromDropDownOption).click().build().perform();

        WebElement whereTo = wait.until(ExpectedConditions.presenceOfElementLocated(this.whereTo));
        actions.moveToElement(whereTo);
        whereTo.clear();
        actions.sendKeys(whereTo,airportDataCode2).build().perform();

        WebElement whereToDropDownOption = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@role='listbox']/li[@data-code = '"+ airportDataCode2 +"']")));
        actions.moveToElement(whereToDropDownOption).click().build().perform();
    }

    private void inputStringByChar(String str, Actions actions, WebElement element){
        int i=0;
        do{
            actions.sendKeys(element,String.valueOf(str.charAt(i))).build().perform();
            i++;
        }while(i<str.length());
    }

}
