package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginTestRailPage extends BasePage{

    @FindBy(id = "name")
    WebElement userNameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "#rememberme")
    WebElement keepLoggedInCheckbox;

    @FindBy(id = "button_primary")
    WebElement loginButton;

    public LoginTestRailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void insertUserName(String userName) {
        insertText(userNameField, userName);
    }

    public void insertPassword(String password) {
        insertText(passwordField, password);
    }

    public void uncheckKeepLoggedIn() {
        if(keepLoggedInCheckbox.isSelected()) {
            keepLoggedInCheckbox.click();
        }
    }

    public boolean clickLoginButton(){
        boolean isLoginSuccessful;
        try{
            loginButton.click();
            isLoginSuccessful = true;
        }catch(Exception e){
            isLoginSuccessful = false;
        }
        return isLoginSuccessful;
    }

    /*public void waitBeforeClickingLogin() {
        WebDriverWait wait = new WebDriverWait(driver, TIMERS.TIME_TO_WAIT);
        //wait.until(ExpectedConditions.elementToBeClickable(passwordField));
    }*/
}
