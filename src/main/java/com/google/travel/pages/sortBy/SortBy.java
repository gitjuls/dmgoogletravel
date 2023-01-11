package com.google.travel.pages.sortBy;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.CommonTools;
import com.google.travel.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class SortBy extends BasePage {

    By sortByButton = By.xpath("//button//span[contains(text(), 'Sort by:')]/..");
    By sortByMenuItems = By.xpath("//ul[@role='menu']/li");
    By popUpMenu = By.xpath("//div[@class='GU4XB TiDqEf']//*[@role='button'][@aria-label='Close']");

    public SortBy(WebDriver driver) {
        super(driver);
    }

    public void clickSortByButton(){
        Optional<WebElement> el = CommonTools.waitForPresenceOfElementLocated(this.popUpMenu, driver);
        if(el.isPresent()){
            el.get().click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(this.sortByButton)).click();
    }

    public void sortByMenuItem(String menuItem){
        List<WebElement> sortByMenuItem = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.sortByMenuItems, 2));
        Predicate<WebElement> predicate = SortByOptionFactory.selectMenuItem(menuItem);
        sortByMenuItem.stream().filter(predicate).findFirst().get().click();
        /** wait 1 second while page is updating **/
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

}
