package com.hms.tests;


import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.hms.core.BaseClass;
import com.hms.core.CommonMethod;
import com.hms.pages.LoginPage;
import com.hms.pages.mercuryRegisterPage;


public class SmokeTest extends BaseClass {
	
	//LoginPage loginpage = new LoginPage();
	mercuryRegisterPage mercury = new mercuryRegisterPage();
	
	@Test(priority=1)
	public void login() throws InterruptedException, IOException {
			
		test = extent.createTest("SFFO-1: Login Test");
		try {
			//loginpage.validateGUIOfLoginPage();
			//loginpage.fillLoginFields();
			//loginpage.clickOnLoginButton();
			//loginpage.validateLoginPage();
			mercury.filleData();
			mercury.clickOnSyumbitButton();
			
			
			
		}
		
		catch(Throwable t){
			 CommonMethod.takesScreenshot("LoginFailed");
	         Error e1 = new Error(t.getMessage()); 
	         e1.setStackTrace(t.getStackTrace()); 
	         throw e1;
		}	
	}
	
	

	
	
	
}
