package ui.generalstore.pages;

import driver.MobileDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductsPage {
    private final MobileDriver mobileDriver;

    public ProductsPage(MobileDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
    }


    // Locators
    private final By productsLabel_L = AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
    private final By cartCounter_L = AppiumBy.id("com.androidsample.generalstore:id/counterText");
    private final By addToCartBtn_L = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");


    // Actions
    public void navigate(){
        mobileDriver.navigateToActivity(".AllProductsActivity");
    }
    public void addToCart(String productName) {
        mobileDriver.gestureActions().scrollToTextContains(productName);
        String uiSelector =
                "new UiSelector().text(\"" + productName + "\")" +
                        ".fromParent(new UiSelector().resourceId(\"com.androidsample.generalstore:id/productAddCart\"))";
        By addToCartButton_L = AppiumBy.androidUIAutomator(uiSelector);
        mobileDriver.elementActions().click(addToCartButton_L);
    }


    // Assertions
    public void verifyProductPageLabel(String expectedLabel) {
        Assert.assertEquals(mobileDriver.elementActions().getTextFluent(productsLabel_L), expectedLabel, "Wrong header Msg!!");
    }

    public void verifyCartQuantity(int expectedNumber) {
        String actualCartQuantity = mobileDriver.elementActions().getTextFluent(cartCounter_L);
        Assert.assertEquals(Integer.parseInt(actualCartQuantity), expectedNumber, "Wrong cart quantity!!");
    }
}
