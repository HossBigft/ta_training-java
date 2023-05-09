package PageObjects;

import Service.PageFailedToLoadException;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected final int  WAIT_TIMEOUT_SECONDS= 30;
    protected abstract AbstractPage openPage() throws PageFailedToLoadException;
    protected AbstractPage(WebDriver driver){
        this.driver=driver;
    }
}
