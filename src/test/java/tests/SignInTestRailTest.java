package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import utilities.TestListener;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;

@Listeners(TestListener.class)
public class SignInTestRailTest extends BaseTest{

    @Test
    public void validTestRailLogin() throws InterruptedException {

        String baseUrl = "https://codechallenge.testrail.io/";
        String projectName = "Sample Project 2";

        //Create Extent report
        ExtentReports report = createExtentReport();
        ExtentTest test = report.createTest("Code Challenge - TestRail");

        LoginTestRailPage loginPage = new LoginTestRailPage(driver);
        driver.get(baseUrl);

        //pass driver obj to TestListener to take screenshots
        ITestResult result = Reporter.getCurrentTestResult();
        result.setAttribute("webdriver", driver);

        //Login to TestRail account
        loginPage.insertUserName("youremail@gmail.com");
        loginPage.insertPassword("yourPassword");
        loginPage.uncheckKeepLoggedIn();
        boolean isLoginSuccessful = loginPage.clickLoginButton();
        Assert.assertTrue(isLoginSuccessful,"Failed to login upon clicking on Login button");
        Assert.assertEquals(driver.getCurrentUrl(),baseUrl + "index.php?/dashboard");
        test.pass("Passed login to TestRail account");

        //Click on Add new project n Dashboard
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickNewProjectButton();
        Assert.assertEquals(driver.getCurrentUrl(),baseUrl + "index.php?/admin/projects/add/1");
        test.pass("Came to the Dashboard page");

        //create a new project and enter details
        AddProjectPage addProjectPage = new AddProjectPage(driver);
        test.pass("Came to the Project tab on Project page");
        addProjectPage.insertProjectName(projectName);
        addProjectPage.insertProjectAnnouncement("This will show on the overview page");
        addProjectPage.selectSingleRepositoryRecommended();

        //Select access level as 'Lead' from the Access Tab
        addProjectPage.clickAccessTab();
        test.pass("Came to the Access tab on Project page");
        addProjectPage.clickProjectAccessDropdown();
        addProjectPage.selectLeadFromAccessDropdown();

        //Save new project details
        addProjectPage.clickProjectTab();
        addProjectPage.clickAddProjectButton();
        Assert.assertEquals(driver.getCurrentUrl(), baseUrl + "index.php?/admin/projects/overview");
        test.pass("Added the new project by clicking on the Add Project button on Projects Page");

        //Click on Dashboard link to go to Dashboard view
        ProjectsOverviewPage projectsOverviewPage = new ProjectsOverviewPage(driver);
        boolean isOnDashboardPage = projectsOverviewPage.clickDashboardLink();
        Assert.assertTrue(isOnDashboardPage, "Could not go to Dashboard Page from Project Overview page");
        test.pass("Came to Dashboard Page from Project Overview page");

        String projectURL = dashboardPage.getProjectId(projectName);
        //get all a within class 'table summary summary-auto'
        //look for Test cases link that as the same href as the above projectUrl
        //click and create test
        boolean clickedOnTestCasesLink = dashboardPage.clickOnTestCasesForProject(projectURL);
        Assert.assertTrue(clickedOnTestCasesLink, "Did not click on Test Cases link for the Project on Dashboard Page");
        test.pass("Came to Test Cases page from Dashboard page");

        TestCasesPage testCasesPage = new TestCasesPage(driver);
        boolean isAddTestCaseClick = testCasesPage.clickOnAddNewTestCasesSidebar();
        Assert.assertTrue(isAddTestCaseClick, "Failed while clicking on Add Test Case in sidebar");
        test.pass("Clicked on Add Test Case in Side Bar");

        testCasesPage.insertTitle("Test scroll to end of page");
        testCasesPage.selectTypeDropdown("Functional");
        testCasesPage.insertPreconditionsField("Page is loaded");
        testCasesPage.insertStepsField("1.Login t TestRail 2. Create Project");
        testCasesPage.insertExpectedResultField("Scroll till end of page");
        boolean isTestCaseAdded = testCasesPage.clickOnAddTestCaseButton();
        Assert.assertTrue(isTestCaseAdded,"Failed to save new test case");
        test.pass("Saved new test case in project");

        report.flush();
    }
}
