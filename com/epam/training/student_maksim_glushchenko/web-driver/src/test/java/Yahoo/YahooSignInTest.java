package Yahoo;

import PageObjects.Yahoo.YahooHomePage;
import PageObjects.Yahoo.YahooLoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;


public class YahooSignInTest {
    private WebDriver driver;
    private final String loginUrl = YahooLoginPage.url;
    @BeforeMethod
     void setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
        driver = new ChromeDriver();
        driver.get(loginUrl);
    }
    @AfterMethod
     void closeWebDriver(){
        driver.quit();
    }


    @Test
    public void testBlankCredentials() {
        YahooLoginPage loginPage =  new YahooLoginPage(driver);
        loginPage.loginAsUser("","");
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }

    @Test
    public void testInvalidCredentials() {
        YahooLoginPage loginPage =  new YahooLoginPage(driver);
        loginPage.loginAsUser("asdfsadf","sdfgsdfa");
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }
    @Test
    public void testValidCredentials() {
        YahooLoginPage loginPage =  new YahooLoginPage(driver);
        String login=System.getenv("yahoo_login");
        String password=System.getenv("yahoo_pass");
        YahooHomePage homePage=loginPage.loginAsUser(login,password);
        Assert.assertNotEquals(homePage.getTitle(), driver.getTitle());
    }

}