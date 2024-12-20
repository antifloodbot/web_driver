package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait waitLongTime;
    protected WebDriverWait waitMediumTime;
    protected WebDriverWait waitShortTime;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waitLongTime = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitMediumTime = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitShortTime = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void load(String endpoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endpoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("OVERLAY SIZE: " + overlays.size());
        if (!overlays.isEmpty()) {
            waitMediumTime.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("OVERLAYS ARE INVISIBLE");
        } else {
            System.out.println("OVERLAYS NOT FOUND");
        }
    }
}
