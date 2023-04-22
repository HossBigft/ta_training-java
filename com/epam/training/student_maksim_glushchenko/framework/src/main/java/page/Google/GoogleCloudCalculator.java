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
    private  final By serverTypeBy = By.cssSelector("[role='tab']>[title='Compute Engine']");
    private  final By numberOfInstancesFieldBy = By.cssSelector("[name='quantity'][id='input_95']");
    private  final By operatingSystemsDropdownBy = By.cssSelector("[id='select_value_label_87']");
    private  final By freeOrBYOLSystem = By.cssSelector("[value='free']");
    private  final By vmClassDropdownBy = By.cssSelector("[placeholder='VM Class']");
    private  final By regularVmOptionBy = By.cssSelector("[value='regular']");
    private  final By seriesDropdownBy = By.cssSelector("[id='select_120']");
    private  final By seriesN1OptionBy = By.cssSelector("[aria-selected='true'][value='n1']");
    private  final By machineTypeDropdownBy = By.cssSelector("[placeholder='Instance type']");
    private  final By n1Standard8MachineOptionBy = By.cssSelector("[value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']");
    private final By addGpuCheckboxBy = By.cssSelector("[ng-model='listingCtrl.computeServer.addGPUs']");
    private final By gpuTypeDropdownBy = By.cssSelector("[placeholder='GPU type']");
    private final By v100GpuTypeOptionBy = By.cssSelector("[value='NVIDIA_TESLA_V100']");
    private final By gpuNumberDropdownBy = By.cssSelector("[placeholder='Number of GPUs']");
    private final By oneGPUoption = By.cssSelector("#select_option_492");
    private final By localSsdDropdownBy = By.cssSelector("[placeholder='Local SSD']#select_445");
    private final By two375gbSsdOptionBy = By.cssSelector("#select_option_471[ng-value='item.value']");
    private final By dataCenterLocationDropdownBy = By.cssSelector("#select_128[placeholder='Datacenter location']");
    private final By europeWest3LocationOptionBy = By.cssSelector("#select_option_252[value='europe-west3']");
    private final By commitedUsageDropdownBy = By.cssSelector("#select_135[placeholder='Committed usage']");
    private final By oneYearUsageOptionBy = By.cssSelector("#select_option_133");
    private final By addToEstimateButtonBy = By.cssSelector("form[name='ComputeEngineForm'] div[class='layout-align-end-start layout-row'] button[type='button']");
    private final By emailEstimateButtonBy = By.cssSelector("[id='Email Estimate']");
    private final By emailFieldBy = By.cssSelector("[ng-model='emailQuote.user.email']");
    private final By sendEmailButtonBy = By.cssSelector("[ng-disabled='emailForm.$invalid']");
    private final By estimateValueBy = By.cssSelector("md-list-item[role='listitem'] div[class='ng-binding']");


    public GoogleCloudCalculator(WebDriver driver){
        this.driver=driver;
        wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
    }
    public GoogleCloudCalculator openPage(){
        driver.get(URL);
        wait.until(ExpectedConditions.elementToBeClickable(serverTypeBy));
        return this;
    }
    public GoogleCloudCalculator withComputerEngine(){
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

    public void sendEstimatetoYopmail(String adress){
        wait.until(ExpectedConditions.elementToBeClickable(addToEstimateButtonBy)).click();
        wait.until(ExpectedConditions.elementToBeClickable(emailEstimateButtonBy)).click();
        WebElement emailField = driver.findElement(emailFieldBy);
        emailField.sendKeys(adress);
        wait.until(ExpectedConditions.elementToBeClickable(sendEmailButtonBy)).click();
        wait.until(ExpectedConditions.invisibilityOf(emailField));
    }
    public String getEstimateString(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(estimateValueBy)).getText();
    }





}
