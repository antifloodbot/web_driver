package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(String browser) {
        browser = System.getProperty("browser", browser);
        setDriver(new DriverManager().initializeDriver(browser));
        System.out.println("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + getDriver());
    }

    @AfterMethod
    public void quitDriver() {
        System.out.println("Current thread: " + Thread.currentThread().getId() + ", " + "Driver: " + getDriver());
        getDriver().quit();
    }
}
