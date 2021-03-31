package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddProjectPage extends BasePage{

    @FindBy(id ="projects-tabs-project")
    WebElement projectTab;

    @FindBy(id = "name")
    WebElement projectNameField;

    @FindBy(id = "announcement")
    WebElement announcementField;

    @FindBy(id ="suite_mode_single")
    WebElement singleRepositoryRecommendedRadioButton;

    @FindBy(id ="suite_mode_single_baseline")
    WebElement singleRepositoryBaselineRadioButton;

    @FindBy(id ="suite_mode_multi")
    WebElement multipleTestSuitesRadioButton;

    @FindBy(id ="accept")
    WebElement addProjectButton;

    @FindBy(id ="projects-tabs-access")
    WebElement accessTab;

    @FindBy(id ="access")
    WebElement accessDropdown;

    public AddProjectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void insertProjectName(String projectName) {
        insertText(projectNameField, projectName);
    }

    public void insertProjectAnnouncement(String announcementText) {
        insertText(announcementField, announcementText);
    }

    public void selectSingleRepositoryRecommended(){
        if(!singleRepositoryRecommendedRadioButton.isSelected()) {
            singleRepositoryRecommendedRadioButton.click();
        }
    }

    public void clickAddProjectButton() {
        //scroll to the end of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        addProjectButton.click();
    }

    public void clickProjectTab(){
        projectTab.click();
    }

    public void clickAccessTab(){
        accessTab.click();
    }

    public void clickProjectAccessDropdown(){
        accessDropdown.click();
    }

    public void selectLeadFromAccessDropdown(){
        Select drpAccess = new Select(accessDropdown);
        drpAccess.selectByValue("1");
    }
}
