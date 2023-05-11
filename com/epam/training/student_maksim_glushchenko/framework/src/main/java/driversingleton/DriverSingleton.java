package driversingleton;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions fOptions = new FirefoxOptions();
                    fOptions.addArguments("--width=1920");
                    fOptions.addArguments("--height=1080");
                    driver = new FirefoxDriver(fOptions);
                    break;
                default:
                    System.setProperty("webdriver.http.factory", "jdk-http-client");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--window-size=1280,768");
                    options.addArguments("--start-maximized");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
