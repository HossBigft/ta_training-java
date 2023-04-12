package Yahoo;
import PageObjects.Tutanota.TutanotaEmailPage;
import PageObjects.Yahoo.YahooEmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.testng.Assert;
import org.testng.annotations.*;

public class YahooEmailTest {
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
    void closeWebDriver(){
        driver.quit();
    }
    @AfterTest
    void clearEmails(){
        setup();
        YahooEmailPage yahPage =  new YahooEmailPage(driver);
        yahPage.clearSentFolder();
        TutanotaEmailPage tutaEmailPage =  new TutanotaEmailPage(driver);
        tutaEmailPage.clearInbox();
        driver.quit();
    }


    @Test(priority = 0)
    public void testSendEmail(){
        YahooEmailPage emailPage =  new YahooEmailPage(driver);
        emailPage.sendEmail(tutaAdress, mailSubject, mailContent);
        Assert.assertTrue( emailPage.isMailSent(tutaAdress, mailSubject, mailContent));

    }
    @Test(priority = 1)
    public void testEmailReceivedAndUnread() {
        TutanotaEmailPage tutaEmailPage =  new TutanotaEmailPage(driver);
        Assert.assertTrue(tutaEmailPage.isEmailReceivedAndUnread(yahAdress, mailSubject, mailContent));

    }






}