package com.automation.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.automation.pages.LoginPage;
import com.aventstack.extentreports.Status;

import commonLibs.implementationn.CommonDriver;
import commonLibs.utilities.ConfigUtilities;
import commonLibs.utilities.ReportUtilities;
import commonLibs.utilities.ScreenshotUtilities;

public class BaseTests {
	
	CommonDriver cmnDriver;
	String url;
	
	WebDriver driver;
	
	LoginPage loginpage;
	ScreenshotUtilities screenshot;
	
	String configFileName;
	String currentWorkingDirectory;
	
	Properties configProperty;
	
	String reportFilename;
	ReportUtilities reportUtilities;
	
	
	@BeforeSuite
	public void preSetup() throws Exception {
		currentWorkingDirectory=System.getProperty("user.dir");
		configFileName=currentWorkingDirectory+"/config/config.properties";
		reportFilename=currentWorkingDirectory+"/reports/automaiton.html";
		
		configProperty=ConfigUtilities.readProperties(configFileName);
		
		reportUtilities=new ReportUtilities(reportFilename);
	}
	@BeforeClass
	public void setup() throws Exception{
		url = configProperty.getProperty("baseUrl");
		String browserType=configProperty.getProperty("browserType");
		
		cmnDriver = new CommonDriver(browserType);
		
		driver=cmnDriver.getDriver();
		
		loginpage=new LoginPage(driver);
		
		screenshot=new ScreenshotUtilities(driver);
		
		cmnDriver.navigateToUrl(url);
	}
	@AfterMethod
	public void postTestAction(ITestResult result) throws Exception {
		String testcasename = result.getName();
		
		long executionTime=System.currentTimeMillis();
		
		String screenshotFilename=currentWorkingDirectory+"/screenshot/"
				+testcasename+executionTime+".jpeg";
		
		if(result.getStatus()==ITestResult.FAILURE) {
			reportUtilities.addTestLog(Status.FAIL, "what a failure");
			screenshot.captureAndSaveScreenshot(screenshotFilename);
			reportUtilities.attachScreenshotToReport(screenshotFilename);
		}
	}
	
	@AfterClass
	public void tearDown() {
		cmnDriver.closeAllBrowser();
	}
	
	@AfterSuite
	public void postTeardown() {
		reportUtilities.flushReport();
	}

}
