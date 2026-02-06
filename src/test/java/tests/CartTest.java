package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    CartPage cartPage;
    HomePage homePage;

    public CartTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testIsProductInCart() {

        // 1. Arrange:
        String productName = "14.1-inch Laptop";

        // Stay on Home Page to add the item
        homePage.addToCartByProductName(productName);

        // Now move to the Cart Page t
        homePage.navigateToCartPage();

        // Act
        boolean isProductInCart = cartPage.isProductInCart(productName);

        // Assert
        assertTrue(isProductInCart, "Product should be in the cart.");
    }

    @Test
    public void testIsProductDetailsCorrect() {
        // 1. Arrange:
        String productName = "14.1-inch Laptop";
        String expectedPrice = "1590";
        String expectedQty = "1";

        // Act:
        // Stay on Home Page to add the item
        homePage.addToCartByProductName(productName);

        // Now move to the Cart Page t
        homePage.navigateToCartPage();

        // 3. Verify
        boolean areDetailsCorrect = cartPage.isProductDetailsCorrect(productName, expectedPrice, expectedQty);

        // 4. Assert
        assertTrue(areDetailsCorrect, "Product details should be correct (price and quantity).");
    }

}