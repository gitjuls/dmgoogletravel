package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.stream.Collectors;

public class MultiCity extends AbstractComponent implements TripOption{

    private TicketType ticketType;
    By inputFields = By.xpath("//*[@jsname='MOPQS']//*[@jsname='pT3pqd']//input");
    By dropDownList = By.xpath("//div[@jsname='rymPhb']/ul/li[1]");
    By addFlight = By.xpath("//button[@jsname='htvI8d']//*[contains(text(), 'Add flight')]");

    public MultiCity(WebDriver driver) {
        super(driver);
        this.ticketType = new TicketType(driver);
        ticketType.selectTicketType(3); //multiCity
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
        List<WebElement> listOfInputFields = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(inputFields, 2));
        for (int i = 0; i < searchData.size(); i++) {
            listOfInputFields.get(i).clear();
            actions.sendKeys(listOfInputFields.get(i), searchData.get(i)).build().perform();
            WebElement dropDownList = wait.until(ExpectedConditions.presenceOfElementLocated(this.dropDownList));
            actions.moveToElement(dropDownList).click().build().perform();
        }
    }

    @Override
    public List<String> getSearchData(){
        List<WebElement> listOfInputFields = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@jsname='MOPQS']//*[@jsname='brjg8b']//span[3]")));
        List<String> result = listOfInputFields.stream()
                .map(el -> ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", el).toString())
                .collect(Collectors.toList());
        return result;
    }


}
