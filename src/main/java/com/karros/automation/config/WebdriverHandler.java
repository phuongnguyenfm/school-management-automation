package com.karros.automation.config;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverHandler {
    
    private static WebDriver driver;
    
    static {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver");
    }
    
    public static void init() {
        setWebDriverSystemProp();
        
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void quit() {
        driver.quit();
    }
    
    public static WebDriver getWebDriver() {
        return driver;
    }
    
    private static void setWebDriverSystemProp() {
        String webDriverPath = "webdriver/chromedriver"; // Default MacOS
        if (isWindows()) {
            webDriverPath = "webdriver/chromedriver.exe";
        }
        System.setProperty("webdriver.chrome.driver", webDriverPath);
    }
    
    public static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }
    
    public static boolean isMacOs() {
        return System.getProperty("os.name").startsWith("Mac");
    }
}
