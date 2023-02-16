package com.google.travel.pages.searchPage.sortByFeature;

import com.google.travel.pages.searchPage.sortByFeature.sortByOption.SortByInterface;
import org.openqa.selenium.WebDriver;

public class SortByFeature extends SortByMenu{

    private SortByInterface sortByInterface;

    public SortByFeature(WebDriver driver) {
        super(driver);
    }

   public void sortBy(String menuOption){
        clickSortByButton();
        this.sortByInterface = sortByMenuOption(menuOption);
    }

    public String getFirstItem(){return sortByInterface.getFirstItem();}
    public String getMinItem(){return sortByInterface.getMinItem();}
}
