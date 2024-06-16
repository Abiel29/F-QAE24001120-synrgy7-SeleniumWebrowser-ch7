import io.github.bonigarcia.wdm.WebDriverManager;
import org.binar.HomePage;
import org.binar.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SortingTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSortHighToLow() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        homePage.sortItemsHighToLow();

        List<WebElement> prices = homePage.getProductPrices();
        double firstPrice = Double.parseDouble(prices.get(0).getText().replace("$", ""));
        double secondPrice = Double.parseDouble(prices.get(1).getText().replace("$", ""));

        Assert.assertTrue(firstPrice > secondPrice);
        Assert.assertEquals(homePage.getTitle(), "Swag Labs");
        Assert.assertTrue(homePage.isShoppingCartIconDisplayed());
        Assert.assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
