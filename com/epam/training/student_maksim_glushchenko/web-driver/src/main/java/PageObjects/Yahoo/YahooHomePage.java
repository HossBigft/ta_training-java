package PageObjects.Yahoo;

import org.openqa.selenium.WebDriver;

public class YahooHomePage {
    WebDriver driver;
    private final String pageTitle ="Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports & Videos";
    public YahooHomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals(pageTitle)) {
            throw new IllegalStateException("This is not Yahoo home page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public String getTitle() {
        return pageTitle;
    }
}
