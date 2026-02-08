package pages;

import base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Locators
    @FindBy(id = "Email")
    private WebElement emailField;
    @FindBy(id = "Password")
    private WebElement passwordField;
    @FindBy(css = "input.login-button")
    private WebElement loginButton;
    @FindBy(css = "div.validation-summary-errors")
    private WebElement loginErrorMessage;
    @FindBy(xpath = "//a[normalize-space()='Log out']")
    private WebElement logOutLink;
    @FindBy(xpath = "//h1[normalize-space()='Welcome, Please Sign In!']")
    private WebElement welcomeSuccessMessage;

    public LoginPage(WebDriver driver) {
        super(driver);

    }

    // Actions
    public void login(String email, String password) {
        // Use BasePage's reusable type method
        type(emailField, email);
        type(passwordField, password);

        // Click login button
        click(loginButton);
    }


    public String getInvalidLoginMessage() {
        if (isElementDisplayed(loginErrorMessage)) {
            return loginErrorMessage.getText();
        }
        System.out.println("Invalid login message not found!");
        return null;
    }

    public boolean isMyLogOutLink() {
        try {
            // Use VERY short timeout for logout check - 2 seconds max!
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOf(logOutLink));
            return true;
        } catch (TimeoutException e) {
            return false; // Element not found within 2 seconds
        }
    }

    public boolean isLoginPageLoaded() {
        return isElementDisplayed(welcomeSuccessMessage);
    }
}
