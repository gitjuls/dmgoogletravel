package com.google.travel.tests;

import com.google.travel.factory.DriverFactory;
import com.google.travel.logger.Log4jLogger;
import com.google.travel.listeners.UITestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(UITestListener.class)
public class TestBase extends Log4jLogger {

    public WebDriver driver;
    public Logger log;

    @Parameters("browser")
    @BeforeTest
    public void setDriver(@Optional("chrome") String browser, ITestContext context){
       this.driver = DriverFactory.getDriver(browser);
       this.driver.manage().window().maximize();
       context.setAttribute("driver", driver);
    }

    @BeforeTest
    public void getLogger(ITestContext context){
        this.log = Log4jLogger.setLogger(context);
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
