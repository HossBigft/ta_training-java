package PageObjects.Google.Calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class GpuTypeMenu extends AbstractMenu<GpuType> {

    private final By ADD_GPU_CHECKBOX_BY = By.cssSelector("[ng-model='listingCtrl.computeServer.addGPUs']");
    private final By MENU_BY = By.cssSelector("[placeholder='GPU type']");
    private final String GPU_QUANTITY_BASE_SELECTOR = "[ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]'][value='";
    private final String GPU_TYPE_SELECTOR_BASE = "[value='";
    private final By GPU_QUANTITY_MENU_BY = By.cssSelector("[placeholder='Number of GPUs']");
    public GpuTypeMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    @Override
    public void selectOption(GpuType option) {
        String gpuTypeSelector = new StringBuilder(GPU_TYPE_SELECTOR_BASE)
                .append((option.toString()).substring(0, option.toString().length() - 2))
                .append("']")
                .toString();
        By gpuOptionBy = By.cssSelector(gpuTypeSelector);

        String gpuQuantitySelector = new StringBuilder(GPU_QUANTITY_BASE_SELECTOR)
                .append(option.toString().substring(option.toString().length() - 1, option.toString().length()))
                .append("']")
                .toString();
        By gpuQuantityBy = By.cssSelector(gpuQuantitySelector);

        wait.until(ExpectedConditions.elementToBeClickable(ADD_GPU_CHECKBOX_BY)).click();
        select(MENU_BY, gpuOptionBy);
        select(GPU_QUANTITY_MENU_BY, gpuQuantityBy);
    }
}
