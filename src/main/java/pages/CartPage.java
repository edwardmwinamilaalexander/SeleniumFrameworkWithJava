package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // locators

    @FindBy(css = "div[class='page-title'] h1")
    private WebElement cartPageTitle;

    @FindBy(css = ".cart-item-row .product-name")
    private List<WebElement> productNamesInCart;

    // Locators at the top of the class
    @FindBy(css = ".cart-item-row")
    private List<WebElement> productRowInCart;

    @FindBy(className = "product-name")
    private List<WebElement> allProductNamesInCart;

    @FindBy(className = "unit-price")
    private List<WebElement> allUnitPricesInCart;

    @FindBy(className = "qty-input")
    private List<WebElement> allQtyInputs;




    // actions
    public boolean isCartPageLoaded() {
        return isElementDisplayed(cartPageTitle);
    }

    public boolean isProductInCart(String productName) {
        // Loop through
        for (WebElement element : productNamesInCart) {
            if (element.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
    public boolean isProductDetailsCorrect(String productName, String expectedPrice, String expectedQty) {
        // loop through the list
        for (int i = 0; i < allProductNamesInCart.size(); i++) {

            // 1. Get the name at the current index
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
}
