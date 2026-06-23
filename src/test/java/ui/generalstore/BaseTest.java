package ui.generalstore;

import driver.DriverFactory;
import driver.MobileDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected DriverFactory driverFactory;
    protected MobileDriver mobileDriver;

    @BeforeClass
    public void setUp() throws Exception {
        driverFactory = new DriverFactory();
        mobileDriver = new MobileDriver(driverFactory.createDriver());
    }

    @AfterClass
    public void tearDown() {
        driverFactory.quitDriver();
        driverFactory.stopService();
    }

}