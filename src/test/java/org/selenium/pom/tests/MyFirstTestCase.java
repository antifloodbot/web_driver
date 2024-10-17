package org.selenium.pom.tests;

import org.junit.Assert;
import org.junit.Test;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {

    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu()
                .search(searchFor);
        Assert.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle());

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(product.getName(), cartPage.getProductName());

        CheckoutPage checkoutPage = cartPage
                .checkout()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice());
    }

    @Test
    public void loginAndCheckoutUsingBankTransfer() throws IOException {
        String searchFor = "Blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
        User user = new User("demouser22", "demouser22@gmail.com");
        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu()
                .search(searchFor);
        Assert.assertEquals("Search results: “" + searchFor + "”", storePage.getTitle());

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = storePage.clickViewCart();
        Assert.assertEquals(product.getName(), cartPage.getProductName());

        CheckoutPage checkoutPage = cartPage
                .checkout()
                .login(user)
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals("Thank you. Your order has been received.", checkoutPage.getNotice());
    }
}