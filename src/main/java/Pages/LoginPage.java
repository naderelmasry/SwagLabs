package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    ///////////////////////////////Variables////////////////////////////////////////
    public WebDriver driver;
    private final By userName=By.id("user-name");
    private final By password =By.id("password");
    private final By loginBtn=By.id("login-button");
    public final By warningLockedUser=By.cssSelector("div[class='error-message-container error']");
    private final By leftSideMenu=By.id("react-burger-menu-btn");
    private final By logoutLink=By.id("logout_sidebar_link");
    /////////////////////////////////////////////Methods//////////////////////////////////////////

    public LoginPage(WebDriver driver) {
        this.driver=driver;
    }            /////////Constructor////////////////


    public void enterUserName(String userNameValue){
        Utility.typeText(driver,userName,userNameValue);
    };

    public  void enterPassword(String passwordValue){
        Utility.typeText(driver,password,passwordValue);
    }
    public  void clickLoginBtn(){
        Utility.clickOnElement(driver,loginBtn);
    }
    public void clickOnLeftSideMenu(){
        Utility.clickOnElement(driver,leftSideMenu);
    }
    public void clickOnLogoutLink(){
        Utility.clickOnElement(driver,logoutLink);
    }
}
