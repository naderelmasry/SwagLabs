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

public class AddProductTestCases {
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

    @Description("Verify that all products are selected correctly")
    @Test
    public void selectAllProducts() throws IOException {
        landingpageObj.addAllProductsToCart();
        Utility.takeScreenShot(driver,"AddAllProductsToCard.png");
        Assert.assertEquals(landingpageObj.getNumberOfSelectedProduct(),"6");
    }

    @Description("Verify Removing the selected  products")
    @Test
    public void removeSelectedProducts() throws IOException {
        landingpageObj.addAllProductsToCart();
        landingpageObj.removeAllProductsToCart();
        Utility.takeScreenShot(driver,"AddAllProductsToCard.png");
        Assert.assertTrue(landingpageObj.addToCardBtnIsActive(),"The cart icon is displayed");
    }

    @Description("Verify the selected products calculates the prices correctly")
    @Test
    public void checkCalculationPricesOfSelectedProducts() throws IOException {
        landingpageObj.addAllProductsToCart();
        landingpageObj.getPriceOfSelectedProducts();
        landingpageObj.clickOnCardIcon();
        System.out.println(landingpageObj.totalPrice);
        Utility.takeScreenShot(driver,"comparingPrice.png");
        Assert.assertTrue(checkoutProductObj.comparingPrices(landingpageObj.totalPrice));
     }

    @AfterMethod
    public void quit(){
        driver.quit();
    }
}










