package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutProductsPage {
    public WebDriver driver;
    /////////////////////////////////////////Variables///////////////////////////////
    private final By checkOutBtn=By.id("checkout");
    private final By firstNameFieldTxt= By.id("first-name");
    private final By lastNameFieldTxt=By.id("last-name");
    private final By postalCodeFieldTxt=By.id("postal-code");
    private final By continueBtn=By.id("continue");
    private final By finishCheckOutBtn=By.id("finish");
    public By finishOrderLabel=By.cssSelector("h2[class='complete-header']");
    public By errorText=By.cssSelector("div[class='error-message-container error']");
    private final By priceOfSelectedProduct=By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class]");
    public String totalPrice;
    ///////////////////////////////////Methods////////////////////////////////
    public CheckoutProductsPage(WebDriver driver) {
        this.driver=driver;
    }  //Constructor
    public void clickOnCheckoutBtn(){
        Utility.clickOnElement(driver,checkOutBtn);
    }
    public void enterFirstName(String firstNameTxt){
        Utility.typeText(driver,firstNameFieldTxt,firstNameTxt);
    }
    public void enterLastName(String lastNameTxt){
        Utility.typeText(driver,lastNameFieldTxt,lastNameTxt);
    }
    public void enterPostalCOde(String postalCode){
        Utility.typeText(driver,postalCodeFieldTxt,postalCode);
    }

    public void clickOnContinueBtn(){
        Utility.clickOnElement(driver,continueBtn);
    }
    public void clickOnFinishBtn(){
        Utility.clickOnElement(driver,finishCheckOutBtn);
    }

    public String getTotalPrices(){
        List<WebElement>priceOfSelectedProductsList=driver.findElements(priceOfSelectedProduct);
        for (int i=1;i<=priceOfSelectedProductsList.size();i++){
            By priceOfSelectedProduct=By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class])[" + i +"]");
            String fullText=Utility.getTxt(driver,priceOfSelectedProduct);
            totalPrice +=Float.parseFloat(fullText.replace("$",""));
        }

        return String.valueOf(totalPrice);
    }

    public boolean comparingPrices(String price){
        return getTotalPrices().equals(price);
    }
}

