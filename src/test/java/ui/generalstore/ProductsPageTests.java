package ui.generalstore;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.generalstore.pages.ProductsPage;

public class ProductsPageTests extends LoggedInBaseTest {



    @Test
    public void addProductToCart(){
        ProductsPage productsPage = new ProductsPage(mobileDriver);
        productsPage.addToCart("Air Jordan 1 Mid SE");
        productsPage.verifyCartQuantity(1);
        productsPage.addToCart("PG 3");
        productsPage.verifyCartQuantity(2);

    }

}
