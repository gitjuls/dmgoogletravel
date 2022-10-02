package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TicketType extends AbstractComponent{

    By ticketTypeButton = By.xpath("//button[contains(@aria-label, 'Change ticket type.')]");
    By listOfTicketTypes = By.xpath("//ul[@role='listbox'][@aria-label='Select your ticket type.']/li");

    public TicketType(WebDriver driver) {
        super(driver);
    }

    public void selectTicketType(int index){
        WebElement ticketTypeButton = driver.findElement(this.ticketTypeButton);
        wait.until(driver1 -> ticketTypeButton.isDisplayed());
        ticketTypeButton.click();

        List<WebElement> listOfTicketTypes = driver.findElements(this.listOfTicketTypes);
        wait.until((driver) -> listOfTicketTypes.size() > 2);
        listOfTicketTypes.get(index-1).click();
    }

    public String getTicketType(){
        WebElement ticketTypeButton = driver.findElement(this.ticketTypeButton);
        wait.until(driver1 -> ticketTypeButton.isDisplayed());
        String ticketType = ticketTypeButton.getAttribute("aria-label").trim().toLowerCase();
        return ticketType;
    }

}
