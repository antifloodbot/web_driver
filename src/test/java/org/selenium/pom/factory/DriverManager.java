package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManager {
    public WebDriver initializeDriver(String browser) {
        WebDriver driver;

        driver = switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                yield new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
            }
            case SAFARI -> {
                WebDriverManager.safaridriver().setup();
                yield new SafariDriver();
            }
        };
        driver.manage().window().maximize();
        return driver;
    }
}
