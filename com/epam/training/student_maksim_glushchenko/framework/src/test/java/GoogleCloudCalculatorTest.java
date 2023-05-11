import driversingleton.DriverSingleton;

import utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.google.calculator.GoogleCloudCalculator;
import pageobjects.google.GoogleCloudHome;
import pageobjects.tempmail.YopMail;

import java.util.Optional;
@Listeners(TestListener.class)
public class GoogleCloudCalculatorTest {
    private WebDriver driver;
    private final String CALCULATOR_TITLE = "Google Cloud Platform Pricing Calculator";
    @BeforeMethod
     void setup(){
        driver=DriverSingleton.getDriver();
    }
    @AfterMethod(alwaysRun = true)
     void teardown(){
        DriverSingleton.closeDriver();
    }
    @Test
    public void testSearch(){
         Optional<GoogleCloudCalculator> googleCalculator = new GoogleCloudHome(driver)
                .openPage()
                .search(CALCULATOR_TITLE)
                .findResultWithTitleAndOpen(CALCULATOR_TITLE);
        Assert.assertTrue(googleCalculator.isPresent());

    }
    @Test
    public void testMail(){
        String mail = new YopMail(driver).openPage().openInbox().getAdress();
        Assert.assertTrue(mail.contains("@yopmail.com"));
    }
    @Test
    public void testCalculator(){
        String estimate = new GoogleCloudCalculator(driver)
                .openPage()
                .createValidInstance()
                .estimate()
                .getEstimateValue();
        Assert.assertTrue(!estimate.isEmpty());
    }

    @Test
    public void testEmailEstimate() {
        GoogleCloudCalculator calculator = new GoogleCloudCalculator(driver)
                .openPage()
                .createValidInstance()
                .estimate();
        String estimate = calculator.getEstimateValue();
        String calcTab = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.TAB);
        YopMail mail = new YopMail(driver);
        String mailAdress = mail.openPage().openInbox().getAdress();
        String mailTab = driver.getWindowHandle();

        driver.switchTo().window(calcTab);
        calculator.sendEstimateToEmail(mailAdress);

        driver.switchTo().window(mailTab);
        String emailEstimate = mail.getEstimateFromMail();

        Assert.assertEquals(estimate, emailEstimate);
    }
}
