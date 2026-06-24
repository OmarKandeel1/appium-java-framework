package ui.generalstore;

import org.testng.annotations.BeforeClass;
import ui.generalstore.pages.HomePage;
import ui.generalstore.pages.ProductsPage;

/**
 * Extends BaseTest and additionally logs in once per class, landing on
 * ProductsPage. Use this for test classes that need to be logged in to test
 * something else (cart, products, etc) — NOT for testing the login flow itself,
 * since by the time @Test methods run here, the login screen is already gone.
 */
public class LoggedInBaseTest extends BaseTest {

    private static final String DEFAULT_COUNTRY = "Argentina";
    private static final String DEFAULT_NAME = "Omar";
    private static final String DEFAULT_GENDER = "Male";

    protected ProductsPage productsPage;

    @BeforeClass(dependsOnMethods = "setUp")
    public void login() {
        productsPage = new HomePage(mobileDriver).login(DEFAULT_COUNTRY, DEFAULT_NAME, DEFAULT_GENDER);
    }
}