package pageobjects.google.calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class CommitedUsageMenu extends AbstractMenu<CommitedUsage> {
    private final By MENU_BY = By.xpath("//*[@ng-model='listingCtrl.computeServer.cud']");
    private final String XPATH_SELECTOR_BASE = "//*[@class='md-select-menu-container md-active md-clickable']  //*[@value='";

    public CommitedUsageMenu(FluentWait<WebDriver> wait){
        super(wait);
    }

    @Override
    public void selectOption(CommitedUsage option) {
        String selector = new StringBuilder(XPATH_SELECTOR_BASE)
                .append(option.getValueString())
                .append("']")
                .toString();
        By optionBy = By.xpath(selector);
        select(MENU_BY, optionBy);
    }
}


