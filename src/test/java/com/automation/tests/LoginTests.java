package com.automation.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pages.LoginPage;
import com.aventstack.extentreports.Status;

public class LoginTests extends BaseTests{
	
	@Parameters({"username","password"})

	@Test
	
	public void verifyUserLoginWithCorrectCredentials(String username,String password) {
		reportUtilities.createTestCase("Verify Login Function");
		reportUtilities.addTestLog(Status.INFO, "Performing log");
		loginpage.loginToApplication(username, password);
		
		String expectedTitle = "Guru99 Bank Manager HomePage";
		String actualTitle = cmnDriver.getTitleOfThePage();
		
		reportUtilities.addTestLog(Status.INFO, "Comparing expected and actual title");
		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}
}
