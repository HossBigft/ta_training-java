import DriverSingleton.DriverSingleton;
import Service.TestDataReader;
import Utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import PageObjects.Google.GoogleCloudCalculator;
import PageObjects.Google.GoogleCloudHome;
import PageObjects.TempMail.YopMail;

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
    void testSearch(){
         Optional<GoogleCloudCalculator> googleCalculator = new GoogleCloudHome(driver)
                .openPage()
                .search(CALCULATOR_TITLE)
                .findResultWithTitleAndOpen(CALCULATOR_TITLE);
        Assert.assertTrue(googleCalculator.isPresent());

    }
    @Test
    void testMail(){
        String mail = new YopMail(driver).openPage().openInbox().getAdress();
        Assert.assertTrue(mail.contains("@yopmail.com"));
    }
    @Test
    void testCalculator(){
        String estimate = new GoogleCloudCalculator(driver)
                .openPage()
                .selectComputerEngine()
                .withNumberOfInstances(TestDataReader.getTestData("dev"))
                .withFreeOrBYOLOperatingSystem()
                .withRegularVM()
                .withSeries1n()
                .withInstanceTypeN1Standard8()
                .withOneTeslaV100GPU()
                .withTwo375GbSSD()
                .withEuropeWest3DataCenter()
                .withOneYearUsage()
                .addToEstimate()
                .getEstimateValue();
        Assert.assertTrue(!estimate.isEmpty());
    }
    @Test
    void testEmailEstimate(){
        GoogleCloudCalculator calculator= new GoogleCloudCalculator(driver)
                .openPage()
                .selectComputerEngine()
                .withNumberOfInstances(4)
                .withFreeOrBYOLOperatingSystem()
                .withRegularVM()
                .withSeries1n()
                .withInstanceTypeN1Standard8()
                .withOneTeslaV100GPU()
                .withTwo375GbSSD()
                .withEuropeWest3DataCenter()
                .withOneYearUsage()
                .addToEstimate();
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
