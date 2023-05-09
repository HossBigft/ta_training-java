package PageObjects.Google.Calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.HashMap;
import java.util.Map;

public class OperatingSystemMenu extends AbstractMenu<OperatingSystem> {
    private final By menuBy = By.cssSelector("[ng-change=\"listingCtrl.applyConfidentialVmValidation('computeServer')\"]");
    private Map<OperatingSystem, String> optionSelectors = new HashMap<OperatingSystem, String>(Map.ofEntries(
            Map.entry(OperatingSystem.FREE, "[value='free']"),
            Map.entry(OperatingSystem.UBUNTU_PRO, "[value='ubuntu-pro']"),
            Map.entry(OperatingSystem.WINDOWS_SEVER, "[value='win']"),
            Map.entry(OperatingSystem.RED_HAT_ENTERPRISE_LINUX, "[value='rhel']"),
            Map.entry(OperatingSystem.RED_HAT_ENTERPRISE_LINUX_FOR_SAP, "[value='rhel-sap-ha']"),
            Map.entry(OperatingSystem.SLES, "[value='sles']"),
            Map.entry(OperatingSystem.SlES_12_FOR_SAP, "[value='sles-sap-12']"),
            Map.entry(OperatingSystem.SlES_15_FOR_SAP, "[value='sles-sap-15']"),
            Map.entry(OperatingSystem.SQL_SERVER_STANDARD, "[value='sql-standard']"),
            Map.entry(OperatingSystem.SQL_SERVER_WEB, "[value='sql-web']"),
            Map.entry(OperatingSystem.SQL_SERVER_ENTERPRISE, "[value='sql-enterprise']")
    ));

    public OperatingSystemMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    @Override
    public void selectOption(OperatingSystem option) {
        By optionBy = By.cssSelector(optionSelectors.get(option));
        select(menuBy, optionBy);
    }

}
