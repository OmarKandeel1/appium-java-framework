package ui.generalstore;

import org.testng.annotations.Test;
import ui.generalstore.pages.HomePage;

public class HomePageTests extends BaseTest{



    @Test
    public void validLoginTest(){
        HomePage homePage = new HomePage(mobileDriver);
        homePage.login("Argentina", "Omar","male").verifyProductPageLabel("Products");
    }

    @Test
    public void invalidLoginTest(){
        HomePage homePage = new HomePage(mobileDriver);
        homePage.login("Argentina", "","male");
        homePage.verifyErrorMsg("Please enter your name");
    }



}
