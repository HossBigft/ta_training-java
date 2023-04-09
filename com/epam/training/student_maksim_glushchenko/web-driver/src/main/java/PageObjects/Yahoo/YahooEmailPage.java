package PageObjects.Yahoo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substring;

public class YahooEmailPage {
    WebDriver driver;
    public static final String url = "https://mail.yahoo.com/";
    public static final String adress = System.getenv("yahoo_login")+"@yahoo.com";
    private final String pageTitle = "Yahoo Mail";
    private final String composeButton = "[data-test-id='compose-button']";
    private final String destinationField = "#message-to-field";
    private final String subjectField = "input[placeholder='Subject']";
    private final String contentField = "div[aria-label='Message body']";
    private final String sendButton= "button[title='Send this email']";
    private final String notificationCloseButton = "button[title='Close notification'] span[class='D_F ab_C gl_C W_6D6F']";

    private final String sentFolderlUrl ="https://mail.yahoo.com/d/folders/2";

    private final String selectAllCheckBox = "[data-test-id=\"checkbox\"]";

    private final String deleteButton ="button[title='Delete the selected messages'] span[class='D_X em_N o_h X_eo6 G_e t_l i_N'] span";

    private  final  String sentMailsItems = "[role='article']";
    public YahooEmailPage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
    }

    public void sendEmail(String destinationEmail, String subject, String content){
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));

        WebElement compose = driver.findElement(By.cssSelector(composeButton));
        compose.click();

        WebElement destination= driver.findElement(By.cssSelector(destinationField));
        destination.sendKeys(destinationEmail);

        WebElement mailSubject= driver.findElement(By.cssSelector(subjectField));
        mailSubject.sendKeys(subject);

        WebElement mailContent= driver.findElement(By.cssSelector(contentField));
        mailContent.sendKeys(content);

        WebElement send = driver.findElement(By.cssSelector(sendButton));
        send.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(notificationCloseButton)));
        WebElement closeNotification= driver.findElement(By.cssSelector(notificationCloseButton));
        closeNotification.click();

    }

    public void clearSentFolder(){
        driver.get(sentFolderlUrl);
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectAllCheckBox)));

        WebElement selectAll = driver.findElement(By.cssSelector(selectAllCheckBox));
        selectAll.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(deleteButton)));

        WebElement delete = driver.findElement(By.cssSelector(deleteButton));
        delete.click();

    }

    public boolean isMailSent(String toEmail, String subject, String content){
        driver.get(sentFolderlUrl);
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectAllCheckBox)));



        List<WebElement> sentMails = driver.findElements(By.cssSelector(sentMailsItems));
        for(WebElement mail : sentMails){
            String mailToAdress = mail.getText().split("\\R")[0];
            String mailSubject = mail.getText().split("\\R")[1];
            String mailContent = mail.getText().split("\\R")[2];
            if(mailToAdress.equals(toEmail)
                    && mailSubject.equals(subject)
                    && mailContent.equals(content)
            ){
                return true;
            }
        }
        return false;
    }




}
