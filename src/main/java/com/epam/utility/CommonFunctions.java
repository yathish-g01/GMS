package com.epam.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CommonFunctions {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CommonFunctions.class.getName());

    private CommonFunctions() {
    }

    public static WebDriver getBrowser(String browser) throws Exception {
        if (driver == null) {
            synchronized (CommonFunctions.class) {
                if (driver == null) {
                    if (browser.equalsIgnoreCase("firefox")) {
                        logger.info(browser + " is set as the browser to run the application.");
                        System.setProperty("webdriver.gecko.driver", "./Drivers/geckodriver.exe");
                        driver = new FirefoxDriver();
                    } else if (browser.equalsIgnoreCase("chrome")) {
                        logger.info(browser + " is set as the browser to run the application.");
                        System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
                        driver = new ChromeDriver();
                    } else if (browser.equalsIgnoreCase("Edge")) {
                        logger.info(browser + " is set as the browser to run the application.");
                        System.setProperty("webdriver.edge.driver", "./Drivers/MicrosoftWebDriver.exe");
                        driver = new EdgeDriver();
                    } else {
                        logger.error("Browser is not correct.");
                        throw new Exception("Browser is not correct");
                    }
                }
            }
        }
        return driver;
    }
}
