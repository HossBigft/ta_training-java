package pageobjects.google.calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class DataCenterLocationMenu extends AbstractMenu<DataCenterLocation> {

    public DataCenterLocationMenu(FluentWait<WebDriver> wait){
        super(wait);
    }
    private final By MENU_BY = By.cssSelector("[ng-model='listingCtrl.computeServer.location']");
    private final String CSS_SELECTOR_BASE = ".md-select-menu-container.cpc-region-select.md-active.md-clickable  [value='";

    @Override
    public void selectOption(DataCenterLocation option){
        String selector = new StringBuilder(CSS_SELECTOR_BASE)
                .append(option.getTag())
                .append("']")
                .toString();
        By optionBy = By.cssSelector(selector);
        select(MENU_BY, optionBy);
    }
}
