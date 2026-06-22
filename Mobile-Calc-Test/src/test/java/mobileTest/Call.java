package mobileTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Call {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");

        // ✅ Your device UDID
        options.setUdid("4H9DRCAM6X8TLVJN");

        // 📱 Google Phone app (generic & stable)
        options.setAppPackage("com.google.android.dialer");
        options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");

        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("Phone app launched successfully!");
    }

    @Test
    public void makeCall() {

        // 📞 Open first call log entry (STABLE - no dynamic text)
        WebElement firstCall = driver.findElement(
                AppiumBy.xpath("(//android.view.ViewGroup[contains(@resource-id,'call_log_entry_root')])[1]")
        );
        firstCall.click();

        // 📞 Click call button (stable accessibility id)
        driver.findElement(AppiumBy.accessibilityId("call")).click();

        // 📡 Validate call screen (end call button)
        WebElement endCall = driver.findElement(
                AppiumBy.accessibilityId("End call")
        );

        Assert.assertTrue(endCall.isDisplayed(), "Call screen not displayed!");

        System.out.println("Call initiated successfully...");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            System.out.println("Closing session...");
            driver.quit();
        }
    }
}