package com.vinsgutu.tests.flightreservation;

import com.vinsguru.pages.flightreservation.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FlightReservationTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() {
        WebDriverManager.chromedriver().driverVersion("116.0.5845.111").setup();
      // System.setProperty("webdriver.chrome.driver", "C:\\Proj\\chromedriver\\chromedriver115.exe");
      // driver = new ChromeDriver();
      // driver.manage().window().maximize();
       this.driver = new ChromeDriver();
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium@docker.com", "docker");
        registrationPage.enterAddress("123 non main street", "atlanta", "30001");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConformationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConformationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers("2");
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectionTest() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlight();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConformationTest() {
        FlightConformationPage flightConformationPage = new FlightConformationPage(driver);
        Assert.assertTrue(flightConformationPage.isAt());
        Assert.assertEquals(flightConformationPage.getPrice(), "$1169 USD");
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}