package com.google.travel.pages.sortBy;

import com.google.travel.pages.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.function.Predicate;

public class SortBy extends BasePageObject {

    By sortByButton = By.xpath("//button//span[contains(text(), 'Sort by:')]/..");
    By sortByMenuItems = By.xpath("//ul[@role='menu']/li");
    By popUpMenu = By.xpath("//div[@class='GU4XB TiDqEf']//*[@role='button'][@aria-label='Close']");

    public SortBy(WebDriver driver) {
        super(driver);
    }

    public void clickSortByButton(){
        wait.until(ExpectedConditions.presenceOfElementLocated(this.popUpMenu)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(this.sortByButton)).click();
    }

    public void sortByMenuItem(String menuItem){
        List<WebElement> sortByMenuItem = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.sortByMenuItems, 2));
        Predicate<WebElement> predicate = SortByCriteriaFactory.selectMenuItem(menuItem);
        sortByMenuItem.stream().filter(predicate).findFirst().get().click();
    }
}
