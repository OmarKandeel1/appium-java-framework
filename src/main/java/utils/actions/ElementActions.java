package utils.actions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.dataReader.PropertyReader;

import java.time.Duration;

public class ElementActions {

    private final AndroidDriver driver;
    private final WebDriverWait wait;
    private final FluentWait<AndroidDriver> fluentWait;

    public ElementActions(AndroidDriver driver) {
        this.driver = driver;
        int timeoutSeconds = PropertyReader.getInt("explicit.wait.seconds");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        this.fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Waits until the element located by `locator` is present, then returns it.
     * Every other method in this class goes through find(), so they all wait too.
     */
    public WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
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

    public String getTextFluent(By locator) {
        return fluentWait.until(d -> d.findElement(locator).getText());
    }



}