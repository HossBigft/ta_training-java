package page.Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class GoogleCloudCalculator {
    private FluentWait<WebDriver> wait;
    private WebDriver driver;

    private final String URL = "https://cloud.google.com/products/calculator";
    private final By calculatorOuterFrameBy = By.cssSelector("#cloud-site > devsite-iframe:nth-child(1) > iframe:nth-child(1)");
    private final By calculatorInnerFrameBy = By.cssSelector("#myFrame");
    private  final By serverTypeBy = By.cssSelector("md-tab-item[id='tab-item-1'] div[class='hexagon-in2']");
    private  final By numberOfInstancesFieldBy = By.cssSelector("[ng-model='listingCtrl.computeServer.quantity']");
    private  final By operatingSystemsDropdownBy = By.cssSelector("[ng-change=\"listingCtrl.applyConfidentialVmValidation('computeServer')\"]");
    private  final By freeOrBYOLSystem = By.cssSelector("[value='free']");
    private  final By vmClassDropdownBy = By.cssSelector("[placeholder='VM Class']");
    private  final By regularVmOptionBy = By.cssSelector("[value='regular']");
    private  final By seriesDropdownBy = By.cssSelector("[ng-model='listingCtrl.computeServer.series']");
    private  final By seriesN1OptionBy = By.cssSelector(".md-select-menu-container.md-active.md-clickable [value='n1']");
    private  final By machineTypeDropdownBy = By.cssSelector("[placeholder='Instance type']");
    private  final By n1Standard8MachineOptionBy = By.cssSelector("[value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']");
    private final By addGpuCheckboxBy = By.cssSelector("[ng-model='listingCtrl.computeServer.addGPUs']");
    private final By gpuTypeDropdownBy = By.cssSelector("[placeholder='GPU type']");
    private final By v100GpuTypeOptionBy = By.cssSelector("[value='NVIDIA_TESLA_V100']");
    private final By gpuNumberDropdownBy = By.cssSelector("[placeholder='Number of GPUs']");
    private final By oneGPUoption = By.cssSelector("[ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]'][value='1']");
    private final By localSsdDropdownBy = By.cssSelector("[ng-model='listingCtrl.computeServer.ssd']");
    private final By two375gbSsdOptionBy = By.cssSelector("[ng-repeat='item in listingCtrl.dynamicSsd.computeServer'][value='2']");
    private final By dataCenterLocationDropdownBy = By.cssSelector("[ng-model='listingCtrl.computeServer.location']");
    private final By europeWest3LocationOptionBy = By.cssSelector(".md-select-menu-container.cpc-region-select.md-active.md-clickable  [value='europe-west3']");
    private final By commitedUsageDropdownBy = By.cssSelector("[ng-model='listingCtrl.computeServer.cud']");
    private final By oneYearUsageOptionBy = By.cssSelector(".md-select-menu-container.md-active.md-clickable [value='1']");
    private final By addToEstimateButtonBy = By.cssSelector("[ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']");
    private final By emailEstimateButtonBy = By.cssSelector("[title='Email Estimate']");
    private final By emailFieldBy = By.cssSelector("[ng-model='emailQuote.user.email']");
    private final By sendEmailButtonBy = By.cssSelector("[ng-disabled='emailForm.$invalid']");
    private final By estimateValueBy = By.cssSelector(".cpc-cart-total");
    private final String estimatePrefix = "Total Estimated Cost: USD ";
    private final String estimatePostfix = " per 1 month";



    public GoogleCloudCalculator(WebDriver driver){
        this.driver=driver;
        wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
    }
    public GoogleCloudCalculator openPage(){
        driver.get(URL);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorOuterFrameBy));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorInnerFrameBy));
        return this;
    }
    public GoogleCloudCalculator selectComputerEngine(){
        wait.until(ExpectedConditions.elementToBeClickable(serverTypeBy)).click();
        return this;
    }
    public GoogleCloudCalculator withNumberOfInstances(Integer instancesNumber){
        WebElement instancesNumberField = wait.until(ExpectedConditions.elementToBeClickable(numberOfInstancesFieldBy));
        instancesNumberField.sendKeys(instancesNumber.toString());
        return this;
    }
    private void openDropdownAndSelectGiven(By dropdown, By option){
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public GoogleCloudCalculator withFreeOrBYOLOperatingSystem(){
        openDropdownAndSelectGiven(operatingSystemsDropdownBy,freeOrBYOLSystem);
        return this;
    }
    public  GoogleCloudCalculator withRegularVM(){
        openDropdownAndSelectGiven(vmClassDropdownBy,regularVmOptionBy);
        return this;
    }
    public  GoogleCloudCalculator withSeries1n(){
        openDropdownAndSelectGiven(seriesDropdownBy,seriesN1OptionBy);
        return this;
    }
    public GoogleCloudCalculator withInstanceTypeN1Standard8(){
        openDropdownAndSelectGiven(machineTypeDropdownBy,n1Standard8MachineOptionBy);
        return this;
    }
    public GoogleCloudCalculator withOneTeslaV100GPU(){
        wait.until(ExpectedConditions.elementToBeClickable(addGpuCheckboxBy)).click();
        openDropdownAndSelectGiven(gpuTypeDropdownBy,v100GpuTypeOptionBy);
        openDropdownAndSelectGiven(gpuNumberDropdownBy,oneGPUoption);
        return this;
    }
    public GoogleCloudCalculator withTwo375GbSSD(){
        openDropdownAndSelectGiven(localSsdDropdownBy,two375gbSsdOptionBy);
        return this;
    }
    public GoogleCloudCalculator withEuropeWest3DataCenter(){
        openDropdownAndSelectGiven(dataCenterLocationDropdownBy,europeWest3LocationOptionBy);
        return this;
    }
    public GoogleCloudCalculator withOneYearUsage(){
        openDropdownAndSelectGiven(commitedUsageDropdownBy,oneYearUsageOptionBy);
        return this;
    }
    public GoogleCloudCalculator addToEstimate(){
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButtonBy)).click();
        return this;
    }

    public void sendEstimateToEmail(String adress){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorOuterFrameBy));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorInnerFrameBy));
        wait.until(ExpectedConditions.elementToBeClickable(emailEstimateButtonBy)).click();
        WebElement emailField = driver.findElement(emailFieldBy);
        emailField.sendKeys(adress);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailButtonBy)).click();
        wait.until(ExpectedConditions.invisibilityOf(emailField));
    }
    public String getEstimateValue(){
        String estimate = wait.until(ExpectedConditions.visibilityOfElementLocated(estimateValueBy)).getText();
        int prefixLength = estimatePrefix.length();
        int postfixLength = estimatePostfix.length();
        return estimate.substring(prefixLength, estimate.length()-postfixLength);
    }





}
