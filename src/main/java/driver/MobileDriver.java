package driver;

import io.appium.java_client.android.AndroidDriver;
import utils.actions.ElementActions;
import utils.actions.GestureActions;

/**
 * Facade over the raw Appium AndroidDriver. Holds one ElementUtils and one
 * GestureUtils instance, and exposes them through named entry points so
 * calls read as: mobileDriver.elementActions().click(locator);
 */
public class MobileDriver {

    private final AndroidDriver rawDriver;
    private final ElementActions elementActions;
    private final GestureActions gestureActions;

    public MobileDriver(AndroidDriver rawDriver) {
        this.rawDriver = rawDriver;
        this.elementActions = new ElementActions(rawDriver);
        this.gestureActions = new GestureActions(rawDriver);
    }

    public ElementActions elementActions() {
        return elementActions;
    }

    public GestureActions gestureActions() {
        return gestureActions;
    }


    public AndroidDriver getRawDriver() {
        return rawDriver;
    }
}