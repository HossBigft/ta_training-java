package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class GoogleCloudHome {
    private FluentWait<WebDriver> wait;
    private WebDriver driver;

    private final String URL = "https://cloud.google.com";
    private final String searchField =".devsite-searchbox";
    private  final String searchSubmitButton = "[type='submit']";
    private final String resultsItem =".gsc-webResult .gsc-result";
}
