package com.google.travel.pages.searchPage.sortByFeature;

import com.google.common.util.concurrent.Uninterruptibles;
import com.google.travel.pages.BasePage;
import com.google.travel.pages.CommonTools;
import com.google.travel.pages.searchPage.sortByFeature.sortByOption.SortByInterface;
import com.google.travel.pages.searchPage.sortByFeature.sortByOption.SortByOptionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class SortByMenu extends BasePage {

    By sortByButton = By.xpath("//button//span[contains(text(), 'Sort by:')]/..");
    By sortByMenuItems = By.xpath("//ul[@role='menu']/li/span[3]");
    By popUpMenu = By.xpath("//div[@class='GU4XB TiDqEf']//*[@role='button'][@aria-label='Close']");

    private SortByInterface sortByInterface;
    private void setSortByInterface(SortByInterface sortByInterface){
        this.sortByInterface = sortByInterface;
    }

    public SortByMenu(WebDriver driver) {
        super(driver);
    }

    protected void clickSortByButton(){
        Optional<WebElement> el = CommonTools.waitForPresenceOfElementLocated(this.popUpMenu, driver);
        if(el.isPresent()){
            el.get().click();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(this.sortByButton)).click();
    }

    protected SortByInterface sortByMenuOption(String menuOption){
        List<WebElement> sortByMenuItem = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(this.sortByMenuItems, 2));
        Predicate<WebElement> predicate = SortByMenuOptionFactory.selectMenuItem(menuOption);
        sortByMenuItem.stream().filter(predicate).findFirst().get().click();
        /** wait 1 second while page is updating **/
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        setSortByInterface(SortByOptionFactory.get(menuOption, driver));
        return sortByInterface;
    }

}
