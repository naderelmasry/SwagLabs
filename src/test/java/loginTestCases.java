import Pages.LoginPage;
import Utilities.Utility;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class loginTestCases {
    LoginPage loginObj;
    public WebDriver driver;
    String loginPath="loginTestData.json";
    @BeforeMethod
    public void test_setUp() throws FileNotFoundException {
        driver=new ChromeDriver();
        loginObj=new LoginPage(driver);
        driver.manage().window().maximize();
        driver.get(Utility.getTestData(loginPath,"loginPageUrl"));
    }

    @Description("Verify the standard user logged successfully")
    @Test
    public void Verify_Login_Standard_User() throws IOException {

        loginObj.enterUserName(Utility.getTestData(loginPath,"standardUsername"));
        loginObj.enterPassword(Utility.getTestData(loginPath,"password"));
        loginObj.clickLoginBtn();
        Utility.takeScreenShot(driver,"validLogin");
        Assert.assertEquals(driver.getCurrentUrl(),Utility.getTestData("loginTestData.json","homeUrl"));
    }

    @Description("Verify the locked user can't be logged")
    @Test
    public void theLockedUserCantBeLogged() throws IOException {
        loginObj.enterUserName(Utility.getTestData(loginPath,"lockedUsername"));
        loginObj.enterPassword(Utility.getTestData(loginPath,"password"));
        loginObj.clickLoginBtn();
        Utility.takeScreenShot(driver,"LoginLockedUser");
        Assert.assertEquals(driver.findElement(loginObj.warningLockedUser).getText(),Utility.getTestData(loginPath,"errorMessage_LockedUser"));
    }

    @Description("Verify displaying error message while logging with invalid credentials")
    @Test
    public void loginWithInvalidCredentials() throws IOException {
        loginObj.enterUserName(Utility.getTestData("loginTestData.json","invalidUsername"));
        loginObj.enterPassword("secret_sauceInvalid");
        loginObj.clickLoginBtn();
        Utility.takeScreenShot(driver,"invalidLogin");
        Assert.assertEquals(driver.findElement(loginObj.warningLockedUser).getText(),Utility.getTestData(loginPath,"errorMessage_invalidUser"));
    }

    @Description("Verify the logged user is logged out after clicking on logout button")
    @Test
    public void testLogout() throws FileNotFoundException {
        loginObj.enterUserName(Utility.getTestData(loginPath,"standardUsername"));
        loginObj.enterPassword(Utility.getTestData(loginPath,"password"));
        loginObj.clickLoginBtn();
        loginObj.clickOnLeftSideMenu();
        loginObj.clickOnLogoutLink();
        Assert.assertEquals(driver.getCurrentUrl(),Utility.getTestData(loginPath,"loginPageUrl"));

    }
     @AfterMethod
     public void quitBrowser(){

        driver.quit();
    }

}
