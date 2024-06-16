package org.binar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productTitles;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "product_sort_container")
    WebElement sortButton;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartIcon;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getProductTitles() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
    }

    public List<WebElement> getProductPrices() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(productPrices));
    }

    public void sortItemsHighToLow() {
        wait.until(ExpectedConditions.elementToBeClickable(sortButton)).sendKeys("hilo");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isShoppingCartIconDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon)).isDisplayed();
    }
    public void addItemToCart(String itemId) {
        WebElement addItemButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(itemId)));
        addItemButton.click();
    }
}
