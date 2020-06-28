package com.hms.core;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

public class CommonMethod extends BaseClass {


	
	public static void clear(By locator) throws Exception {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.findElement(locator).clear();
			log.info("Clearing on object locator : "+ locator);
			test.log(Status.PASS, "Clearing Text field :"+locator);
			//MediaEntityBuilder.createScreenCaptureFromPath(takesScreenshot("test")).build()
		}
		catch (Exception e) {
			log.error("Clearing on object locator failed: "+locator);
			log.error(e.getMessage());
			test.log(Status.FAIL, "Clearing Text field Failed:"+locator+"::"+e);
			throw e;
		}

		
	}
	
	
	public static void click(By locator) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.findElement(locator).click();
			log.info("Clicking on the field : "+ locator);
			test.log(Status.PASS, "Clicking on object field :"+locator);
		}
		catch (Exception e) {
			log.error("Clicking on object locator failed: "+locator);
			log.error(e.getMessage());
			test.log(Status.FAIL, "Clicking on object field Failed :"+locator+"::"+e);
			throw e;
		}		
	}
	
	public static void sendKeys(By locator, String data) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			driver.findElement(locator).sendKeys(data);
			log.info("Sending data to the object locator : "+ locator +"Data is : "+data);
			test.log(Status.PASS, "passing data to the text  field: "+locator);
			
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Sending data to the object locator Failed: "+ locator +"Data is : "+data);
			test.log(Status.PASS, "passing data to the text  field failed: "+locator+ "::"+e);
			log.error(e.getMessage());
			throw e;

		}		
	}
	
	public static boolean isElementPresent(By locator) {
		
		int len = driver.findElements(locator).size();
		boolean status =false;
		
		if(len>0) {
			
			status = true;
		}
		
		return status;
	}
	
	public static String getTitle() {
		
		return driver.getTitle();
	}
	
	public static String getCurrentUrl() {
		
		return driver.getCurrentUrl();
	}
	
	public static String getText(By locator) {
		
		return driver.findElement(locator).getText();
	}
	
	public static String getAttributeValue(By locator) {
		
		return driver.findElement(locator).getAttribute("value");
	}
	
	public static String takesScreenshot(String ScreenshotName) throws IOException{
	       
    //Creating Object for Date
	Date date = new Date();
	//Creating object for time stamp
	Timestamp timestamp= new Timestamp(date.getTime());
	//converting date format to String
	String time=timestamp.toString();
	//replacing all spaces with '-'
	time = time.replace(' ', '-');
	//replacing all colons with '-'
	time = time.replace(':', '-'); 
	
	//Capturing screenshot as File output type 
	File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//copying screenshot to local machine directory
	FileUtils.copyFile(screen, new File(System.getProperty("user.dir")+"\\Screenshots\\"+"_"+ScreenshotName+"_"+time+"_"+"_"+".jpg"));	
	return System.getProperty("user.dir")+"\\Screenshots\\"+"_"+ScreenshotName+"_"+time+"_"+"_"+".jpg";
	}
	
	//frame index
	public static void handleFrame(int index) {
		
		driver.switchTo().frame(index);
	}
	
	//frame ID, Name
	public static void handleFrame(String name) {
		
		driver.switchTo().frame(name);
	}
	
	//any locator
	public static void handleFrame(By locator) {
		
		driver.switchTo().frame(driver.findElement(locator));
		
	}
	
	public static void fileUpload(By locator, String path) {
		
		driver.findElement(locator).sendKeys(path);
	}
	
	
	//handle window or Tab
	 public static void handleNewTab()
	 {
	  Set<String> allWindowHandles = driver.getWindowHandles();
	  
	  Iterator<String> iter=allWindowHandles.iterator();
	  int size=allWindowHandles.size();
	  String child = null;
	  
	  for(int i=0;i<size;i++){
	   
		  child=iter.next();
	  }
	  
	  driver.switchTo().window(child);
	 }
	
	 
	 //Handling Parent window(tab) from child window(tab)
	 public static void handleParentTab(){
	  
	  Set<String> allWindowHandles = driver.getWindowHandles();
	  
	  String parent = (String) allWindowHandles.toArray()[0];
	  driver.switchTo().window(parent);

	 }
	 
	 //Handling tab using int
	 public static void handleTab(int tabNum){
	  
	  Set<String> allWindowHandles = driver.getWindowHandles();
	  
	  String parent = (String) allWindowHandles.toArray()[tabNum];
	  driver.switchTo().window(parent);

	 }
		
	 public static void alertAccept() {
		 Alert alert = driver.switchTo().alert();
		 alert.accept(); 
	 }
		
	
	 public static void alertDismiss() {
		 Alert alert = driver.switchTo().alert();
		 alert.dismiss(); 
	 }
	 
	 public static String getAlertText() {
		 Alert alert = driver.switchTo().alert();
		 return alert.getText();
	 }
	
	 public static boolean isAlertPresent(){
		 boolean foundAlert = false;
		 WebDriverWait wait = new WebDriverWait(driver, 30 /*timeout in seconds*/);
		   
		 try {
		        wait.until(ExpectedConditions.alertIsPresent());
		        foundAlert = true;
		        
		    } catch (TimeoutException eTO) {
		    	
		        foundAlert = false;
		    }
		    return foundAlert;
		} 
	 
	public static void handleMouseHover(By locator) {
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).build().perform();
		
	}

	public static void handleDragAndDrop(By drag, By drop ) {
		
		Actions action = new Actions(driver);
		action.dragAndDrop(driver.findElement(drag), driver.findElement(drop)).build().perform();
		
	}
	
	public static void handleMouseHoverClick(By locator) {
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).click().build();
		
	}
	
	public static void handleKeysTab() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		
	}
	
	public static void handleKeysEnter() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		
	}
	
	public static void handleKeysTab(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).sendKeys(Keys.TAB).build().perform();
		
	}
	
	public static void handleKeysEnter(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(locator)).sendKeys(Keys.ENTER).build().perform();
		
	}
	
	public static void selectByVisibletext(By locator, String text) {
		
		new Select(driver.findElement(locator)).selectByVisibleText(text);
	}
	
	public static void selectByValue(By locator, String value) {
		
		new Select(driver.findElement(locator)).selectByValue(value);
	}
	
	public static void selectByIndex(By locator, int index) {
		
		new Select(driver.findElement(locator)).selectByIndex(index);
	}
	
	
	public static void scrollToElement(By locator) {
		
		//scroll in to view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement Element = driver.findElement(locator);
		js.executeScript("arguments[0].scrollIntoView();", Element);
		        
	}
	
	public static void scrollHeight() {
			
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(1000,1000)");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			        
		}
	
	
	public static void VerticalScroll(String pixel) {
		
		//scroll in to view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+pixel+")");
		        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
