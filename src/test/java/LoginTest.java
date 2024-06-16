import io.github.bonigarcia.wdm.WebDriverManager;
import org.binar.HomePage;
import org.binar.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        Assert.assertEquals(homePage.getTitle(), "Swag Labs");
        Assert.assertTrue(homePage.getProductTitles().size() > 0);
        Assert.assertTrue(homePage.isShoppingCartIconDisplayed());
        Assert.assertEquals(homePage.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void testFailedLogin() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match any user in this service"));
        Assert.assertEquals(loginPage.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertEquals(loginPage.getTitle(), "Swag Labs");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should still be displayed after a failed login attempt");
    }
}
