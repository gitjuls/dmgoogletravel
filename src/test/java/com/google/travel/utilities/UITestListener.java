package com.google.travel.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

public class UITestListener extends TestLogger implements ITestListener {
    public WebDriver driver;

    @Override
    public void onTestStart(ITestResult result){
        log.info("TEST " + result.getMethod().getMethodName() + " START");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        log.info("TEST " + result.getMethod().getMethodName() + " PASS");
    }

    @Override
    public void onTestSkipped(ITestResult result){
        log.debug("TEST " + result.getMethod().getMethodName() + " SKIPPED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.debug("TEST " + result.getMethod().getMethodName() + " FAIL");

        this.driver = (WebDriver) result.getTestContext().getAttribute("driver");
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        String path = buildFilePath(result);
        try {
            FileUtils.copyFile(screenshotFile , new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildFilePath(ITestResult testResult){
        String userDir = System.getProperty("user.dir");
        String folder = "/screenshots";
        String className = "/" + testResult.getTestClass().getName().replace("." , "/");
        String testMethodName = "/" + testResult.getMethod().getMethodName();
        String fileName = "/image_" + testResult.getMethod().getMethodName() + "_" + new Timestamp(System.currentTimeMillis()) + ".jpg";
        String fileNameNoColon = fileName.replaceAll(":" , "-");
        return new StringBuilder()
                .append(userDir)
                .append(folder)
                .append(className)
                .append(testMethodName)
                .append(fileNameNoColon).toString();
    }

}
