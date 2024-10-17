package org.selenium.pom.base;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void startDriver() {
        String browser = System.getProperty("browser");
        driver = new DriverManager().initializeDriver(browser);
        System.out.println("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + driver);
    }

    @After
    public void quitDriver() {
        System.out.println("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + driver);
        driver.quit();
    }
}
