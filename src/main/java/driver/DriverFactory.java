package driver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import utils.dataReader.PropertyReader;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private AppiumDriverLocalService service;
    private AndroidDriver driver;

    public AndroidDriver createDriver() throws Exception {
        startService();
        UiAutomator2Options options = buildOptions();

        // Use the URL the service actually bound to, instead of a hardcoded port.
        URL serviceUrl = service.getUrl();
        driver = new AndroidDriver(serviceUrl, options);
        return driver;
    }

    private void startService() {
        String ip = PropertyReader.get("ip.address");
        String appiumJs = PropertyReader.get("appium.main.js");

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumJs))
                .withIPAddress(ip)
                .usingAnyFreePort() // avoids "port already in use" from orphaned node processes
                .build();
        service.start();
    }

    private UiAutomator2Options buildOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(PropertyReader.get("device.name"));
        options.setApp(PropertyReader.get("app.path"));
        options.setPlatformName(PropertyReader.get("platform.name"));
        options.setAutomationName(PropertyReader.get("automation.name"));
        return options;
    }

    public void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    public void stopService() {
        if (service != null) {
            try {
                service.stop();
            } catch (Exception ignored) {
            }
        }
    }
}