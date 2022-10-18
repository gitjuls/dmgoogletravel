package com.google.travel;

import com.google.travel.driver.DriverFactory;
import com.google.travel.utilities.TestLogger;
import com.google.travel.utilities.UITestListener;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

@Listeners(UITestListener.class)
public class TestBase extends TestLogger {
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
        this.log = TestLogger.setLogger(context);
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
