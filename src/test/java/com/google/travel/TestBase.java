package com.google.travel;

import com.google.travel.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


public class TestBase {
    public WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setDriver(@Optional("chrome") String browser){
        driver = DriverFactory.getDriver(browser);
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
