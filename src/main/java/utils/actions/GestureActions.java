package utils.actions;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.dataReader.PropertyReader;

import java.time.Duration;
import java.util.Collections;

public class GestureActions {

    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public GestureActions(AndroidDriver driver) {
        this.driver = driver;
        int timeoutSeconds = PropertyReader.getInt("explicit.wait.seconds");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    /**
     * Waits until the element located by `locator` is present, then returns it.
     * Every By-based method below goes through this instead of driver.findElement directly,
     * so gestures wait the same way ElementActions does.
     */
    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Long-presses a WebElement using Appium's mobile: longClickGesture.
     */
    public void longPress(WebElement element) {
        driver.executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", element));
    }

    /**
     * Long-presses an element located by a By locator (waits for it, then long presses).
     */
    public void longPress(By locator) {
        longPress(find(locator));
    }

    /**
     * Scrolls a scrollable container until an element with the given visible text appears,
     * then returns that element.
     */
    public WebElement scrollToText(String text) {
        String uiSelector =
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
        return find(AppiumBy.androidUIAutomator(uiSelector));
    }

    public WebElement scrollToTextContains(String text) {
        String uiSelector =
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))";
        return find(AppiumBy.androidUIAutomator(uiSelector));
    }

    /**
     * Scrolls a scrollable container until an element with the given resource-id appears,
     * then returns that element.
     **/
    public WebElement scrollToResourceId(String resourceId) {
        String uiSelector =
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"))";
        return find(AppiumBy.androidUIAutomator(uiSelector));
    }

    /**
     * Swipes in the given direction within the bounding box of the given element.
     * direction must be one of: "up", "down", "left", "right".
     * percent (0.0–1.0) controls how far the swipe travels within the element's bounds.
     */
    public void swipe(WebElement element, String direction, double percent) {
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", element,
                "direction", direction,
                "percent", percent));
    }

    /** Swipe on an element with a sensible default distance (75% of its bounds). */
    public void swipe(WebElement element, String direction) {
        swipe(element, direction, 0.75);
    }

    /** Waits for the element, then swipes on it. */
    public void swipe(By locator, String direction, double percent) {
        swipe(find(locator), direction, percent);
    }

    public void swipe(By locator, String direction) {
        swipe(find(locator), direction, 0.75);
    }

    /**
     * Swipes within an explicit screen region instead of an element's bounds.
     * Useful for full-screen swipes (e.g. dismissing a screen, navigating between pages)
     * where there's no single element to anchor the gesture to.
     */
    public void swipe(int left, int top, int width, int height, String direction, double percent) {
        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", left,
                "top", top,
                "width", width,
                "height", height,
                "direction", direction,
                "percent", percent));
    }

    /**
     * Drags from the center of one element to the center of another, using W3C Actions.
     * Unlike swipe(), this targets an exact destination instead of a direction/percent guess —
     * useful when you want to land precisely on a specific second element (e.g. photo 1 -> photo 2).
     */
    public void swipeBetween(WebElement from, WebElement to) {
        Point start = centerOf(from);
        Point end = centerOf(to);
        swipeBetween(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public void swipeBetween(By fromLocator, By toLocator) {
        swipeBetween(find(fromLocator), find(toLocator));
    }

    /**
     * Drags from one exact screen coordinate to another over ~300ms.
     */
    public void swipeBetween(int fromX, int fromY, int toX, int toY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 0);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), fromX, fromY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), toX, toY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    /**
     * Drags from the center of one element and drops it on the center of another.
     * Unlike swipeBetween(), this holds briefly after pressing down (and before releasing)
     * so the gesture is recognized as a "grab and move" rather than a quick flick —
     * important for drag-and-drop UIs (reordering lists, sortable grids, etc).
     */
    public void dragAndDrop(WebElement from, WebElement to) {
        Point start = centerOf(from);
        Point end = centerOf(to);
        dragAndDrop(start.getX(), start.getY(), end.getX(), end.getY());
    }

    public void dragAndDrop(By fromLocator, By toLocator) {
        dragAndDrop(find(fromLocator), find(toLocator));
    }

    /**
     * Drags from one exact screen coordinate and drops at another, with a hold after
     * pressing down and a hold before releasing (300ms each) to register as a real drag.
     */
    public void dragAndDrop(int fromX, int fromY, int toX, int toY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence drag = new Sequence(finger, 0);

        drag.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), fromX, fromY));
        drag.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        drag.addAction(new Pause(finger, Duration.ofMillis(300))); // dwell so it's read as a grab, not a flick
        drag.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), toX, toY));
        drag.addAction(new Pause(finger, Duration.ofMillis(300))); // dwell before release so the drop registers
        drag.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(drag));
    }




// *************************************************************** HELPER FUNCTIONS ********************************************************************************* //

    /**
     * Computes the center point of an element from its top-left location and size,
     * since WebElement only exposes getLocation() (top-left corner) and getSize(),
     * not a center point directly.
     */
    private Point centerOf(WebElement element) {
        Point topLeft = element.getLocation();
        Dimension size = element.getSize();
        int centerX = topLeft.getX() + (size.getWidth() / 2);
        int centerY = topLeft.getY() + (size.getHeight() / 2);
        return new Point(centerX, centerY);
    }
}