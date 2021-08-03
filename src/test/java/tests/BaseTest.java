package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/Users/rshetti/IdeaProjects/chromedriver");
        //initialize the driver
        driver = new ChromeDriver(); //get this from Jenkins or CI/CD as to which browser to run on
    }
    /*public void setup(String browserName){ //you get this from Jenkins or CI/CD as to which browser to run on
        if(browserName.equals("Chrome"))
            driver = new ChromeDriver();
        else
            driver = new FirefoxDriver();
    }*/

    public ExtentReports createExtentReport(){
        ExtentSparkReporter spark = new ExtentSparkReporter("test-results/extent.html");
        ExtentReports report = new ExtentReports();
        report.attachReporter(spark);
        return report;
    }

    @AfterMethod
    public void tearDown() {
        //1. take a screenshot if fail
        //2. then close the browser
        driver.quit();
    }
}
