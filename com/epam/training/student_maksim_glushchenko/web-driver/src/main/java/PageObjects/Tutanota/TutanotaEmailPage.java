package PageObjects.Tutanota;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class TutanotaEmailPage {
    private WebDriver driver;

    public static final String url = "https://mail.tutanota.com/mail/";
    public static final String adress = System.getenv("tutanota_login");
    private final String pageTitle =System.getenv("tutanota_login")+" - Tutanota";
    private final String createButton ="[title='New email']";
    private final String inboxFolder ="[title='Inbox']";
    private final String mailItem = "[style*='display: list-item']";
    private final String mailItemUnread ="[class='dot bg-accent-fg']";
    private final String deleteButton ="[title='Delete']";
    private final  String mailSubjectField= "[class*='subject']";
    private final  String mailFromAdressField=".small.flex .text-break";
    private  final String mailContentField = "#mail-body";

    public TutanotaEmailPage(WebDriver driver) {
        this.driver = driver;
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(inboxFolder)));

    }

    public String getPageTitle() {
        return pageTitle;
    }
    public void clearInbox(){
        FluentWait wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500));
        WebElement inbox = driver.findElement(By.cssSelector(inboxFolder));
        inbox.click();
        while (!driver.findElements(By.cssSelector(mailItem)).isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(mailItem)));
            WebElement mail = driver.findElement(By.cssSelector(mailItem));
            mail.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(deleteButton)));
            WebElement delete = driver.findElement(By.cssSelector(deleteButton));
            delete.click();

            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(mailItem), driver.findElements(By.cssSelector(mailItem)).size()));
        }
    }
    public boolean isEmailReceivedAndUnread(String fromEmail, String subject, String content){
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(inboxFolder)));
        WebElement inbox = driver.findElement(By.cssSelector(inboxFolder));
        inbox.click();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(mailItemUnread)));
        } catch (TimeoutException e){
            System.out.println("There are no unread emails");
            return false;
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(mailItem)));
        WebElement mail = driver.findElement(By.cssSelector(mailItem));
        mail.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(mailFromAdressField)));
        WebElement fromField= driver.findElement(By.cssSelector(mailFromAdressField));
        String currFromEmail = fromField.getText().strip();

        WebElement subjectField= driver.findElement(By.cssSelector(mailSubjectField));
        String currSubject = subjectField.getText().strip();

        WebElement contentField= driver.findElement(By.cssSelector(mailContentField));
        String currContent = contentField.getText().strip();

        if(currFromEmail.equals(fromEmail) && currSubject.equals(subject) && currContent.equals(content)){
            return  true;
        } else{
            System.out.println("Data is not matching\n"+"Given adress: \""+fromEmail +"\"\nActual adress: \""+currFromEmail  +"\""+currFromEmail.equals(fromEmail)+"\n"
            +"Given subject: " +subject+"\nActual subject: "+currSubject+" "+currSubject.equals(subject)+"\n"
            +"Given content: "+content+"\nActual content: "+currContent +" "+ currContent.equals(content));
            return false;
        }
    }

}
