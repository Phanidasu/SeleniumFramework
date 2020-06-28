package com.hms.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.hms.utilities.ExcelReader;
import com.hms.utilities.PropertyFileReader;

public class BaseClass {
	
	public static WebDriver driver ;
	public ExcelReader excel;
	public static Logger log = Logger.getLogger("BaseClass");
	public Properties prop = new Properties();
	
	//Extent Reporter
	public static ExtentReports extent;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentTest test;
	
	
	@BeforeClass
	@Parameters({"browser","env", "mode"})
	public void setup(String browser, String env, String mode) throws IOException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\Log4j.properties");
		prop.load(file);
		PropertyConfigurator.configure(prop);
		log.info("Log4j Properties configured....");
		
		//Extent Reporter
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/Reports/testReport.html");
		extent.attachReporter(htmlReporter);
		
		
		
		//Excel File COnfiguration
		excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\main\\resources\\TestData.xlsx");
		
		log.info("Excel Properties configured....");
		
		log.info("Seelcted Browser is : "+browser);
		log.info("Seelcted Browser mode is : "+mode);
		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/DriverFiles/chromedriver.exe");

			if(mode.equalsIgnoreCase("head")) {
				
				driver = new ChromeDriver();
			    driver.manage().window().setSize(new Dimension(1920, 1080));
			    driver.manage().window().maximize();

			}
			
			else if(mode.equalsIgnoreCase("headless")) {
				
				ChromeOptions options = new ChromeOptions();
			    options.addArguments("--headless", "--disable-gpu","--window-size=1920,1200");
			    driver = new ChromeDriver(options);

			}
			
			
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			//work with firefox
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/DriverFiles/geckodriver.exe");
				if(mode.equalsIgnoreCase("head")) {
					
					driver = new FirefoxDriver();
				}
				
				else if(mode.equalsIgnoreCase("headless")) {
					
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setHeadless(true);
					driver = new FirefoxDriver(firefoxOptions);
					
				}
			}
			
		else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/DriverFiles/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		else {
			throw new RuntimeException("Incorrect browser provided. Supporting browsers are chrome, firefox, ie");
		}
		
		//maximize
		driver.manage().window().maximize();
		log.info("Browser Maximized");
	
		PropertyFileReader prop = new PropertyFileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\environment.properties");
		
		String url = null;
		log.info("Seelcted Environment is "+env);
		if(env.equalsIgnoreCase("qa")) {
			
			//navigate
			url = prop.getKeyValue("QA");
		}
		
		else if(env.equalsIgnoreCase("stage")) {
			
			url = prop.getKeyValue("STAGE");
		}
		
		else if(env.equalsIgnoreCase("dev")) {
			
			//navigate
			url = prop.getKeyValue("DEV");
		}
		log.info("Seelcted Environment url is "+url);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		String user = System.getProperty("user.name");
		extent.setSystemInfo("Environment", env);
        extent.setSystemInfo("Browser", browser);
        extent.setSystemInfo("Browser Mode", mode);
        extent.setSystemInfo("URL", url);
        extent.setSystemInfo("User", user);
        
        htmlReporter.config().setDocumentTitle("Mercury Automation Reports");
        htmlReporter.config().setReportName("Automation Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
       
	}
	
	
	
	@AfterClass
	public void teardown() {
		driver.close();
		log.info("Closing Browser");
		extent.flush();
	}
	
	
	@AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
  }
	
	
	
	
	
	
	
	
	
	

}
