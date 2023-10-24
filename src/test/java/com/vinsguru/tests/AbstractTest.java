package com.vinsguru.tests;

import com.vinsguru.pages.vendorportal.DashboardPage;
import com.vinsguru.pages.vendorportal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
//    @Parameters({"browser"})
//        public void setDriver(String browser) throws MalformedURLException {
        public void setDriver() throws MalformedURLException {
        // driver setup
        // WebDriverManager.chromedriver().driverVersion("116.0.5845.111");
        //System.setProperty("webdriver.chrome.driver", "C:\\Proj\\chromedriver\\chromedriver115.exe");

        if (Boolean.getBoolean("selenium.grid.enabled")) {
//            this.driver = getRemoteDriver(browser);
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
            this.driver.manage().window().maximize();
        }

        //WebDriverManager.chromedriver().setup();
        //this.driver = new ChromeDriver();
        //this.driver.manage().window().maximize();
    }

//    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        private WebDriver getRemoteDriver() throws MalformedURLException {
            Capabilities capabilities;
        if (System.getProperty("browser").equalsIgnoreCase("chrome")) {
//        if (browser.equalsIgnoreCase("chrome")) {

            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}