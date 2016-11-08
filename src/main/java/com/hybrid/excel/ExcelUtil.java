package com.hybrid.excel;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hybrid.log.TestLogger;

public class ExcelUtil {

	public static Object[][] readExcelFile() throws Exception {

		// Columns in test case sheet
		String testCaseSheetTestCaseID;
		String testCaseSheetTestCaseName;
		String testCaseSheetDescription;
		String testCaseSheetExecute;

		// Columns in test steps sheet
		String testStepSheetTestCaseID = null;
		String testStepSheetTestCaseName = null;
		String testStepSheetTestStepID = null;
		String testStepSheetTestSteps = null;
		String testStepSheetKeyword = null;
		String testStepSheetLocatorType = null;
		String testStepSheetLocator = null;
		String testStepSheetLocatorvalue = null;
		String testStepSheetTestData = null;
		int waitTimeInSeconds = 0;
		int testStepRowNumber = 0;

		Object[][] val = null;

		FileInputStream fileInputStream = new FileInputStream("HybridFrameworkKeywordsTestData.xlsx");
		// Accessing the workbook

		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		TestLogger.log.debug("Accessed the workbook");

		// XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		// Accessing the Test Case sheet
		XSSFSheet testCaseSheet = workBook.getSheet("TestCase");
		TestLogger.log.debug("Accessed the Test Case sheet");
		// Accessing the Test Steps Sheet
		XSSFSheet testStepSheet = workBook.getSheet("TestSteps");
		TestLogger.log.debug("Accessed the Test Steps Sheet");
		int tcRowCount = testCaseSheet.getPhysicalNumberOfRows();
		TestLogger.log.debug("Retrieved row count in Test Case sheet");
		System.out.println("Test Case Row Count: " +tcRowCount);

		int tsRowCount = testStepSheet.getPhysicalNumberOfRows();
		System.out.println("Test Step Row Count: " + tsRowCount);
		TestLogger.log.debug("Retrieved row count in Test Step sheet");
		val = new Object[tsRowCount - 1][];
		for (int i = 1; i < tcRowCount; i++) {

			testCaseSheetTestCaseID = testCaseSheet.getRow(i).getCell(0).getStringCellValue();
			TestLogger.log.debug("Retrieved Test Case Id in test case sheet");

			testCaseSheetTestCaseName = testCaseSheet.getRow(i).getCell(1).getStringCellValue();
			TestLogger.log.debug("Retrieved Test Case Name in test case sheet");

			testCaseSheetDescription = testCaseSheet.getRow(i).getCell(2).getStringCellValue();
			TestLogger.log.debug("Retrieved Test Case Description in test case sheet");

			testCaseSheetExecute = testCaseSheet.getRow(i).getCell(3).getStringCellValue();
			TestLogger.log.debug("Retrieved Test Case Y/N value in test case sheet");

			if (testCaseSheetExecute.contains("Y")) {

				for (int j = 1; j < tsRowCount; j++) {
					testStepSheetTestCaseID = testStepSheet.getRow(j).getCell(0).getStringCellValue();

					if (testStepSheetTestCaseID.contains(testCaseSheetTestCaseID)) {
						System.out.println("================== Executing " + testCaseSheetTestCaseID
								+ " ================================");
						testStepRowNumber = j;

						testStepSheetTestCaseName = testStepSheet.getRow(j).getCell(1).getStringCellValue();

						testStepSheetTestStepID = testStepSheet.getRow(j).getCell(2).getStringCellValue();

						testStepSheetTestSteps = testStepSheet.getRow(j).getCell(3).getStringCellValue();

						testStepSheetKeyword = testStepSheet.getRow(j).getCell(4).getStringCellValue();

						testStepSheetLocatorType = testStepSheet.getRow(j).getCell(5).getStringCellValue();

						testStepSheetLocator = testStepSheet.getRow(j).getCell(6).getStringCellValue();

						testStepSheetLocatorvalue = testStepSheet.getRow(j).getCell(7).getStringCellValue();

						testStepSheetTestData = testStepSheet.getRow(j).getCell(8).getStringCellValue();

						waitTimeInSeconds = (int) testStepSheet.getRow(j).getCell(9).getNumericCellValue();

						val[j - 1] = new Object[] { testStepSheetTestCaseID, testStepSheetTestCaseName,
								testStepSheetTestStepID, testStepSheetTestSteps, testStepSheetKeyword,
								testStepSheetLocatorType, testStepSheetLocatorvalue, testStepRowNumber,
								testStepSheetTestData, waitTimeInSeconds };

					}

				}

			}

		}
		workBook.close();
		return val;

	}

	public static void writeToExcelFile(String TCID, String TCName, String TSID, String TestStep, String Result,
			int RowNum, String sheetName) throws Exception {

		FileInputStream fis = new FileInputStream("HybridFrameworkKeywordsTestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet(sheetName);
		// Create First Row
		XSSFRow row1 = sheet.createRow(RowNum);
		XSSFCell col1 = row1.createCell(0);
		col1.setCellValue(TCID);

		// XSSFRow row2 = sheet.createRow(RowNum);
		XSSFCell col2 = row1.createCell(1);
		col2.setCellValue(TCName);

		XSSFCell col3 = row1.createCell(2);
		col3.setCellValue(TSID);

		XSSFCell col4 = row1.createCell(3);
		col4.setCellValue(TestStep);

		XSSFCell col5 = row1.createCell(4);
		col5.setCellValue(Result);

		fis.close();
		FileOutputStream os = new FileOutputStream("HybridFrameworkKeywordsTestData.xlsx");
		workbook.write(os);
		os.close();
		workbook.close();
	}

}
