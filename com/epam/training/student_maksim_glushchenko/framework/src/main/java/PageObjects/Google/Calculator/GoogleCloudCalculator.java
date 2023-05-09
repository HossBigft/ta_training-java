package PageObjects.Google.Calculator;

import PageObjects.AbstractPage;
import Service.PageFailedToLoadException;
import Service.TestDataReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class GoogleCloudCalculator extends AbstractPage {
    private final FluentWait<WebDriver> wait;
    private final Logger log = LogManager.getRootLogger();

    private final String URL = "https://cloud.google.com/products/calculator";
    private final By calculatorOuterFrameBy = By.cssSelector("#cloud-site > devsite-iframe:nth-child(1) > iframe:nth-child(1)");
    private final By calculatorInnerFrameBy = By.cssSelector("#myFrame");
    private final By addToEstimateButtonBy = By.cssSelector("[ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']");
    private final By emailEstimateButtonBy = By.cssSelector("[title='Email Estimate']");
    private final By emailFieldBy = By.cssSelector("[ng-model='emailQuote.user.email']");
    private final By sendEmailButtonBy = By.cssSelector("[ng-disabled='emailForm.$invalid']");
    private final By estimateValueBy = By.cssSelector(".cpc-cart-total");
    private final String estimatePrefix = "Total Estimated Cost: USD ";
    private final String estimatePostfix = " per 1 month";


    public GoogleCloudCalculator(WebDriver driver) {
        super(driver);
        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofMillis(500));
    }

    @Override
    public GoogleCloudCalculator openPage() {
        driver.get(URL);
        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorOuterFrameBy));
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorInnerFrameBy));
        } catch (TimeoutException e) {
            log.error("Failed to load Google Cloud calculator: " + e.getLocalizedMessage());
            throw new PageFailedToLoadException("Failed to load Google Cloud calculator: " + e.getLocalizedMessage(), e);
        }

        return this;
    }

    public GoogleCloudCalculator createValidInstance() {
        new CalculatorForm(wait)
                .selectComputerEngine()
                .withNumberOfInstances(TestDataReader.getTestData("test.data.numberOfInstances"))
                .withFreeOrBYOLOperatingSystem()
                .withRegularVM()
                .withSeries1n()
                .withInstanceTypeN1Standard8()
                .withOneTeslaV100GPU()
                .withTwo375GbSSD()
                .withFrankfurtDataCenter()
                .withOneYearUsage();
        return this;
    }


    public GoogleCloudCalculator estimate() {
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButtonBy)).click();
        log.info("Google Cloud estimate created");
        return this;
    }

    public void sendEstimateToEmail(String adress) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorOuterFrameBy));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorInnerFrameBy));
        wait.until(ExpectedConditions.elementToBeClickable(emailEstimateButtonBy)).click();
        WebElement emailField = driver.findElement(emailFieldBy);
        emailField.sendKeys(adress);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailButtonBy)).click();
        wait.until(ExpectedConditions.invisibilityOf(emailField));
        log.info("Estimate sent to email");
    }

    public String getEstimateValue() {
        String estimate = wait.until(ExpectedConditions.visibilityOfElementLocated(estimateValueBy)).getText();
        int prefixLength = estimatePrefix.length();
        int postfixLength = estimatePostfix.length();
        return estimate.substring(prefixLength, estimate.length() - postfixLength);
    }


}
