package Utils;

import DriverSingleton.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;



public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/"
                    + getCurrentTimeAsString() +
                    ".png"));
        } catch (IOException e) {
            log.error("failed to save screenshots: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenshot();
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onFinish(ITestContext contextFinish) {}

    @Override
    public void onStart(ITestContext contextStart) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
