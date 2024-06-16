import io.github.bonigarcia.wdm.WebDriverManager;
import org.binar.CheckoutPage;
import org.binar.HomePage;
import org.binar.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCheckout() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        homePage.addItemToCart("add-to-cart-sauce-labs-backpack");
        homePage.addItemToCart("add-to-cart-sauce-labs-bike-light");

        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();

        checkoutPage.enterFirstName("Abel");
        checkoutPage.enterLastName("Licius");
        checkoutPage.enterPostalCode("1234567");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        Assert.assertTrue(checkoutPage.isCheckoutComplete());
        Assert.assertEquals(checkoutPage.getCheckoutCompleteText(), "Thank you for your order!");
        Assert.assertTrue(checkoutPage.isBackHomeButtonDisplayed());
        Assert.assertEquals(checkoutPage.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(homePage.getTitle(), "Swag Labs");
    }
}
