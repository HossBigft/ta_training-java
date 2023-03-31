package PageObjects.Tutanota;

import org.openqa.selenium.WebDriver;

public class TutanotaEmailPage {
    private WebDriver driver;
    private final String emailPageTitle =System.getenv("tutanota_login")+" - Tutanota";
    public TutanotaEmailPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals(emailPageTitle)) {
            throw new IllegalStateException("This is not Tutanota email page," +
                    " current page is: " +"Title: "+driver.getTitle()+  driver.getCurrentUrl())
                    ;
        }
    }

    public String getEmailPageTitle() {
        return emailPageTitle;
    }
}
