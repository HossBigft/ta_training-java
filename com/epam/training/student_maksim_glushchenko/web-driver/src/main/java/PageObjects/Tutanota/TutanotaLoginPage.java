package PageObjects.Tutanota;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class TutanotaLoginPage {
    WebDriver driver;
    public static final String url = "https://mail.tutanota.com/login/";
    private final String usernameBy = "input[aria-label='Email address']";
    private final String passwordBy = "input[aria-label='Password']";
    private final String loginButton = ".button-content.flex.items-center.login.plr-button.justify-center";

    public TutanotaLoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().equals(url)) {
            throw new IllegalStateException("This is not Tutanota sign in page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    public TutanotaEmailPage loginAsUser(String username, String password){
        FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(500));

        WebElement loginField=driver.findElement(By.cssSelector(usernameBy));
        loginField.sendKeys(username);

        WebElement passField = driver.findElement(By.cssSelector(passwordBy));
        WebElement loginButton=driver.findElement(By.cssSelector(this.loginButton));
        passField.sendKeys(password);
        loginButton.click();
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
        } catch (TimeoutException e){
            System.out.println("Login failed");
            return null;
        }
        return new TutanotaEmailPage(driver);
    }
}

