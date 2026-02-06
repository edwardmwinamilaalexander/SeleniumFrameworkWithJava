package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

    RegisterPage registerPage;
    HomePage homePage;

    public RegisterTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage(driver);
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testUserCanRegisterSuccessfully() {

        // Navigate to Register Page
        homePage.navigateToRegisterPage();

        // Fill registration form
        String uniqueEmail = "testuser" + System.currentTimeMillis() + "@example.com";

        registerPage.fillRegisterForm(
                "John",
                "Doe",
                uniqueEmail,
                "Pass123!",
                "Pass123!"
        );

        // Assertions
        Assert.assertTrue(
                registerPage.isRegistrationSuccessMessageDisplayed(),
                "Registration success message is not be displayed"
        );

        Assert.assertEquals(
                registerPage.getRegistrationSuccessMessageText(),
                "Your registration completed",
                "Registration success message text is incorrect"
        );
    }
}
