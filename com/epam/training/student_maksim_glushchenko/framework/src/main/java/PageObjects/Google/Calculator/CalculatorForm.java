package PageObjects.Google.Calculator;

import PageObjects.Google.Calculator.Menu.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


public class CalculatorForm {
    private final FluentWait<WebDriver> wait;
    private final By serverTypeBy = By.cssSelector("md-tab-item[id='tab-item-1'] div[class='hexagon-in2']");
    private final By numberOfInstancesFieldBy = By.cssSelector("[ng-model='listingCtrl.computeServer.quantity']");

    public CalculatorForm(FluentWait<WebDriver> wait) {
        this.wait = wait;
    }

    public PageObjects.Google.Calculator.CalculatorForm selectComputerEngine() {
        wait.until(ExpectedConditions.elementToBeClickable(serverTypeBy)).click();
        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withNumberOfInstances(Integer instancesNumber) {
        WebElement instancesNumberField = wait.until(ExpectedConditions.elementToBeClickable(numberOfInstancesFieldBy));
        instancesNumberField.sendKeys(instancesNumber.toString());
        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withFreeOrBYOLOperatingSystem() {
        new OperatingSystemMenu(wait).selectOption(OperatingSystem.SLES);
        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withRegularVM() {
        new ProvisioningModelMenu(wait).selectOption(ProvisioningModel.REGULAR);

        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withSeries1n() {
        new SeriesMenu(wait).selectOption(Series.N1);

        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withInstanceTypeN1Standard8() {
        new MachineTypeMenu(wait).selectOption(MachineType.N1_STANDARD_8);

        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withOneTeslaV100GPU() {
        new GpuTypeMenu(wait).selectOption(GpuType.NVIDIA_TESLA_P100_1);

        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withTwo375GbSSD() {
        new SsdQuantityMenu(wait).selectOption(SsdQuantity.TWO_375GB);
        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withFrankfurtDataCenter() {
        new DataCenterLocationMenu(wait).selectOption(DataCenterLocation.FRANKFURT);
        return this;
    }

    public PageObjects.Google.Calculator.CalculatorForm withOneYearUsage() {
        new CommitedUsageMenu(wait).selectOption(CommitedUsage.ONE_YEAR);
        return this;
    }
}


