package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectsOverviewPage extends BasePage{

    //using FindBy library
    @FindBy(id = "navigation-dashboard")
    WebElement dashboardLink;

    public ProjectsOverviewPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean clickDashboardLink(){
        boolean isOnDashboardPage = true;
        try {
            dashboardLink.click();
        } catch(Exception e){
            isOnDashboardPage = false;
        }
        return isOnDashboardPage;
    }
}
