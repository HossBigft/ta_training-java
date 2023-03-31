package Tutanota;

import PageObjects.Tutanota.TutanotaEmailPage;
import PageObjects.Tutanota.TutanotaLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TutanotaLoginPageTest {

    private WebDriver driver;
    private final String loginUrl = TutanotaLoginPage.loginUrl;
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
        TutanotaLoginPage loginPage =  new TutanotaLoginPage(driver);
        loginPage.loginAsUser("","");
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }

    @Test
    public void testInvalidCredentials() {
        TutanotaLoginPage loginPage =  new TutanotaLoginPage(driver);
        loginPage.loginAsUser("asdfsadf","sdfgsdfa");
        Assert.assertEquals(loginUrl, driver.getCurrentUrl());
    }
    @Test
    public void testValidCredentials() {
        TutanotaLoginPage loginPage =  new TutanotaLoginPage(driver);
        String login= System.getenv("tutanota_login");
        String password= System.getenv("tutanota_pass");
        TutanotaEmailPage homePage=loginPage.loginAsUser(login,password);
        Assert.assertEquals(homePage.getEmailPageTitle(), driver.getTitle());
    }
}
