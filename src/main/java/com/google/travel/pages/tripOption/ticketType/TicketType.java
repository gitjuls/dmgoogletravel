package com.google.travel.pages.tripOption.ticketType;

import com.google.travel.pages.BasePage;
import com.google.travel.pages.tripOption.TripOption;
import com.google.travel.pages.tripOption.TripOptionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TicketType extends BasePage {

    By ticketTypeButton = By.xpath("//button[contains(@aria-label, 'Change ticket type.')]");
    By listOfTicketTypes = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li");

    public TicketType(WebDriver driver) {
        super(driver);
    }

    private TripOption tripOption;

    private void setTripOption(TripOption tripOption){
        this.tripOption = tripOption;
    }

    public TripOption selectTicketType(String ticketType){
        WebElement ticketTypeButton = driver.findElement(this.ticketTypeButton);
        wait.until(driver1 -> ticketTypeButton.isDisplayed());
        ticketTypeButton.click();

        List<WebElement> listOfTicketTypes = driver.findElements(this.listOfTicketTypes);
        wait.until((driver) -> listOfTicketTypes.size() > 2);
        for (WebElement el: listOfTicketTypes) {
            if(el.getText().contains(ticketType)){
                el.click();
            }
        }

        setTripOption(TripOptionFactory.get(ticketType, driver));
        return tripOption;
    }

}
