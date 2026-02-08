package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CartPage extends BasePage {

    // Constructor
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Page Title
    @FindBy(css = "div.page-title h1")
    private WebElement cartPageTitle;

    // Product details locators
    @FindBy(css = ".cart-item-row .product-name")
    private List<WebElement> productNamesInCart;

    @FindBy(css = ".cart-item-row")
    private List<WebElement> productRowInCart;

    @FindBy(className = "product-name")
    private List<WebElement> allProductNamesInCart;

    @FindBy(className = "unit-price")
    private List<WebElement> allUnitPricesInCart;

    @FindBy(className = "qty-input")
    private List<WebElement> allQtyInputs;

    // Checkout and terms locators
    @FindBy(css = "#checkout")
    private WebElement checkOutBtn;

    @FindBy(css = "#termsofservice")
    private WebElement termsOfServiceCheckBox;

    // Country selector
    @FindBy(css = "#CountryId")
    private WebElement countryDropDown;


    // Verify if cart page is loaded
    public boolean isCartPageLoaded() {
        return isElementDisplayed(cartPageTitle);
    }

    // Check if product is in cart by name
    public boolean isProductInCart(String productName) {
        for (WebElement element : productNamesInCart) {
            if (element.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Validate product details: price and quantity
    public boolean isProductDetailsCorrect(String productName, String expectedPrice, String expectedQty) {
        for (int i = 0; i < allProductNamesInCart.size(); i++) {
            String actualName = allProductNamesInCart.get(i).getText();

            if (actualName.equalsIgnoreCase(productName)) {
                String actualPrice = allUnitPricesInCart.get(i).getText();
                String actualQty = allQtyInputs.get(i).getAttribute("value");

                System.out.println("Match found at index " + i + ": Price=" + actualPrice + ", Qty=" + actualQty);

                return actualPrice.contains(expectedPrice) && actualQty.equals(expectedQty);
            }
        }
        return false;
    }

    // Click the Terms of Service checkbox
    public void clickTermsOfServiceCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsOfServiceCheckBox));
        click(termsOfServiceCheckBox);
    }

    public boolean isTermsOfServiceChecked() {
        wait.until(ExpectedConditions.visibilityOf(termsOfServiceCheckBox));
        return termsOfServiceCheckBox.isSelected();
    }

    // Click the Checkout button with wait
    public void clickCheckOutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn));
        click(checkOutBtn);
    }



    // Select a country from dropdown
    public void selectCountry(String countryName) {
        wait.until(ExpectedConditions.visibilityOf(countryDropDown));
        new Select(countryDropDown).selectByVisibleText(countryName);
    }

    // Get the currently selected country
    public String getSelectedCountry() {
        Select select = new Select(countryDropDown);
        return select.getFirstSelectedOption().getText();
    }
}


