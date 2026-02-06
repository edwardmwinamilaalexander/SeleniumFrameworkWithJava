package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    // Header links
    @FindBy(linkText = "Register")
    private WebElement registerLink;

    @FindBy(linkText = "Log in")
    private WebElement loginLink;

    @FindBy(xpath = "//ul[@class='top-menu']//a[normalize-space()='Books']")
    private WebElement booksMenu;

    // Page elements
    @FindBy(css = "img[alt='Tricentis Demo Web Shop']")
    private WebElement shopLogo;

    @FindBy(css = "ul.list > li.inactive")
    private List<WebElement> categories;

    @FindBy(css = ".item-box")
    private List<WebElement> products;

    @FindBy(css = "h2.product-title")
    private List<WebElement> productTitle;

    @FindBy(css = ".item-box input[value='Add to cart']")
    private List<WebElement> addToCartButtons;

    @FindBy(css = ".cart-qty")
    private WebElement cartCountItem;

    @FindBy(id = "bar-notification")
    private WebElement successNotificationBar;

    @FindBy(css = "#bar-notification .close")
    private WebElement closeNotificationButton;

    @FindBy(css = "a[class='ico-cart'] span[class='cart-label']")
    private WebElement cartShoppingLabel;





    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    // Navigation Actions
    public RegisterPage navigateToRegisterPage() {
        click(registerLink);
        return new RegisterPage(driver);
    }

    public LoginPage navigateToLoginPage() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public BookPage navigateToBookPage() {
        click(booksMenu);
        return new BookPage(driver);
    }

    public CartPage navigateToCartPage() {
        click(cartShoppingLabel);
        return new CartPage(driver);
    }

    // Verification Actions
    public boolean isLogoDisplayed() {
        return isElementDisplayed(shopLogo);
    }

    public boolean hasEightCategories() {
        return categories.size() == 8;
    }

    public boolean hasSixFeaturedProducts() {
        return products.size() == 6;
    }

    public void addToCartByProductName(String productName) {
        for (int i = 0; i < productTitle.size(); i++) {
            if (productTitle.get(i).getText().equalsIgnoreCase(productName)) {
                // 1. Perform the click
                click(addToCartButtons.get(i));

                // 2. Wait for the green notification bar to appear 🟢
                // This confirms the site received the request
                waitForVisibility(successNotificationBar);

                // 3. Optional: Close the bar so it doesn't block other elements
                click(closeNotificationButton);

                break;
            }
        }
    }
        public boolean isSuccessNotificationDisplayed(){
            try {
                // We use our existing waitForVisibility to ensure the bar has
                // had time to appear before we check it
                waitForVisibility(successNotificationBar);
                return successNotificationBar.isDisplayed();
            } catch (Exception e) {
                // If the element isn't found or times out, it's not displayed
                return false;
            }
        }
    }

