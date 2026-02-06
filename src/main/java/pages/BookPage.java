package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {
    // PageFactory locators

    @FindBy(css = ".item-box")
    WebElement BookList;
    @FindBy(css = ".product-item")
    List<WebElement> bookItem;

    @FindBy(css = "div.page-title h1")
    private WebElement bookTitlePage;


    public BookPage(WebDriver driver) {
        super(driver);
    }

    // Actions

    public boolean isBookPageLoaded() {
        return isElementDisplayed(bookTitlePage);
    }

    public boolean isBookListVisible() {
        return isElementDisplayed(BookList);
    }

}







