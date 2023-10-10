package com.vinsguru.tests;

import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() {
        // driver setup
        // WebDriverManager.chromedriver().driverVersion("116.0.5845.111");
        System.setProperty("webdriver.chrome.driver", "C:\\Proj\\chromedriver\\chromedriver115.exe");
        // WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}