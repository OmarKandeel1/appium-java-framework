package ui.generalstore.pages;

import driver.MobileDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage {

    private final MobileDriver mobileDriver;

    public HomePage(MobileDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
    }


    // Locators
    private final By countryDropdown_L = AppiumBy.id("android:id/text1");
    private final By nameInputField_L  = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private final By maleGender_L = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
    private final By femaleGender_L = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private final By errorMsg_L = AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]");
    private final By letsShop_L = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");


    // Actions
    public void chooseCountry(String countryName) {
        mobileDriver.elementActions().find(countryDropdown_L).click();
        By countryChoice = AppiumBy.xpath("//android.widget.TextView[@text=\"" + countryName + "\"]");
        mobileDriver.elementActions().find(countryChoice).click();
    }

    public void chooseGender(String gender){
        String input = gender.toLowerCase();
        if(input.equals("male"))
        {
            mobileDriver.elementActions().find(maleGender_L).click();
        }
        else if(input.equals("female"))
        {
            mobileDriver.elementActions().find(femaleGender_L).click();
        }else {
            throw new IllegalArgumentException("Unknown gender: " + gender);
        }
    }

    public void enterName(String name){
        mobileDriver.elementActions().find(nameInputField_L).sendKeys(name);
    }

    public ProductsPage login(String country, String name, String gender){
        chooseCountry(country);
        enterName(name);
        chooseGender(gender);
        mobileDriver.elementActions().find(letsShop_L).click();
        return new ProductsPage(mobileDriver);
    }


    // Assertions
    public void verifyErrorMsg(String expectedMsg){
        Assert.assertEquals(mobileDriver.elementActions().find(errorMsg_L).getText(), expectedMsg,"Error not the expected Msg!!!");
    }


}
