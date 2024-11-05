package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Utility {
    WebDriver driver;
    public Utility(WebDriver driver) {
        this.driver=driver;
    }
    private static final String TESTDATA_PATH = "src/test/testData/";
    private static String SCREENSHOTS_Path = "src/ScreenShots";

    /// ///TODO: Clicking on the element
    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }

    //TODO: Sending data to the element
    public static void typeText(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    //TODO:Getting text
    public static String getTxt(WebDriver driver,By locator){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }
    //TODO: Taking screenShot
    public static void takeScreenShot(WebDriver driver, String screenShotName) throws IOException {
        File screenSrc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenDest = new File(SCREENSHOTS_Path+screenShotName+".png");
        FileUtils.copyFile(screenSrc,screenDest);
        Allure.addAttachment(screenShotName, Files.newInputStream(Path.of(screenDest.getPath())));
    }

    //TODO: To readig Datat from Json File
    public static String getTestData(String fileName, String field) throws FileNotFoundException {
        FileReader reader = new FileReader(TESTDATA_PATH+fileName);
        JsonElement jsonElement= JsonParser.parseReader(reader);
        return jsonElement.getAsJsonObject().get(field).getAsString();
    }

}
