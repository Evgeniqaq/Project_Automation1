package com.selenium.test;
import Pages.*;
import com.selenium.test.root.UtilTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddItems extends UtilTest {

    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productPage = loginPage.login("standard_user", "secret_sauce");
        productPage.addToCartByProductName("backpack");
        productPage.addToCartByProductName("onesie");

        Assert.assertEquals(productPage.getNumbersInTheCart(),2, "We have two item in the cart.");
    }
}
