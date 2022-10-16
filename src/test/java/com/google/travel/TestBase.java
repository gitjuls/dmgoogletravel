package com.google.travel;

import com.google.travel.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.apache.log4j.*;

public class TestBase {
    public WebDriver driver;
    public Logger log;

    @BeforeTest
    @Parameters("browser")
    public void setDriver(@Optional("chrome") String browser, ITestContext context){
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();

      //  log = LogManager.getLogger(this.getClass().getName());
        String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        BasicConfigurator.configure();
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile("logfile.txt");
        fileAppender.setLayout(new PatternLayout("[%t] %d{HH:mm:ss,SSS} %-5p [%c{1}] %m%n"));
        log.addAppender(fileAppender);
        fileAppender.activateOptions();
    }

    @AfterTest
    public void quitDriver(){
        driver.quit();
    }
}
