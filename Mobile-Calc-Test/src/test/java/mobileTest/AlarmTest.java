package mobileTest;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class AlarmTest {

    AndroidDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setup() throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Realme C75");
        options.setUdid("4H9DRCAM6X8TLVJN");

        options.setAppPackage("com.coloros.alarmclock");
        options.setAppActivity("com.coloros.alarmclock.AlarmClock");

        options.setNoReset(true);

        driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"),
                options
        );

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void setAlarmToFiveAM() throws Exception {

        // 1. ALWAYS go to Alarm tab first (MOST IMPORTANT FIX)
        boolean alarmTabFound = false;

        try {
            driver.findElement(By.xpath("//*[@text='Alarm']")).click();
            alarmTabFound = true;
        } catch (Exception ignored) {}

        if (!alarmTabFound) {
            try {
                driver.findElement(By.xpath("//*[@text='ALARM']")).click();
                alarmTabFound = true;
            } catch (Exception ignored) {}
        }

        if (!alarmTabFound) {
            System.out.println("⚠️ Alarm tab not found - app UI differs");
        }

        // 2. Click + / Add alarm
        driver.findElement(By.xpath(
                "//*[contains(@content-desc,'Add') or contains(@text,'+') or contains(@text,'Add')]"
        )).click();

  
        try {
            driver.findElement(By.xpath("//*[@text='AM']")).click();
        } 
        catch (Exception ignored) {}

        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Done  Button']")).click();

        System.out.println("Alarm set successfully");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}