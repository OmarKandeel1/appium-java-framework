import driver.DriverFactory;
import driver.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.actions.ElementActions;
import utils.actions.GestureActions;

public class BaseTest {

    protected DriverFactory driverFactory;
    protected MobileDriver mobileDriver;

    @BeforeMethod
    public void setUp() throws Exception {
        driverFactory = new DriverFactory();
        mobileDriver = new MobileDriver(driverFactory.createDriver());
    }

    @AfterMethod
    public void tearDown() {
        driverFactory.quitDriver();
        driverFactory.stopService();
    }

}