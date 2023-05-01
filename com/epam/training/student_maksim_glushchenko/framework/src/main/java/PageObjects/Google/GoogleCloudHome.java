package PageObjects.Google;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class GoogleCloudHome {
    private FluentWait<WebDriver> wait;
    private WebDriver driver;
    private Logger log = LogManager.getRootLogger();
    private final String URL = "https://cloud.google.com";
    private final By searchButtonBy = By.cssSelector(".devsite-searchbox") ;
    private final By searchFieldBy = By.cssSelector("[placeholder='Search']") ;
    private  final By submitButtonBy = By.cssSelector("[type='submit']");
    private final By resultItemBy = By.cssSelector(".gsc-webResult .gsc-result");
    private final By resultItemTitleBy = By.cssSelector(".gs-title");



    public GoogleCloudHome(WebDriver driver){
        this.driver=driver;
        wait=new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500));
    }
    public GoogleCloudHome openPage(){
        driver.get(URL);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchButtonBy));
        } catch (TimeoutException e){
            log.error("Failed to load Google Cloud homepage: " + e.getLocalizedMessage());
        }
        return this;
    }
    public GoogleCloudHome search(String query){
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(searchButtonBy));
        searchButton.click();
        WebElement searchField= wait.until(ExpectedConditions.elementToBeClickable(searchFieldBy));
        searchField.sendKeys(query);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(submitButtonBy));
        submitButton.click();
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultItemBy));
        } catch (TimeoutException e) {
            log.error("Nothing was found: " + e.getLocalizedMessage());
        }
        return this;
    }
    public Optional<GoogleCloudCalculator> findResultWithTitleAndOpen(String title){
        List<WebElement> results = driver.findElements(resultItemBy);
        for(WebElement result:results){
            String currTitle = result.findElement(resultItemTitleBy).getText();
            if(currTitle.equals(title)){
                return Optional.of(new GoogleCloudCalculator(driver));
            }
        }
        log.info("Item with title "+title+"\" not found"  );
        return Optional.empty();
    }




}
