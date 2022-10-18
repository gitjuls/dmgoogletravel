package com.google.travel.utilities;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PatternLayout;
import org.testng.ITestContext;
import java.sql.Timestamp;
import java.util.Arrays;

public class Log4jLogger {

    public static org.apache.log4j.Logger log;

    public static org.apache.log4j.Logger setLogger(ITestContext context){
        String testName = Arrays.stream(context.getAllTestMethods()).findFirst().get().getMethodName();
        //String testName = context.getCurrentXmlTest().getName();
        log = LogManager.getLogger(testName);
        BasicConfigurator.configure();
        FileAppender fileAppender = new FileAppender();
        String path = buildFilePath(context);
        fileAppender.setFile(path);
        fileAppender.setLayout(new PatternLayout("[%t] %d{HH:mm:ss,SSS} %-5p [%c{1}] %m%n"));
        log.addAppender(fileAppender);
        fileAppender.activateOptions();
        return log;
    }

    private static String buildFilePath(ITestContext context){
        String userDir = System.getProperty("user.dir");
        String folder = "/logResult";
        String fileName = "/log " + new Timestamp(System.currentTimeMillis()) + ".txt";
        String fileNameNoColon = fileName.replaceAll(":" , "-");
        return new StringBuilder()
                .append(userDir)
                .append(folder)
                .append(fileNameNoColon).toString();
    }

}
