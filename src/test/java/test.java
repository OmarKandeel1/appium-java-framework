import org.testng.annotations.Test;
import ui.generalstore.BaseTest;

public class test extends BaseTest {

//    @Test
//    public void wifiSettingsTest() {
//        var pref = driver.findElement(AppiumBy.accessibilityId("Preference"));
//        pref.click();
//        var prefDepend = driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies"));
//        prefDepend.click();
//        var wifiToggle = driver.findElement(AppiumBy.id("android:id/checkbox"));
//        wifiToggle.click();
//        var wifiSettings = driver.findElement(AppiumBy.xpath("(//android.widget.RelativeLayout)[2]"));
//        wifiSettings.click();
//        var wifiInput = driver.findElement(AppiumBy.id("android:id/edit"));
//        wifiInput.sendKeys("TestWifi");
//        var okButton = driver.findElement(AppiumBy.id("android:id/button1"));
//        okButton.click();

//    }


//    @Test
//    public void longPressGestureTest() {
//        //Locators
//        By views_L = AppiumBy.accessibilityId("Views");
//        By expandList_L = AppiumBy.accessibilityId("Expandable Lists");
//        By customAdapter_L = AppiumBy.accessibilityId("1. Custom Adapter");
//        By peopleNames_L = AppiumBy.xpath("//android.widget.TextView[@text='People Names']");
//        By sampleMenu_L  = AppiumBy.xpath("//android.widget.TextView[@text=\"Sample menu\"]");
//
//        //Actions
//        driver.findElement(views_L).click();
//        driver.findElement(expandList_L).click();
//        driver.findElement(customAdapter_L).click();
//        gestureUtils.longPress(peopleNames_L);
//
//        String sampleMenuText = driver.findElement(sampleMenu_L).getText();
//        assert sampleMenuText.equals("Sample menu") : "Expected 'Sample menu' but got '" + sampleMenuText + "'";
//
//    }


//    @Test
//    public void scrollToWebViewTest() {
//        mobileDriver.elementActions().click(AppiumBy.accessibilityId("Views"));
//        WebElement webView = mobileDriver.gestureActions().scrollToText("WebView");
//        webView.click();
//    }
//
//    @Test
//    public void swipeTest() {
//        By views_L = AppiumBy.accessibilityId("Views");
//        By gallery_L = AppiumBy.accessibilityId("Gallery");
//        By photos_L = AppiumBy.accessibilityId("1. Photos");
//        By firstPhoto_L = AppiumBy.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]\n");
//        By secondPhoto_L = AppiumBy.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[2]\n");
//
//
//        mobileDriver.elementActions().click(views_L);
//        mobileDriver.elementActions().click(gallery_L);
//        mobileDriver.elementActions().click(photos_L);
//        Assert.assertTrue(mobileDriver.elementActions().find(firstPhoto_L).getAttribute("focusable").equals("true"));
//        mobileDriver.gestureActions().swipe(firstPhoto_L, "left");
//        Assert.assertTrue(mobileDriver.elementActions().find(firstPhoto_L).getAttribute("focusable").equals("false"));
//        Assert.assertTrue(mobileDriver.elementActions().find(secondPhoto_L).getAttribute("focusable").equals("true"));
//
//    }

//    @Test
//    public void dragNDropTest(){
//        By views_L = AppiumBy.accessibilityId("Views");
//        By dragNDrop_L = AppiumBy.accessibilityId("Drag and Drop");
//        By firstCircle_L = AppiumBy.id("io.appium.android.apis:id/drag_dot_1");
//        By secondCircle_L = AppiumBy.id("io.appium.android.apis:id/drag_dot_2");
//        By dropText_L = AppiumBy.id("io.appium.android.apis:id/drag_result_text");
//        mobileDriver.elementActions().click(views_L);
//        mobileDriver.elementActions().click(dragNDrop_L);
//        mobileDriver.gestureActions().dragAndDrop(firstCircle_L, secondCircle_L);
//        Assert.assertTrue(mobileDriver.elementActions().find(dropText_L).isDisplayed());
//
//
//    }

@Test
    public void generalStoreTest(){

}

}