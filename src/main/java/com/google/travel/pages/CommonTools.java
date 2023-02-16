package com.google.travel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class CommonTools {

    public static Optional<WebElement> waitForPresenceOfElementLocated(By byLocator, WebDriver driver) {
        try {
            return Optional.ofNullable((new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.presenceOfElementLocated(byLocator))));
        } catch (TimeoutException e) {
            return Optional.empty();
        }
    }
}
