package com.epam.utility;

import com.epam.configuration.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverBaseClass {
    protected static WebDriver driver;
    protected WebDriverWait wait;


    static ConfigurationManager configurationManager = ConfigurationManager.getInstance();
    public static Logger logger = LogManager.getLogger(DriverBaseClass.class.getName());

    public static void initDriver() throws Exception {
        logger.info("Selecting the browser to run the application");
        driver=CommonFunctions.getBrowser(configurationManager.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Browser is set as " + configurationManager.getProperty("browser"));
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void tearDown() {
        driver.quit();
        logger.info("Driver is Quit");
    }
}
