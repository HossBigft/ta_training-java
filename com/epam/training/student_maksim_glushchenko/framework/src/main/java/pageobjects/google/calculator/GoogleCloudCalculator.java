package pageobjects.google.calculator;

import pageobjects.AbstractPage;
import pageobjects.google.calculator.Menu.*;
import exceptions.PageFailedToLoadException;
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
    private final By serverTypeBy = By.cssSelector("md-tab-item[id='tab-item-1'] div[class='hexagon-in2']");
    private final By numberOfInstancesFieldBy = By.cssSelector("[ng-model='listingCtrl.computeServer.quantity']");
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

    private void selectComputerEngine() {
        wait.until(ExpectedConditions.elementToBeClickable(serverTypeBy)).click();
    }

    private void enterNumberOfInstances(Integer number) {
        WebElement instancesNumberField = wait.until(ExpectedConditions.elementToBeClickable(numberOfInstancesFieldBy));
        instancesNumberField.sendKeys(number.toString());
    }

    public void selectOperatingSystem(OperatingSystem operatingSystem) {
        new OperatingSystemMenu(wait).selectOption(operatingSystem);
    }

    public void selectProvisioningModel(ProvisioningModel provisioningModel) {
        new ProvisioningModelMenu(wait).selectOption(provisioningModel);
    }

    private void selectSeries(Series series) {
        new SeriesMenu(wait).selectOption(series);
    }

    private void selectMachineType(MachineType machineType) {
        new MachineTypeMenu(wait).selectOption(machineType);
    }

    private void selectGpuType(GpuType gpuType) {
        new GpuTypeMenu(wait).selectOption(gpuType);

    }

    private void selectSsdQuantity(SsdQuantity ssdQuantity) {
        new SsdQuantityMenu(wait).selectOption(ssdQuantity);
    }

    private void selectDataCenterLocation(DataCenterLocation dataCenterLocation) {
        new DataCenterLocationMenu(wait).selectOption(dataCenterLocation);
    }

    private void selectCommitedUsage(CommitedUsage commitedUsage) {
        new CommitedUsageMenu(wait).selectOption(commitedUsage);
    }

    public GoogleCloudCalculator createInstance(CalculatorParameters parameters) {
        selectComputerEngine();

        enterNumberOfInstances(parameters.getInstancesNumber());
        if (parameters.getOperatingSystem() != OperatingSystem.NONE) {
            selectOperatingSystem(parameters.getOperatingSystem());
        }
        if (parameters.getProvisioningModel() != ProvisioningModel.NONE) {
            selectProvisioningModel(parameters.getProvisioningModel());
        }
        if (parameters.getSeries() != Series.NONE) {
            selectSeries(parameters.getSeries());
        }
        if (parameters.getMachineType() != MachineType.NONE) {
            selectMachineType(parameters.getMachineType());
        }
        if (parameters.getGpuType() != GpuType.NONE) {
            selectGpuType(parameters.getGpuType());
        }
        if (parameters.getSsdQuantity() != SsdQuantity.NONE) {
            selectSsdQuantity(parameters.getSsdQuantity());
        }
        if (parameters.getDataCenterLocation() != DataCenterLocation.NONE) {
            selectDataCenterLocation(parameters.getDataCenterLocation());
        }
        if (parameters.getCommitedUsage() != CommitedUsage.NONE) {
            selectCommitedUsage(parameters.getCommitedUsage());
        }
        return this;
    }

    public GoogleCloudCalculator createValidInstance() {
        CalculatorParameters validParemeters = new CalculatorParameters
                .Builder(4)
                .withOperatingSystem(OperatingSystem.FREE)
                .withProvisioningModel(ProvisioningModel.REGULAR)
                .withSeries(Series.N1)
                .withMachineType(MachineType.N1_STANDARD_8)
                .withGpuType(GpuType.NVIDIA_TESLA_V100_1)
                .withSsdQuantity(SsdQuantity.TWO_375GB)
                .withDataCenterLocation(DataCenterLocation.FRANKFURT)
                .withCommitedUsage(CommitedUsage.ONE_YEAR)
                .getParameters();
        createInstance(validParemeters);
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
