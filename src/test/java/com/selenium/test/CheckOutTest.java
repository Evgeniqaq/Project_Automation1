package com.selenium.test;

import Pages.LoginPage;
import Pages.ProductsPage;
import com.selenium.test.root.UtilTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends UtilTest {

    @Test
    public void checkOutTest() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");
        productPage.addToCartByProductName("backpack");
        productPage.openCart();

        Assert.assertEquals(productPage.getNumbersInTheCart(), 1 , "We have one item in the cart.");
    }
}
