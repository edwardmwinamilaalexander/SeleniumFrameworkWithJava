package pages;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

    // Page Elements
    @FindBy(css = "#FirstName")
    private WebElement firstNameField;

    @FindBy(css = "#LastName")
    private WebElement lastNameField;

    @FindBy(css = "#Email")
    private WebElement emailField;

    @FindBy(css = "#Password")
    private WebElement passwordField;

    @FindBy(css = "#ConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(css = "#register-button")
    private WebElement registerBtn;

    @FindBy(css = "div.page-title h1")
    private WebElement registerTitlePage;

    @FindBy(xpath = "//div[@class='result']")
    private WebElement registrationSuccessMessage;

    // Actions
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegisterForm(
            String firstname,
            String lastname,
            String email,
            String password,
            String confirmPassword
    ) {
        type(firstNameField, firstname);
        type(lastNameField, lastname);
        type(emailField, email);
        type(passwordField, password);
        type(confirmPasswordField, confirmPassword);
        click(registerBtn);
    }

    public boolean isRegistrationSuccessMessageDisplayed() {
        return isElementDisplayed(registrationSuccessMessage);
    }

    public String getRegistrationSuccessMessageText() {
        return registrationSuccessMessage.getText();
    }

    public boolean isRegisterPageLoaded() {
        return isElementDisplayed(registerTitlePage);
    }
}
