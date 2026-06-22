package utils.actions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private final AndroidDriver driver;

    public ElementActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        return driver.findElement(locator);
    }

    public void click(By locator) {
        find(locator).click();
    }

    public void sendKeys(By locator, String text) {
        find(locator).sendKeys(text);
    }

    public String getText(By locator) {
        return find(locator).getText();
    }


    public void assertText(By locator, String expectedText) {
        String actualText = getText(locator);
        assert actualText.equals(expectedText)
                : "Expected '" + expectedText + "' but got '" + actualText + "'";
    }
}
