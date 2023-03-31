package PageObjects.Yahoo;

import org.openqa.selenium.WebDriver;

public class YahooHomePage {
    WebDriver driver;
    private final String homePageTitle ="Yahoo | Mail, Weather, Search, Politics, News, Finance, Sports &amp; Videos";
    public YahooHomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals(homePageTitle)) {
            throw new IllegalStateException("This is not Yahoo home page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public String getHomePageTitle() {
        return homePageTitle;
    }
}
