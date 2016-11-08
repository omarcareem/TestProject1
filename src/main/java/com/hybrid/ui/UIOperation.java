package com.hybrid.ui;

import org.openqa.selenium.WebDriver;

import com.hybrid.keyword.ActionKeyword;



public class UIOperation extends ActionKeyword{
	
	
	
	public UIOperation(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void performUIOperation(String keyWord, String locatorType, String locatorValue, String testData, int waitTimeInSeconds) throws Exception{
		
		
				
		switch(keyWord){
		
		case "openBrowser":
			ActionKeyword.openBrowser(testData);
			break;
		case "navigateToURL":
			ActionKeyword.navigateToURL(testData);
			break;			
		case "enterText":
			ActionKeyword.enterText(locatorType, locatorValue, testData);
			break;
		case "clickButton":
			ActionKeyword.clickButton(locatorType, locatorValue);
			break;
		case "clickLink":
			ActionKeyword.clickLink(locatorType, locatorValue);
			break;
		case "selectValue":
			ActionKeyword.selectValue(locatorType, locatorValue, testData);
			break;
		case "clickCheckBox":
			ActionKeyword.clickCheckBox(locatorType, locatorValue);
			break;
		case "clickRadioButton":
			ActionKeyword.clickRadioButton(locatorType, locatorValue);
			break;
		case "verifyElementEnable":
			ActionKeyword.verifyElementEnable(locatorType, locatorValue);
			break;
		case "waitForPageTitle":
			ActionKeyword.waitForPageTitle(testData, waitTimeInSeconds);
			break;
		case "waitForPageLoad":
			ActionKeyword.waitForPageLoad(waitTimeInSeconds);
			break;
		case "verifyElementDisable":
			ActionKeyword.verifyElementDisable(locatorType, locatorValue);
			break;
		case "verifyText":
			ActionKeyword.verifyText(locatorType, locatorValue, testData);			
			break;
		case "verifyTitle":
			ActionKeyword.verifyTitle(testData);
			break;
		case "closeBrowser":
			ActionKeyword.closeBrowser();			
			break;
		}
		
		
	}
	


}
