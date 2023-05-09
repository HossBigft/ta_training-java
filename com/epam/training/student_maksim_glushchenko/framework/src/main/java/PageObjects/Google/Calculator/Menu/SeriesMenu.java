package PageObjects.Google.Calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.HashMap;
import java.util.Map;

public class SeriesMenu extends AbstractMenu<Series> {
    private final By MENU_BY = By.cssSelector("[ng-model='listingCtrl.computeServer.series']");

    private Map<Series, String> optionSelectors = new HashMap<Series, String>(Map.ofEntries(
            Map.entry(Series.N1, ".md-select-menu-container.md-active.md-clickable [value='n1']"),
            Map.entry(Series.N2, ".md-select-menu-container.md-active.md-clickable [value='n2']"),
            Map.entry(Series.E2, ".md-select-menu-container.md-active.md-clickable [value='e2']"),
            Map.entry(Series.N2D, ".md-select-menu-container.md-active.md-clickable [value='n2d']"),
            Map.entry(Series.T2A, ".md-select-menu-container.md-active.md-clickable [value='t2a']"),
            Map.entry(Series.T2D, ".md-select-menu-container.md-active.md-clickable [value='t2d']")
    ));

    public SeriesMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    @Override
    public void selectOption(Series option) {
        By optionBy = By.cssSelector(optionSelectors.get(option));
        select(MENU_BY, optionBy);
    }
}
