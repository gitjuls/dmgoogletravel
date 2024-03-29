package com.google.travel.pages.commonFeatures.searchFligths.tripOption.ticketType;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.commonFeatures.searchFligths.tripOption.TripOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class MultiCity extends BasePage implements TripOption {

    By inputFields = By.xpath("//*[@jsname='MOPQS']//*[@jsname='pT3pqd']//input");
    By dropDownList = By.xpath("//div[@jsname='rymPhb']/ul/li[1]");
    By addFlight = By.xpath("//button[@jsname='htvI8d']//*[contains(text(), 'Add flight')]");

    public MultiCity(WebDriver driver) {
        super(driver);
    }

    @Override
    public void inputSearchData(List<String> searchData) {
        WebElement addFlight = driver.findElement(this.addFlight);
        wait.until(driver1 -> addFlight.isDisplayed());

        Actions actions = new Actions(driver);

        int rows = searchData.size()/2;
        if(rows > 2){
            for (int i = 1; i <= rows-2 ; i++) {
                addFlight.click();
            }
        }
        List<WebElement> listOfInputFields = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFields, 1));
        for (int i = 0; i < searchData.size(); i++) {
            actions.moveToElement(listOfInputFields.get(i)).click();
            listOfInputFields.get(i).clear();
            inputStringByChar(searchData.get(i), actions, listOfInputFields.get(i));
            WebElement dropDownList = wait.until(ExpectedConditions.presenceOfElementLocated(this.dropDownList));
            actions.moveToElement(dropDownList).click().build().perform();
        }
    }

    private void inputStringByChar(String str, Actions actions, WebElement element){
        int i=0;
        do{
            actions.sendKeys(element,String.valueOf(str.charAt(i))).build().perform();
            i++;
        }while(i<str.length());
    }
}
