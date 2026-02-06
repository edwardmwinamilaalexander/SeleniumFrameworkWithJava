package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookPage;
import pages.HomePage;
import pages.LoginPage;

public class BookTest extends BaseTest {
    HomePage homePage;
    BookPage bookPage;
    LoginPage loginPage;

    public BookTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        bookPage = new BookPage(driver);
    }

    @Test
    public void testIsBookUrlDisplayed() {
        homePage.navigateToBookPage();
        Assert.assertTrue(bookPage.isBookPageLoaded(), "Book page is not loaded.");
    }

    @Test
    public void testProductListVisibility() {
        homePage.navigateToBookPage();
        Assert.assertTrue(bookPage.isBookListVisible(), "Book list is not visible on the books page.");
    }





}


