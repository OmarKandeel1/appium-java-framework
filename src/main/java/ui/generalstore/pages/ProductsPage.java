package ui.generalstore.pages;

import driver.MobileDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductsPage {
    MobileDriver mobileDriver;

    public ProductsPage(MobileDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
    }


    // Locators
    private final By productsLabel_L = AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");



    // Actions



    // Assertions
    public void verifyProductPageLabel(String expectedLabel){
        Assert.assertEquals(mobileDriver.elementActions().getTextFluent(productsLabel_L), expectedLabel , "Wrong header Msg!!");
    }
}
