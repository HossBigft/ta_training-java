package pageobjects.tempmail;

import exceptions.PageFailedToLoadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;
import java.util.Optional;

public class YopMail {

    private FluentWait<WebDriver> wait;
    private WebDriver driver;
    private Logger log = LogManager.getRootLogger();
    private final String URL = "https://yopmail.com/email-generator";
    private final By emailFieldBy =By.cssSelector(".bname");
    private final By inboxButtonBy =By.cssSelector("[onclick='egengo();']");
    private final By refreshInboxButtonBy =By.cssSelector("#refresh");
    private final By emailItemBy = By.cssSelector(".lm");
    private final By emailContentEstimateCellBy = By.cssSelector("tbody tr td:nth-child(4)");
    private final String emailContentIframeID = "#ifmail";
    private final String emailItemIframeID = "#ifinbox";
    private final By captchaPopupBy =By.cssSelector("#r_parent");
    public YopMail(WebDriver driver){
        this.driver=driver;
        wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
    }
    public YopMail openPage() {
        driver.get(URL);
        try{
        wait.until(ExpectedConditions.elementToBeClickable(inboxButtonBy));
        } catch (
        TimeoutException e){
            log.error("Failed to load yopmail: " + e.getLocalizedMessage());
            throw new PageFailedToLoadException("Failed to load yopmail: " + e.getLocalizedMessage(), e);
        }
        return this;
    }
    public YopMail openInbox(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(inboxButtonBy)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(captchaPopupBy));
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldBy));
        return this;
    }
    public String getAdress(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldBy)).getText();
    }
    private Optional<String> waitForEmailToArriveAndGetText(int mins){
        WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(refreshInboxButtonBy));
        refreshButton.click();
        int waitAmount=(mins*60)/30;
        for(int i=0; i<waitAmount; i++){
            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector(emailContentIframeID)));
                WebElement estimateCell = wait.until(ExpectedConditions.elementToBeClickable(emailContentEstimateCellBy));
                return Optional.of(estimateCell.getText());
            } catch (TimeoutException e){
                System.out.println("Email not found");
            }
            driver.switchTo().defaultContent();
            refreshButton.click();
        }
        return Optional.empty();
    }
    public String getEstimateFromMail(){
        String estimate = waitForEmailToArriveAndGetText(10).get();
        return estimate.substring(4,estimate.length());


    }



}
