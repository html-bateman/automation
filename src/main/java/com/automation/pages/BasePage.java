package com.automation.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementationn.ElementControl;

public class BasePage {
	WebDriver driver;
	
	public ElementControl elementControl;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		
		elementControl = new ElementControl(driver);
	}

}
