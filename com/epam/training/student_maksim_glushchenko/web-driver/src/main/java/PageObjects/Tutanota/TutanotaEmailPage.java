package PageObjects.Tutanota;

import org.openqa.selenium.*;
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
    private final String sendButton="[title='Send']";
    private final String destinationAdressField ="input[aria-label='To']";
    private final String subjectField="input[aria-label='Subject']";
    private final String contentField= "[role='textbox']";

    private final String inboxFolderButton ="[title='Inbox']";
    private final String sentFolderButton ="[title='Sent']";
    private final String makeEmailNonConfidentialButton ="[title='Confidential']";
    private final String emailSentNotification ="#dialog-title";
    private final String mailItem = "[style*='display: list-item']";
    private final String mailItemUnread ="[class='dot bg-accent-fg']";
    private final String deleteButton ="[title='Delete']";
    private final  String sentSubject = "[class*='subject']";
    private final  String sentFromAdress =".flex.click.small.ml-between-s.items-center>.text-ellipsis";
    private  final String sentContent = "#mail-body";

    public TutanotaEmailPage(WebDriver driver) {
        this.driver = driver;
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(inboxFolderButton)));

    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void sendEmail(String destinationEmail, String subject, String content){
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(500))
                .pollingEvery(Duration.ofMillis(500));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(createButton)));
        WebElement compose = driver.findElement(By.cssSelector(createButton));
        compose.click();


        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(destinationAdressField)));
        WebElement destination= driver.findElement(By.cssSelector(destinationAdressField));
        destination.sendKeys(destinationEmail);
        removeAutofillEmails(destinationEmail);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(makeEmailNonConfidentialButton)));
        WebElement makeNonConfidentialButton = driver.findElement(By.cssSelector(makeEmailNonConfidentialButton));
        makeNonConfidentialButton.click();

        WebElement mailSubject= driver.findElement(By.cssSelector(subjectField));
        mailSubject.sendKeys(subject);

        WebElement mailContent= driver.findElement(By.cssSelector(contentField));
        mailContent.sendKeys(content);

        WebElement send = driver.findElement(By.cssSelector(sendButton));
        send.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(emailSentNotification)));

    }
    private void removeAutofillEmails(String email){
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(3))
                .pollingEvery(Duration.ofMillis(500));
        String removeAdressButton = "[role='menu'] :nth-child(2)";
        try {
            for (int i = 1; i < 3; i++) {
                String emailSelector = "[title*='<" + email.substring(0, email.length() - i) + ">']";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(emailSelector)));
                WebElement emailEntry = driver.findElement(By.cssSelector(emailSelector));
                emailEntry.click();

                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(removeAdressButton)));
                WebElement removeAdress = driver.findElement(By.cssSelector(removeAdressButton));
                removeAdress.click();

            }
        } catch (TimeoutException e){
            System.out.println("There are no autofill email pieces");
        }

    }

    public boolean isMailSent(String toEmail, String subject, String content){
        FluentWait wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(sentFolderButton)));
        WebElement sentFolder= driver.findElement(By.cssSelector(sentFolderButton));
        sentFolder.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(mailItem)));
        List<WebElement> sentMails = driver.findElements(By.cssSelector(mailItem));
        for(WebElement mail : sentMails){
            mail.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sentFromAdress)));
            WebElement destinationAdress= driver.findElement(By.cssSelector(sentFromAdress));
            WebElement mailSubject= driver.findElement(By.cssSelector(sentSubject));
            WebElement mailContent= driver.findElement(By.cssSelector(sentContent));

            if(toEmail.equals(destinationAdress.getText())
                    && subject.equals(mailSubject.getText())
                    && content.equals(mailContent.getText())
            ){
                return true;
            }
        }
        return false;
    }
    public void clearInbox(){
        FluentWait wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500));
        WebElement inbox = driver.findElement(By.cssSelector(inboxFolderButton));
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
    public void clearSentFolder(){
        clearFolder(sentFolderButton);
    }
    private void clearFolder(String folderSelector){
        FluentWait wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500));
        WebElement inbox = driver.findElement(By.cssSelector(folderSelector));
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
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(inboxFolderButton)));
        WebElement inbox = driver.findElement(By.cssSelector(inboxFolderButton));
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(sentFromAdress)));
        WebElement fromField= driver.findElement(By.cssSelector(sentFromAdress));
        String currFromEmail = fromField.getText().strip();

        WebElement subjectField= driver.findElement(By.cssSelector(sentSubject));
        String currSubject = subjectField.getText().strip();

        WebElement contentField= driver.findElement(By.cssSelector(sentContent));
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
