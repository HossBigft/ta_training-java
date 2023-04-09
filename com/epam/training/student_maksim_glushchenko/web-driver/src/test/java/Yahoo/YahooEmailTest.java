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
//        options.addArguments("--headless=new");
        options.addArguments("--profile-directory=Profile 1");
        options.addArguments("--user-data-dir="+System.getenv("chromeProfilePath"));
        driver = new ChromeDriver(options);
    }
    @AfterMethod
    void closeWebDriver(){
        driver.quit();
    }


    @Test
    public void testSendEmail(){
        YahooEmailPage emailPage =  new YahooEmailPage(driver);
        emailPage.sendEmail(tutaAdress, mailSubject, mailContent);
        Assert.assertTrue( emailPage.isMailSent(tutaAdress, mailSubject, mailContent));
        emailPage.clearSentFolder();
    }
    @Test
    public void testCorrectContentOfSentMail() {

        TutanotaEmailPage tutaEmailPage =  new TutanotaEmailPage(driver);
        Assert.assertTrue( tutaEmailPage.isEmailReceivedAndUnread(yahAdress, mailSubject, mailContent));
        tutaEmailPage.clearInbox();
    }






}