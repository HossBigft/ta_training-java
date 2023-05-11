package pageobjects.google.calculator.Menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public abstract class AbstractMenu<E extends Enum<E>>{
    protected final FluentWait<WebDriver> wait;
    protected AbstractMenu(FluentWait<WebDriver> wait){
        this.wait=wait;
    }

    protected void select(By menu, By option) {
        wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public abstract void selectOption(E option);

}
