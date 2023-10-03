package com.vinsguru.pages.flightreservation;

import com.vinsguru.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConformationPage extends AbstractPage {

    private final static Logger log = LoggerFactory.getLogger(FlightConformationPage.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConformationElement;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    public FlightConformationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConformationElement));
        return this.flightConformationElement.isDisplayed();
    }

    public String getPrice() {
        String confirmation = this.flightConformationElement.getText();
        String price = this.totalPriceElement.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price# : {}", price);
        return price;
    }
}