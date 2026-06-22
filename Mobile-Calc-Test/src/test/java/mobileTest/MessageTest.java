package mobileTest;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class MessageTest {

    public static void main(String[] args) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Android Device");
        options.setUdid("4H9DRCAM6X8TLVJN");

        options.setAppPackage("com.whatsapp");
        options.setAppActivity(".HomeActivity");
        options.setNoReset(true);

        AndroidDriver driver = new AndroidDriver(
                new URL("http://127.0.0.1:4723"), options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 1. Wait for WhatsApp home screen
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//android.widget.FrameLayout")));

        // 2. Click first chat (pinned/recent chat)
        WebElement firstChat = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("Pinned chat")
                )
        );
        firstChat.click();

        // 3. Wait for message box
        WebElement messageBox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("com.whatsapp:id/entry")
                )
        );

        String message = "Hello Buddy from Appium";
        messageBox.sendKeys(message);

        // 4. Click send
        WebElement sendBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("com.whatsapp:id/send")
                )
        );
        sendBtn.click();

        System.out.println("Message sent successfully");

        driver.quit();
    }
}