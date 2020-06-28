package com.hms.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.hms.core.BaseClass;
import com.hms.core.CommonMethod;

public class LoginPage extends BaseClass {

	
	public By username_field = By.name("username");
	public By password_field = By.name("password");
	public By login_button = By.name("submit");
	public By reset_button = By.name("reset");
	public By username_label = By.xpath("//label[text()='Username']");
	public By password_label = By.xpath("//label[text()='Password']");
	
	public void fillLoginFields() throws Exception {
		
		String username = excel.getCellData("Login", "Username", 2);
		String password = excel.getCellData("Login", "Password", 2);
		CommonMethod.clear(username_field);
		CommonMethod.sendKeys(username_field, username);
		CommonMethod.clear(password_field);
		CommonMethod.sendKeys(password_field, password);
		
	}
	
	public void clickOnLoginButton() throws Exception {
		
		CommonMethod.click(login_button);	
	}
	
	public void validateGUIOfLoginPage() {
		
		Assert.assertTrue(CommonMethod.isElementPresent(username_label));
		Assert.assertTrue(CommonMethod.isElementPresent(password_label));
		Assert.assertTrue(CommonMethod.isElementPresent(login_button));
		Assert.assertTrue(CommonMethod.isElementPresent(reset_button));	
	}
	
	public void validateLoginPage() {
		
		String actual_title = CommonMethod.getTitle();
		Assert.assertEquals(actual_title, "Master Page1");
		
	List<WebElement> wb = driver.findElements(By.xpath("(//div[@class='myPillContainer'])[1]//span[@class='fal fa-times ux-m-l-12 ux-m-r-15']"));
		
		for(int i=1;i<wb.size();i++) {
			wb.get(i).click();
			if(i==4) {
				break;
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
