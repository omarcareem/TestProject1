package com.hybrid.test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hybrid.excel.ExcelUtil;
import com.hybrid.ui.UIOperation;



public class ActionDriver {

	public static WebDriver driver;
	//UIOperation uiOperation = new UIOperation(driver);

	@DataProvider(name = "testData")
	public Object[][] getTestData() throws Exception {

		Object[][] testObjArray = ExcelUtil.readExcelFile();

		return (testObjArray);
	}

	@Test(groups = { "hybridTestGroup" }, dataProvider = "testData")
	public void myKDFTest(String testStepSheetTestCaseID, String testStepSheetTestCaseName,
			String testStepSheetTestStepID, String testStepSheetTestSteps, String testStepSheetKeyword,
			String testStepSheetLocatorType, String testStepSheetLocatorvalue, int testStepRowNumber,
			String testStepSheetTestData, int waitTimeInSeconds) throws Exception {

		UIOperation.performUIOperation(testStepSheetKeyword, testStepSheetLocatorType, testStepSheetLocatorvalue,
				testStepSheetTestData, waitTimeInSeconds);

	}

	@AfterMethod(groups = { "hybridTestGroup" })
	public void myKDFAfterMethod(Object[] parameters, ITestResult result) {

		String testStepSheetTestCaseID = (String) parameters[0];
		String testStepSheetTestCaseName = (String) parameters[1];
		String testStepSheetTestStepID = (String) parameters[2];
		String testStepSheetTestSteps = (String) parameters[3];
		int testStepRowNumber = (int) parameters[7];	

		try {
			if (result.getStatus() == ITestResult.SUCCESS) {
				ExcelUtil.writeToExcelFile(testStepSheetTestCaseID, testStepSheetTestCaseName, testStepSheetTestStepID,
						testStepSheetTestSteps, "Pass", testStepRowNumber, "TestResult");
			} else if (result.getStatus() == ITestResult.FAILURE) {
				ExcelUtil.writeToExcelFile(testStepSheetTestCaseID, testStepSheetTestCaseName, testStepSheetTestStepID,
						testStepSheetTestSteps, "Fail", testStepRowNumber, "TestResult");

				UIOperation.takeScreenShotofFailure(testStepSheetTestSteps);
			} else if (result.getStatus() == ITestResult.SKIP) {
				ExcelUtil.writeToExcelFile(testStepSheetTestCaseID, testStepSheetTestCaseName, testStepSheetTestStepID,
						testStepSheetTestSteps, "Skip", testStepRowNumber, "TestResult");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
