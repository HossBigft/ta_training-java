package PageObjects.Google.Calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class SsdQuantityMenu extends AbstractMenu<SsdQuantity> {
    private final By MENU_BY = By.xpath("//*[@ng-model='listingCtrl.computeServer.ssd']");
    private final String SDD_QUANTITY_SELECTOR_BASE = "//*[@ng-repeat='item in listingCtrl.dynamicSsd.computeServer' and @value='";

    public SsdQuantityMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    @Override
    public void selectOption(SsdQuantity option) {
        String quantitySelector = new StringBuilder(SDD_QUANTITY_SELECTOR_BASE)
                .append(option.getQuantityString())
                .append("']")
                .toString();
        By ssdQuantityOptionBy = By.xpath(quantitySelector);
        select(MENU_BY, ssdQuantityOptionBy);
    }
}
