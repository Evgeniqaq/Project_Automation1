package com.selenium.test.root;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UtilTest {
    public WebDriver driver;
    private String url, browser;
//    private int implicitWaitSeconds;

    @BeforeMethod
    public void setUp() {
        setupBrowserDriver();
        loadInitialPage();
    }

    private void loadInitialPage() {
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void setupBrowserDriver() {
        try {
            FileInputStream configFile = new
                    FileInputStream("src/test/resources/config.properties");
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("url");
//             implicitWaitSeconds = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("markBrowser");


        } catch (IOException e) {
            System.out.println(e);

        }
        switch (browser) {
            case "chrome":
                driver = DriverFactory.getChromeDriver();
                break;
            case "firefox":
                driver = DriverFactory.getFirefoxDriver();
                break;
        }
    }

    private void setupChromeDriver() {
        driver = DriverFactory.getChromeDriver();

//       WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
    }

    private void setupFirefoxDriver() {
        driver = DriverFactory.getFirefoxDriver();
//
//       WebDriverManager.firefoxdriver().setup();
//       driver = new FirefoxDriver();

    }
}
