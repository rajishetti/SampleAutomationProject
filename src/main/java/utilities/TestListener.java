package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = (WebDriver) result.getAttribute("webdriver");
        takeScreenshot(testName, driver);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        WebDriver driver = (WebDriver) result.getAttribute("webdriver");
        takeScreenshot(testName, driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    private void takeScreenshot(String testName, WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destinationFolder = "test-results/screenshots/" + testName + ".png";
        File dest = new File(destinationFolder);
        try {
            FileUtils.copyFile(sourceFile,dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
