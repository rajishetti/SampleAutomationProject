package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCasesPage extends BasePage{

    By addTestCasesSidebarLink = By.id("sidebar-cases-add");
    By testCaseTitleField = By.id("title");
    By typeDropdown = By.id("type_id_chzn");
    By preconditionField = By.id("custom_preconds");
    By stepsField = By.id("custom_steps");
    By expectedResultField = By.id("custom_expected");
    By addTestCaseButton = By.id("accept");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickOnAddNewTestCasesSidebar(){
        boolean isClickOnAddTestCase;
        try{
            driver.findElement(addTestCasesSidebarLink).click();
            isClickOnAddTestCase = true;
        }catch(Exception e){
            isClickOnAddTestCase = false;
        }
        return isClickOnAddTestCase;
    }

    public void insertTitle(String title){
        insertText(driver.findElement(testCaseTitleField), title);
    }

    public void selectTypeDropdown(String type){
        driver.findElement(typeDropdown).click();
        driver.findElement(By.id("type_id_chzn_o_5")).click();
    }

    public void insertPreconditionsField(String conditions){
        insertText(driver.findElement(preconditionField), conditions);
    }

    public void insertStepsField(String steps){
        insertText(driver.findElement(stepsField), steps);
    }

    public void insertExpectedResultField(String expectedResult){
        insertText(driver.findElement(expectedResultField), expectedResult);
    }

    public boolean clickOnAddTestCaseButton(){
        boolean isTestCaseAdded;
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try{
            driver.findElement(addTestCaseButton).click();
            isTestCaseAdded = true;
        }catch(Exception e){
            isTestCaseAdded = false;
        }
        return isTestCaseAdded;
    }
}
