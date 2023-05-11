package pageobjects.google.calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class MachineTypeMenu extends AbstractMenu<MachineType> {
    private final By MENU_BY = By.xpath("//*[@placeholder='Instance type']");
    private final By n1Standard8MachineOptionBy = By.cssSelector("[value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']");
    private final String BASE_NAME = "CP-COMPUTEENGINE-VMIMAGE";
    private final String XPATH_MACHINE_TAG = "//*[@value='";


    public MachineTypeMenu(FluentWait<WebDriver> wait) {
        super(wait);
    }

    private String getXpathSelector(MachineType option) {
        String machineName = option.toString();
        return new StringBuilder(XPATH_MACHINE_TAG)
                .append(BASE_NAME)
                .append("-")
                .append(machineName)
                .append("']")
                .toString();
    }

    @Override
    public void selectOption(MachineType option) {
        By optionBy = By.xpath(getXpathSelector(option));
        select(MENU_BY, optionBy);
    }
}
