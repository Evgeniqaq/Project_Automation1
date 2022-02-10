package com.selenium.test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.selenium.test.root.UtilTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnsuccessfulLogin extends UtilTest {
    @DataProvider(name = "users_csv")
    public Object[][] readUsersFromCsv() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/userList.csv"))){
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];
            for (int i = 0; i <csvData.size() ; i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;
        }
    }

    @DataProvider (name = "users")
    public Object[][] getUsers() {
        return new Object[][]{
                {"standard_user", "wrongPass"},
                {"Tralala", "secret_sauce"},
        };
    }

    @Test(dataProvider = "users")
    public void logInWithWrongUser(String userName, String password) {

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.clear();
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.id("password"));
        userNameInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        userNameInput.clear();
        loginBtn.click();

        WebElement wrongUserBtn = driver.findElement(By.cssSelector(".error-button"));

        Assert.assertTrue(wrongUserBtn.isDisplayed());

    }
}
