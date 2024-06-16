package org.binar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement checkoutComplete;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField)).sendKeys(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isCheckoutComplete() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutComplete)).isDisplayed();
    }

    public String getCheckoutCompleteText() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutComplete)).getText();
    }

    public boolean isBackHomeButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(backHomeButton)).isDisplayed();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public String getTitle() {
        return driver.getTitle();
    }
}
