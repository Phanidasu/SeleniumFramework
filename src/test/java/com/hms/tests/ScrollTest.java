package com.hms.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScrollTest {
	
	public static WebDriver driver;
	
	
	
	public static void setup() throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://google.com");
		Thread.sleep(10000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement Element = driver.findElement(By.linkText("VBScript"));
        js.executeScript("arguments[0].scrollIntoView();", Element);
        
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ScrollTest.setup();
	}

}
