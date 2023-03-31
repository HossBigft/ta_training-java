package Yahoo;

import PageObjects.Yahoo.YahooHomePage;
import PageObjects.Yahoo.YahooLoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class YahooLoginPageTest {
    private WebDriver driver;
    private final String loginUrl = YahooLoginPage.loginUrl;
    @BeforeTest
     void setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get(loginUrl);
    }
    @AfterTest
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
        YahooHomePage homePage=loginPage.loginAsUser(System.getenv("yahoo_login"),System.getenv("yahoo_pass"));
        Assert.assertNotEquals(homePage.getHomePageTitle(), driver.getTitle());
    }

}