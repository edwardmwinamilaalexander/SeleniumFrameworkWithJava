package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    private CartPage cartPage;
    private HomePage homePage;

    public CartTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(description = "Should add a product to the cart successfully")
    public void shouldAddProductToCartSuccessfully() {

        // Arrange
        String productName = "14.1-inch Laptop";

        // Act
        homePage.addToCartByProductName(productName);
        homePage.navigateToCartPage();
        boolean isProductInCart = cartPage.isProductInCart(productName);

        // Assert
        assertTrue(isProductInCart, "Product should be in the cart.");
    }

    @Test(description = "Should display correct product details in the cart")
    public void shouldDisplayCorrectProductDetailsInCart() {

        // Arrange
        String productName = "14.1-inch Laptop";
        String expectedPrice = "1590";
        String expectedQty = "1";

        // Act
        homePage.addToCartByProductName(productName);
        homePage.navigateToCartPage();
        boolean areDetailsCorrect = cartPage.isProductDetailsCorrect(productName, expectedPrice, expectedQty);

        // Assert
        assertTrue(areDetailsCorrect, "Product details should be correct (price and quantity).");
    }

    @Test(description = "Should allow selecting a country in the cart")
    public void shouldSelectCountryInCart() {

        // Arrange
        String productName = "14.1-inch Laptop";
        String country = "Congo";

        // Act
        homePage.addToCartByProductName(productName);
        homePage.navigateToCartPage();
        cartPage.selectCountry(country);

        // Assert
        Assert.assertEquals(cartPage.getSelectedCountry(), country, "Country selection failed!");
    }

    @Test(description = "Should have Terms of Service checkbox unchecked by default")
    public void shouldHaveTermsOfServiceUncheckedByDefault() {

        // Arrange
        String productName = "14.1-inch Laptop";

        // Act
        homePage.addToCartByProductName(productName);
        homePage.navigateToCartPage();
        boolean isChecked = cartPage.isTermsOfServiceChecked();

        // Assert
        assertFalse(isChecked, "Terms of Service checkbox should be unchecked initially");
    }
}

