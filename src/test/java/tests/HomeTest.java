package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class HomeTest extends BaseTest {

    private HomePage homePage;

    public HomeTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage(driver);
    }

    @Test(description = "Should load home page successfully")
    public void shouldLoadHomePageSuccessfully() {
        Assert.assertTrue(
                homePage.isHomePageLoaded(),
                "Home page did not load correctly - logo not visible"
        );
    }

    @Test(description = "Should navigate to Login page from home page")
    public void shouldNavigateToLoginPage() {
        LoginPage loginPage = homePage.navigateToLoginPage();
        Assert.assertTrue(
                loginPage.isLoginPageLoaded(),
                "Login page did not load after clicking Login link"
        );
    }

    @Test(description = "Should navigate to Register page from home page")
    public void shouldNavigateToRegisterPage() {
        RegisterPage registerPage = homePage.navigateToRegisterPage();
        Assert.assertTrue(
                registerPage.isRegisterPageLoaded(),
                "Register page did not load after clicking Register link"
        );
    }

    @Test(description = "Should navigate to Book page from home page")
    public void shouldNavigateToBookPage() {
        BookPage bookPage = homePage.navigateToBookPage();
        Assert.assertTrue(
                bookPage.isBookPageLoaded(),
                "Book page did not load after clicking Book link"
        );
    }

    @Test(description = "Should navigate to Cart page from home page")
    public void shouldNavigateToCartPage() {
        CartPage cartPage = homePage.navigateToCartPage();
        Assert.assertTrue(
                cartPage.isCartPageLoaded(),
                "Cart page did not load after clicking Cart link"
        );
    }

    @Test(description = "Should have exactly eight subcategories in category")
    public void shouldHaveEightSubCategories() {
        Assert.assertTrue(
                homePage.hasEightCategories(),
                "Category should contain exactly 8 inactive subcategories"
        );
    }

    @Test(description = "Should display exactly six featured products on home page")
    public void shouldHaveSixFeaturedProducts() {
        Assert.assertTrue(
                homePage.hasSixFeaturedProducts(),
                "HomePage should contain exactly 6 products"
        );
    }

    @Test(description = "Should add a product to cart successfully")
    public void shouldAddProductToCartSuccessfully() {
        // Arrange
        String targetProduct = "14.1-inch Laptop";

        // Act
        homePage.addToCartByProductName(targetProduct);

        // Assert
        Assert.assertTrue(
                homePage.isSuccessNotificationDisplayed(),
                "Success notification bar was not displayed after adding product!"
        );
    }
}
