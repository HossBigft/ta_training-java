package page.TempMail;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;


import java.time.Duration;
import java.util.Optional;

public class Yopmail {

    private FluentWait<WebDriver> wait;
    private WebDriver driver;
    private final String URL = "https://yopmail.com/email-generator";
    private final By emailFieldBy =By.cssSelector(".bname");
    private final By inboxButtonBy =By.cssSelector("[onclick='egengo();']");
    private final By refreshInboxButtonBy =By.cssSelector("#refresh");
    private final By emailItemBy = By.cssSelector(".lm");
    private final By emailContentEstimateCellBy = By.cssSelector("tbody tr td:nth-child(4)");
    private final By emailContentBy =By.cssSelector("#mail");
    public Yopmail(WebDriver driver){
        this.driver=driver;
        wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
    }
    public Yopmail openPage(){
        driver.get(URL);
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldBy));
        return this;
    }
    public Yopmail openInbox(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(inboxButtonBy)).click();
        wait.until(ExpectedConditions.elementToBeClickable(emailFieldBy));
        return this;
    }
    public String getAdress(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldBy)).getText();
    }
    private Optional<WebElement> waitForEmailToArriveAndOpen(int mins){
        WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(refreshInboxButtonBy));
        int waitAmount=(mins*60)/30;
        for(int i=0; i<waitAmount; i++){
            try {
                wait.until(ExpectedConditions.elementToBeClickable(emailItemBy)).click();
                return Optional.of(wait.until(ExpectedConditions.elementToBeClickable(emailContentEstimateCellBy)));
            } catch (TimeoutException e){
                System.out.println("Email not found");
            }
            refreshButton.click();
        }
        return Optional.empty();
    }
    public Optional<String> getEstimateFromMail(){
        String estimate;
        if(waitForEmailToArriveAndOpen(10).isPresent()) {
            WebElement email = waitForEmailToArriveAndOpen(10).get();
            estimate=email.getText();
            return Optional.of(estimate.substring(estimate.length()-4,estimate.length()));
        }else {
            return Optional.empty();
        }

    }



}
