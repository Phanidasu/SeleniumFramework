package com.hms.pages;

import org.openqa.selenium.By;

import com.hms.core.BaseClass;
import com.hms.core.CommonMethod;

public class mercuryRegisterPage extends BaseClass {
	
	
	
	public By first_name = By.name("firstName");
	public By last_name = By.name("lastName");
	public By phone = By.name("phone");
	public By email = By.name("userName");
	public By address = By.name("address1");
	public By city = By.name("city");
	public By state = By.name("state");
	public By postalCode = By.name("postalCode");
	public By country = By.name("country");
	public By username = By.name("email");
	public By password = By.name("password");
	public By confirmPassword = By.name("confirmPassword");
	public By submit = By.name("register");
	
	
	
	
	public void filleData() throws Exception {
		
		CommonMethod.clear(first_name);
		CommonMethod.sendKeys(first_name, "Sachin");
		
		CommonMethod.clear(last_name);
		CommonMethod.sendKeys(last_name, "Tendulkar");
		
		CommonMethod.clear(phone);
		CommonMethod.sendKeys(phone, "7896541230");
		
		CommonMethod.clear(email);
		CommonMethod.sendKeys(email, "Sachin78965@gmail.com");
		
		CommonMethod.clear(address);
		CommonMethod.sendKeys(address, "HYD");
		
		CommonMethod.clear(city);
		CommonMethod.sendKeys(city, "HYD");
		
		CommonMethod.clear(postalCode);
		CommonMethod.sendKeys(postalCode, "500090");
		
		CommonMethod.selectByIndex(country, 5);
		
		CommonMethod.clear(username);
		CommonMethod.sendKeys(username, "sachintt");
		
		CommonMethod.clear(password);
		CommonMethod.sendKeys(password, "Test@1234");
		
		
		CommonMethod.clear(confirmPassword);
		CommonMethod.sendKeys(confirmPassword, "Test@1234");
		
		
		
	}
	
	
	
	public void clickOnSyumbitButton() throws Exception {
		
		CommonMethod.click(submit);
	}
	
	
	public void scroll() {
		
		
		
	}
	
	
	
	
	
	
	
	
	

}
