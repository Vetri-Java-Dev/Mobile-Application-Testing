package mobileTest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;


public class CalculatorTest {


    AndroidDriver driver;


    @BeforeTest
    public void setup() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();

        // Device details
        options.setDeviceName("Android Device");

        // Calculator app details
        options.setAppPackage("com.coloros.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");
        
        options.setNoReset(true);

        driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),options);

    }

    @Test
    public void additionTest() {

        // Click 5
        driver.findElement(By.id("com.coloros.calculator:id/digit_5")).click();

        // Click +
        driver.findElement(By.id("com.coloros.calculator:id/op_add")).click();

        // Click 3
        driver.findElement(By.id("com.coloros.calculator:id/digit_3")).click();

        // Click =
        driver.findElement(By.id("com.coloros.calculator:id/img_eq")).click();

        // Get Result
        WebElement result=driver.findElement(By.id("com.coloros.calculator:id/result"));

        String actualResult=result.getText();

        Assert.assertEquals(actualResult, "8");
        
        System.out.print("Addition Test Passed");


    }
    
    @Test 
    public void subtractTest() {
    	
    		driver.findElement(By.id("com.coloros.calculator:id/digit_9")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/img_op_sub")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/digit_5")).click();
    		
    		driver.findElement(By.id("com.coloros.calculator:id/img_eq")).click();
    		
    		Assert.assertEquals(driver.findElement(By.id("com.coloros.calculator:id/result")).getText(), "4");
    		
    		System.out.print("Subtract Test Passed");
    }
    
    @Test 
    public void multiplicationTest() {
    	
    		driver.findElement(By.id("com.coloros.calculator:id/digit_1")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/digit_00")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/img_op_mul")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/digit_5")).click();
    		
    		driver.findElement(By.id("com.coloros.calculator:id/img_eq")).click();
    		
    		Assert.assertEquals(driver.findElement(By.id("com.coloros.calculator:id/result")).getText(), "500");
    		
    		System.out.print("Multiplication Test Passed");
    }
    
    @Test 
    public void divisionTest() {
    	
    		driver.findElement(By.id("com.coloros.calculator:id/digit_1")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/digit_00")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/img_op_div")).click();
    		driver.findElement(By.id("com.coloros.calculator:id/digit_5")).click();
    		
    		driver.findElement(By.id("com.coloros.calculator:id/img_eq")).click();
    		
    		Assert.assertEquals(driver.findElement(By.id("com.coloros.calculator:id/result")).getText(), "20");
    		
    		System.out.print("Division Test Passed");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}