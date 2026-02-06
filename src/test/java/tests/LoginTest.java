
package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.ExcelUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;

    public LoginTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization(); // From BaseTest
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData", description = "Verify login using Excel data")
    public void testLoginExcel(String email, String password) {
        homePage.navigateToLoginPage();
        loginPage.login(email, password);

        if (loginPage.isMyLogOutLink()) {
            // Login successful
            System.out.println("Login succeeded for: " + email);
        } else {
            // Login failed - verify error message
            String errorMsg = loginPage.getInvalidLoginMessage();
            Assert.assertNotNull(errorMsg, "No error message displayed for: " + email);
            Assert.assertFalse(errorMsg.isEmpty(), "Empty error message for: " + email);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
        ExcelUtils.loadExcel(filePath, "LoginData");
        int rowCount = ExcelUtils.getRowCount();
        Object[][] data = new Object[rowCount - 1][2];

        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
            data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
        }
        ExcelUtils.closeExcel();
        return data;
    }
}