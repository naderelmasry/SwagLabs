import Pages.CheckoutProductsPage;
import Pages.LandingPage;
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

public class CheckoutProductsTestcases {
    public WebDriver driver;
    String addProductsTestDataPath="addProductsTestData.json";
    CheckoutProductsPage checkoutProductObj;
    LandingPage landingpageObj;
    LoginPage loginObj;

    @BeforeMethod
    public void loginToSystem() throws FileNotFoundException {
        driver = new ChromeDriver();
        loginObj = new LoginPage(driver);
        checkoutProductObj = new CheckoutProductsPage(driver);
        landingpageObj=new LandingPage(driver);

        driver.manage().window().maximize();
        driver.get(Utility.getTestData(addProductsTestDataPath,"loginPageUrl"));
        loginObj.enterUserName(Utility.getTestData(addProductsTestDataPath,"standardUsername"));
        loginObj.enterPassword(Utility.getTestData(addProductsTestDataPath,"password"));
        loginObj.clickLoginBtn();

    }
    @Description("verify adding new product and checkOut")
    @Test
    public void addingNewProduct() throws IOException {
        landingpageObj.clickOnAddProductBtn();
        landingpageObj.clickOnCardIcon();
        checkoutProductObj.clickOnCheckoutBtn();
        checkoutProductObj.enterFirstName(Utility.getTestData(addProductsTestDataPath,"firstname"));
        checkoutProductObj.enterLastName(Utility.getTestData(addProductsTestDataPath,"lastname"));
        checkoutProductObj.enterPostalCOde(Utility.getTestData(addProductsTestDataPath,"postalCode"));;
        checkoutProductObj.clickOnContinueBtn();
        checkoutProductObj.clickOnFinishBtn();
        Utility.takeScreenShot(driver,"AddingNewProducts.png");
        Assert.assertEquals(driver.findElement(checkoutProductObj.finishOrderLabel).getText(),"Thank you for your order!");
    }

    @Description("Verify Can't complete the checkout with empty first name")
    @Test
    public void checkoutOrderWithoutFirstName() throws IOException {
        landingpageObj.clickOnAddProductBtn();
        landingpageObj.clickOnCardIcon();
        checkoutProductObj.clickOnCheckoutBtn();
        checkoutProductObj.enterLastName(Utility.getTestData(addProductsTestDataPath,"lastname"));
        checkoutProductObj.enterPostalCOde(Utility.getTestData(addProductsTestDataPath,"postalCode"));
        checkoutProductObj.clickOnContinueBtn();
        Utility.takeScreenShot(driver,"emptyFirstName.png");
        Assert.assertEquals(driver.findElement(checkoutProductObj.errorText).getText(),Utility.getTestData(addProductsTestDataPath,"errorMessage_missingFirstName"));
    }

    @Description("Verify can't complete the checkout with empty last name")
    @Test
    public void checkoutOrderWithoutLastName() throws IOException {
        landingpageObj.clickOnAddProductBtn();
        landingpageObj.clickOnCardIcon();
        checkoutProductObj.clickOnCheckoutBtn();
        checkoutProductObj.enterFirstName(Utility.getTestData(addProductsTestDataPath,"firstname"));
        checkoutProductObj.enterPostalCOde(Utility.getTestData(addProductsTestDataPath,"postalCode"));
        checkoutProductObj.clickOnContinueBtn();
        Utility.takeScreenShot(driver,"emptyLastName.png");
        Assert.assertEquals(driver.findElement(checkoutProductObj.errorText).getText(),Utility.getTestData(addProductsTestDataPath,"errorMessage_missingLastName"));
    }

    @Description("Verify can't complete the checkout order with empty postalCode")
    @Test
    public void checkOutOrderWithoutPostalCode() throws IOException {
        landingpageObj.clickOnAddProductBtn();
        landingpageObj.clickOnCardIcon();
        checkoutProductObj.clickOnCheckoutBtn();
        checkoutProductObj.enterFirstName(Utility.getTestData(addProductsTestDataPath,"firstname"));
        checkoutProductObj.enterLastName(Utility.getTestData(addProductsTestDataPath,"lastname"));
        checkoutProductObj.clickOnContinueBtn();
        Utility.takeScreenShot(driver,"emptyPostalCodeName.png");
        Assert.assertEquals(driver.findElement(checkoutProductObj.errorText).getText(),Utility.getTestData(addProductsTestDataPath,"errorMessage_missingPostalCode"));;
    }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}
