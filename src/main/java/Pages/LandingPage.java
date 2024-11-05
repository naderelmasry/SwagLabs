package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {
    public WebDriver driver;
    /////////////////////////////////////////Variables///////////////////////////////
    private final By addToCardBtnForAllProducts=By.xpath("// button[@class]");
    private final By addProductToCart=By.id("add-to-cart-sauce-labs-backpack");
    public final By numberOfProducts=By.cssSelector("span[class=shopping_cart_badge");
    private final By cardBtn=By.cssSelector("a[class='shopping_cart_link']");
    private final By priceOfSelectedProduct=By.xpath("//button[.=\"Remove\"]//preceding-sibling::div[@class]");
    public String totalPrice;
    public LandingPage(WebDriver driver) {
        this.driver=driver;
    }
    private static List<WebElement> allProducts;

    public void clickOnAddProductBtn(){
        Utility.clickOnElement(driver,addProductToCart);
    }

    public LandingPage addAllProductsToCart(){
        allProducts=driver.findElements(addToCardBtnForAllProducts);   //return all products
        for (int i=1;i<= allProducts.size();i++){
            By addToCardBtnForAllProducts=By.xpath("(//button[@class])[" + i + "]");
            Utility.clickOnElement(driver,addToCardBtnForAllProducts);
        }
        return this;
    }

    public LandingPage removeAllProductsToCart(){
        allProducts=driver.findElements(addToCardBtnForAllProducts);   //return all products
        for (int i=1;i<= allProducts.size();i++){
            By addToCardBtnForAllProducts=By.xpath("(//button[@class])[" + i + "]");
            Utility.clickOnElement(driver,addToCardBtnForAllProducts);
        }
        return this;
    }

    public String getNumberOfSelectedProduct(){
        return Utility.getTxt(driver,numberOfProducts);
    }

    public String getPriceOfSelectedProducts(){
        List<WebElement>priceOfSelectedProductsList=driver.findElements(priceOfSelectedProduct);
        for (int i=1;i<=priceOfSelectedProductsList.size();i++){
           By priceOfSelectedProduct=By.xpath("(//button[.=\"Remove\"]//preceding-sibling::div[@class])[" + i +"]");
           String fullText=Utility.getTxt(driver,priceOfSelectedProduct);
           totalPrice +=Float.parseFloat(fullText.replace("$",""));
        }

        return String.valueOf(totalPrice);
    }
    public void clickOnCardIcon(){
        Utility.clickOnElement(driver,cardBtn);
    }

    public boolean addToCardBtnIsActive(){
        driver.findElement(addProductToCart).isDisplayed();
        return true;
    }
}
