package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookPage;
import pages.HomePage;
import pages.LoginPage;

public class BookTest extends BaseTest {

    private HomePage homePage;
    private BookPage bookPage;
    private LoginPage loginPage;

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

    @Test(description = "Should display the correct URL when navigating to the Book page")
    public void shouldDisplayBookPageUrl() {
        homePage.navigateToBookPage();
        Assert.assertTrue(
                bookPage.isBookPageLoaded(),
                "Book page is not loaded."
        );
    }

    @Test(description = "Should display the book list on the Book page")
    public void shouldDisplayBookListOnBookPage() {
        homePage.navigateToBookPage();
        Assert.assertTrue(
                bookPage.isBookListVisible(),
                "Book list is not visible on the books page."
        );
    }
}
