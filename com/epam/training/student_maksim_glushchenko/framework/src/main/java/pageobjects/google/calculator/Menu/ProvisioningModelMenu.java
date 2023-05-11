package pageobjects.google.calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.HashMap;
import java.util.Map;


public class ProvisioningModelMenu extends AbstractMenu<ProvisioningModel> {
    private final By menuBy = By.cssSelector("[placeholder='VM Class']");

    private Map<ProvisioningModel, String> optionSelectors = new HashMap<ProvisioningModel, String>(Map.ofEntries(
            Map.entry(ProvisioningModel.REGULAR, "//*[@value=\"regular\"]"),
            Map.entry(ProvisioningModel.SPOT, "//*[@value=\"preemptible\"]")
    ));

    public ProvisioningModelMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    @Override
    public void selectOption(ProvisioningModel option) {
        By optionBy = By.xpath(optionSelectors.get(option));
        select(menuBy, optionBy);
    }

}
