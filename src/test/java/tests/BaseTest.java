package tests;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import utils.ExtentReportManager;
import utils.Log;
import utils.TestUtil;

public class BaseTest {

    protected static ExtentReports extent;
    public Properties prop;
    protected WebDriver driver;
    protected ExtentTest test;

    // ===== Constructor =====
    public BaseTest() {
        loadProperties();
        Log.info("BaseTest constructor executed. Properties loaded.");

        extent = ExtentReportManager.getReportInstance(); // Initialize ExtentReports

        // Create a default test for the class
        String testName = this.getClass().getSimpleName();
        test = extent.createTest(testName);
        Log.info("ExtentTest created for: " + testName);
    }

    // ===== AfterMethod for teardown =====
    @AfterMethod(alwaysRun = true)
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath =
                    ExtentReportManager.captureScreenshot(driver, "LoginFailure");

            test.fail(
                    "Test Failed.. Check Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
            );
        }

        if (driver != null) {
            Log.info("Closing Browser...");
            extent.flush();
            driver.quit();
        }
    }

    // ===== Initialize browser =====
    protected void initialization() {

        String browserName = prop.getProperty("browser");
        Log.info("Initializing browser: " + browserName);
        if (test != null) test.info("Initializing browser: " + browserName);

        if ("chrome".equalsIgnoreCase(browserName)) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

            Log.info("ChromeDriver launched successfully.");
            if (test != null) test.pass("ChromeDriver launched successfully.");

        } else if ("FF".equalsIgnoreCase(browserName)) {

            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);

            Log.info("FirefoxDriver launched successfully.");
            if (test != null) test.pass("FirefoxDriver launched successfully.");

        } else {
            String msg = "Browser not supported: " + browserName;
            Log.error(msg);
            if (test != null) test.fail(msg);
            throw new RuntimeException(msg);
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

        String url = prop.getProperty("url");
        driver.get(url);
        Log.info("Navigated to URL: " + url);
        if (test != null) test.pass("Navigated to URL: " + url);
    }

    // ===== Load configuration properties (constructor-safe) =====
    private void loadProperties() {

        prop = new Properties();

        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (inputStream == null) {
                Log.error("config/config.properties not found in classpath");
                throw new RuntimeException(
                        "config/config.properties not found in classpath"
                );
            }

            prop.load(inputStream);
            Log.info("Configuration properties loaded successfully.");

        } catch (IOException e) {
            Log.error("Configuration loading failed: " + e.getMessage());
            throw new RuntimeException("Configuration loading failed", e);
        }
    }
}
