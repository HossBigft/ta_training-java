package PageObjects.Yahoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class YahooLoginPage {
    private final WebDriver driver;
    public static final String url ="https://login.yahoo.com/";
    private final String usernameBy= "#login-username";
    private final String passwordBy = "#login-passwd";
    private final String loginButton = "#login-signin";

    public YahooLoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(url)) {
            throw new IllegalStateException("This is not Yahoo sign in page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public YahooHomePage loginAsUser(String username, String password){
        return loginAsUser(username,password,3);
    }
    public YahooHomePage loginAsUser(String username, String password, int waitTime){
        FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(waitTime))
                .pollingEvery(Duration.ofMillis(500));

        WebElement loginField=driver.findElement(By.cssSelector(usernameBy));
        loginField.sendKeys(username);

        WebElement loginButton=driver.findElement(By.cssSelector(this.loginButton));
        loginButton.click();

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(passwordBy)));
            WebElement passField = driver.findElement(By.cssSelector(passwordBy));
            passField.sendKeys(password);
        } catch (Exception e){
            System.out.println("Sign in failed");
            return  null;
        }
        loginButton=driver.findElement(By.cssSelector(this.loginButton));
        loginButton.click();
        return new YahooHomePage(driver);
    }
}