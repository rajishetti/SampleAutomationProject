package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DashboardPage extends BasePage{

    By addProjectButton = By.id("sidebar-projects-add");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void clickNewProjectButton(){
        driver.findElement(addProjectButton).click();
    }

    public String getProjectId(String projectName) {
        //if there are multiple projects with same name , fetch the one that was created recently
        List<WebElement> links = driver.findElements(By.linkText(projectName));
        System.out.println("This is the latest " + links.get(links.size()-1).getAttribute("href"));
        String projectUrl = links.get(links.size()-1).getAttribute("href");
        //get the string after the last '/' which will be the project id
        return projectUrl.substring(projectUrl.lastIndexOf('/')+1);
    }

    public boolean clickOnTestCasesForProject(String projectId) {
        List<WebElement> links = driver.findElements(By.linkText("Test Cases"));   // Using list web-element get all web-elements, whose classname name as "without"
        String currentProjectIdSubString, testCaseUrl;
        boolean isProjectFoundAndClickedOnTestCasesLink = false;

        for(int i = 0;i<links.size();i++) {
            testCaseUrl = links.get(i).getAttribute("href");
            currentProjectIdSubString = testCaseUrl.substring(testCaseUrl.lastIndexOf('/')+1);

            //check if the Project Id in the list matches projectId param value
            if(currentProjectIdSubString.equals(projectId)) {
                System.out.println(links.get(i).getAttribute("href"));   //Using for loop getting one by one links name.

                String locator = "#project-" + projectId + " > div:nth-child(2) > div:nth-child(2) > a:nth-child(4)";
                WebElement projectTestCasesLink = driver.findElement(By.cssSelector(locator));

                //scroll to the end of the page until the element is found
                //((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                isProjectFoundAndClickedOnTestCasesLink = scrollDownToProject(projectTestCasesLink, locator);
                if(isProjectFoundAndClickedOnTestCasesLink) {
                    break;
                }
            }
        }
        return isProjectFoundAndClickedOnTestCasesLink;
    }

    private boolean scrollDownToProject(WebElement scrollToElement, String locator){
        boolean elementFound = false;

        Actions actions = new Actions(driver);

        while(!elementFound) {
            //keys to scroll down the page
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
            try {
                //release any pressed keys
                actions
                        .sendKeys( Keys.CONTROL, Keys.DIVIDE )
                        .keyUp( Keys.CONTROL )
                        .build()
                        .perform();
                scrollToElement.click();
                elementFound = true;
            }catch(ElementClickInterceptedException e) {
                elementFound = false;
            }

        }
        return elementFound;
    }
}
