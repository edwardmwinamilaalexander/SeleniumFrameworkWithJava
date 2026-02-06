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


    @Test(description = "Verify home page loads successfully")
    public void testHomePageLoadsSuccessfully() {

        Assert.assertTrue(
                homePage.isLogoDisplayed(),
                "Home page did not load correctly - logo not visible"
        );
    }

    @Test(description = "Verify user can navigate to Login page from home page")
    public void testNavigateToLoginPage() {

        LoginPage loginPage = homePage.navigateToLoginPage();

        Assert.assertTrue(
                loginPage.isLoginPageLoaded(),
                "Login page did not load after clicking Login link"
        );
    }

    @Test(description = "Verify user can navigate to Register page from home page")
    public void testNavigateToRegisterPage() {

        RegisterPage registerPage = homePage.navigateToRegisterPage();

        Assert.assertTrue(
                registerPage.isRegisterPageLoaded(),
                "Register page did not load after clicking Register link"
        );
    }

    @Test(description = "Verify user can navigate to Book page from home page")
    public void testNavigateToBookPage() {

        BookPage bookPage = homePage.navigateToBookPage();

        Assert.assertTrue(
                bookPage.isBookPageLoaded(),
                "Book page did not load after clicking Register link"
        );
    }

    @Test(description = "Verify user can navigate to Cart page from home page")
    public void testNavigateToCartPage() {

        CartPage cartPage = homePage.navigateToCartPage();

        Assert.assertTrue(
                cartPage.isCartPageLoaded(),
                "Book page did not load after clicking Register link"
        );
    }

    @Test
    public void testCategoryHasEightSubCategories() {


        // Assert the category has exactly 8 inactive items
        Assert.assertTrue(
                homePage.hasEightCategories(),
                "Category should contain exactly 8 inactive subcategories"
        );
    }

    @Test
    public void testHomePageHasSixFeaturedProducts() {


        // Assert the category has exactly 8 inactive items
        Assert.assertTrue(
                homePage.hasSixFeaturedProducts(),
                "HomePage should contain exactly 6 products"
        );
    }

    @Test
    public void testAddProductToCart() {
        // Arrange
        String targetProduct = "14.1-inch Laptop";

        // Act
        homePage.addToCartByProductName(targetProduct);

        // Assert: Verify the success bar is visible
        Assert.assertTrue(homePage.isSuccessNotificationDisplayed(),
                "Success notification bar was not displayed after adding product!");
    }
}

