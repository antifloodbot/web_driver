package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.User;

public class CheckoutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By billingAddressOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostcodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By showLogin = By.className("showlogin");
    private final By userNameFld = By.id("username");
    private final By userPasswordFld = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By countryDropDown = By.id("select2-billing_country-container");
    private final By stateDropDown = By.id("select2-billing_state-container");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage enterFirstName(String firstName) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(firstNameFld));
        webElement.clear();
        webElement.sendKeys(firstName);
        return this;
    }

    public CheckoutPage enterLastName(String lastName) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(lastNameFld));
        webElement.clear();
        webElement.sendKeys(lastName);
        return this;
    }

    public CheckoutPage selectCountry(String countryName) {
        waitShortTime.until(ExpectedConditions.elementToBeClickable(countryDropDown)).click();
        driver.findElement(By.xpath("//li[text()='" + countryName + "']")).click();
        return this;
    }

    public CheckoutPage enterBillingAddressLineOne(String billingAddressOne) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(billingAddressOneFld));
        webElement.clear();
        webElement.sendKeys(billingAddressOne);
        return this;
    }

    public CheckoutPage enterBillingCity(String billingCity) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(billingCityFld));
        webElement.clear();
        webElement.sendKeys(billingCity);
        return this;
    }

    public CheckoutPage selectState(String stateName) {
        waitShortTime.until(ExpectedConditions.elementToBeClickable(stateDropDown)).click();
        driver.findElement(By.xpath("//li[text()='" + stateName + "']")).click();
        return this;
    }

    public CheckoutPage enterBillingPostcode(String billingPostCode) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(billingPostcodeFld));
        webElement.clear();
        webElement.sendKeys(billingPostCode);
        return this;
    }

    public CheckoutPage enterBillingEmail(String billingEmail) {
        WebElement webElement = waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(billingEmailFld));
        webElement.clear();
        webElement.sendKeys(billingEmail);
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress) {
        return enterFirstName(billingAddress.getFirstName())
                .enterLastName(billingAddress.getLastName())
                .selectCountry(billingAddress.getCountry())
                .enterBillingAddressLineOne(billingAddress.getAddressLineOne())
                .enterBillingCity(billingAddress.getCity())
                .selectState(billingAddress.getState())
                .enterBillingPostcode(billingAddress.getPostalCode())
                .enterBillingEmail(billingAddress.getEmail());
    }

    public CheckoutPage placeOrder() {
        waitForOverlaysToDisappear(overlay);
        waitShortTime.until(ExpectedConditions.elementToBeClickable(placeOrderBtn)).click();
        return this;
    }

    public String getNotice() {
        return waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(successNotice)).getText();
    }

    public CheckoutPage clickShowLoginBtn() {
        waitShortTime.until(ExpectedConditions.elementToBeClickable(showLogin)).click();
        return this;
    }

    public CheckoutPage enterUserName(String userName) {
        waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(userNameFld)).sendKeys(userName);
        return this;
    }

    public CheckoutPage enterUserPassword(String userPassword) {
        waitShortTime.until(ExpectedConditions.visibilityOfElementLocated(userPasswordFld)).sendKeys(userPassword);
        return this;
    }

    public CheckoutPage clickLoginButton() {
        waitShortTime.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
        return this;
    }

    public CheckoutPage login(User user) {
        return clickShowLoginBtn()
                .enterUserName(user.getUserName())
                .enterUserPassword(user.getUserPassword())
                .clickLoginButton();
    }

    public CheckoutPage selectDirectBankTransfer() {
        WebElement radioBtn = waitShortTime.until(ExpectedConditions.elementToBeClickable(directBankTransferRadioBtn));
        if(!radioBtn.isSelected()) {
            radioBtn.click();
        }
        return this;
    }
}
