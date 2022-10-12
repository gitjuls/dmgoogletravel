package com.google.travel.pages.sortBy;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.pages.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class SortBy extends BasePageObject {

    By sortByButton = By.xpath("//button//span[contains(text(), 'Sort by:')]/..");
    By sortByMenuItems = By.xpath("//ul[@role='menu']/li");
    By popUpMenu = By.xpath("//div[@class='GU4XB TiDqEf']//*[@role='button'][@aria-label='Close']");

    public SortBy(WebDriver driver) {
        super(driver);
    }

    public void clickSortByButton(){
        Optional<WebElement> el = waitForPresenceOfElementLocated(this.popUpMenu);
        if(el.isPresent()){
            el.get().click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(this.sortByButton)).click();
    }

    public void sortByMenuItem(String menuItem){
        List<WebElement> sortByMenuItem = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.sortByMenuItems, 2));
        Predicate<WebElement> predicate = SortByCriteriaFactory.selectMenuItem(menuItem);
        sortByMenuItem.stream().filter(predicate).findFirst().get().click();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

    protected Optional<WebElement> waitForPresenceOfElementLocated(By byLocator) {
        try {
            return Optional.ofNullable((new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(byLocator))));
        } catch (TimeoutException e) {
            return Optional.empty();
        }
    }
}
