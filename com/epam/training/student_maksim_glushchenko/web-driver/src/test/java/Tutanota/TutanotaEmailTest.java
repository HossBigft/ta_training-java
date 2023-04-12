package Tutanota;

import PageObjects.Tutanota.TutanotaEmailPage;
import PageObjects.Yahoo.YahooEmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TutanotaEmailTest {
    private WebDriver driver;
    private final String yahAdress = YahooEmailPage.adress;
    private final String tutaAdress = TutanotaEmailPage.adress;
    private final String mailSubject="eksdee";
    private final String mailContent="All work and no play makes Jack a dull boy";
    @BeforeMethod
    void setup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments(" --start-maximized");
        options.addArguments("--profile-directory=Profile 2");
        options.addArguments("--user-data-dir="+System.getenv("chromeProfilePath"));
        driver = new ChromeDriver(options);
    }
    @AfterMethod
    void closeWebDriver(){driver.quit();}
    @AfterTest
    void clearEmail(){
        setup();
        TutanotaEmailPage tutaPage =  new TutanotaEmailPage(driver);
        tutaPage.clearSentFolder();
        YahooEmailPage yahPage = new YahooEmailPage(driver);
        yahPage.clearInboxFolder();
        driver.quit();
    }


    @Test(priority = 0)
    public void testSendEmail(){
        TutanotaEmailPage emailPage =  new TutanotaEmailPage(driver);
        emailPage.sendEmail(yahAdress, mailSubject, mailContent);
        Assert.assertTrue(emailPage.isMailSent(yahAdress, mailSubject, mailContent));

    }

    @Test(priority = 1)
    public void testReceiveEmail(){
        YahooEmailPage emailPage = new YahooEmailPage(driver);
        Assert.assertTrue(emailPage.isMailReceivedAndUnread(tutaAdress, mailSubject, mailContent));
    }
}
