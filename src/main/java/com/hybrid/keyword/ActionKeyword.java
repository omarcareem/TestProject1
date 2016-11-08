package com.hybrid.keyword;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.hybrid.log.TestLogger;

public class ActionKeyword {

	public static WebDriver driver;
	public static String chromeDriverPath = System.getProperty("user.dir") + "/"
			+ "Drivers/ChromeDriver/chromedriver.exe";
	public static String ieDriverPath = System.getProperty("user.dir") + "/" + "Drivers/IEDriver/IEDriverServer.exe";

	public static By getObject(String locatorType, String locator) throws Exception {

		By objectName = null;

		if (locatorType.equalsIgnoreCase("id")) {
			objectName = By.id(locator);
		} else if (locatorType.equalsIgnoreCase("name")) {
			objectName = By.name(locator);
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			objectName = By.xpath(locator);
		} else if (locatorType.equalsIgnoreCase("className")) {
			objectName = By.className(locator);
		} else if (locatorType.equalsIgnoreCase("cssSelector")) {
			objectName = By.cssSelector(locator);
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			objectName = By.tagName(locator);
		} else if (locatorType.equalsIgnoreCase("linkText")) {
			objectName = By.linkText(locator);
		} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
			objectName = By.partialLinkText(locator);
		} else {
			throw new Exception("Wrong object type");
		}

		return objectName;
	}

	public ActionKeyword(WebDriver driver) {

		ActionKeyword.driver = driver;

	}

	public static void openBrowser(String browserName) {

		TestLogger.log.info("Openning browser");
		try {
			if (browserName.equalsIgnoreCase("firefox")) {
				// TestLogger.log.info("Creating firefox profile");
				FirefoxProfile profile = new FirefoxProfile();
				// TestLogger.log.info("Created a new firefox profile");
				// TestLogger.log.info("Accepting untrusted cert");
				profile.setAcceptUntrustedCertificates(true);
				DesiredCapabilities fireFoxCap = DesiredCapabilities.firefox();
				fireFoxCap.setCapability(FirefoxDriver.PROFILE, profile);
				fireFoxCap.setBrowserName("firefox");
				driver = new FirefoxDriver(fireFoxCap);
				// driver = new FirefoxDriver();
				driver.manage().window().maximize();
				TestLogger.log.info("Firefox browser started");

			} else if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				DesiredCapabilities chromeCap = DesiredCapabilities.chrome();
				chromeCap.setBrowserName("chrome");
				driver = new ChromeDriver(chromeCap);
				driver.manage().window().maximize();
				TestLogger.log.info("Chrome browser started");

			} else if (browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				DesiredCapabilities ieCap = DesiredCapabilities.internetExplorer();
				ieCap.setBrowserName("ie");
				driver = new InternetExplorerDriver(ieCap);
				driver.manage().window().maximize();
				TestLogger.log.info("IE browser started");
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Not able to open the browser: " + e.getMessage());
			TestLogger.log.info("Not able to open the browser: " + e.getMessage());
		}

	}

	public static void navigateToURL(String baseURL) {

		try {
			driver.get(baseURL);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			TestLogger.log.info("Navigated to " + baseURL + " URL");
		} catch (Exception e) {
			TestLogger.log.info("Unable to navigate to " + baseURL + e.getMessage());
		}

	}

	public static void enterText(String locatorType, String locator, String value) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement inputElement = driver.findElement(ActionKeyword.getObject(locatorType, locator));
			wait.until(ExpectedConditions.visibilityOf(inputElement));
			if (inputElement.isDisplayed()) {
				inputElement.clear();
				inputElement.sendKeys(value);
				System.out.println("User entered " + value + " to input field");
				TestLogger.log.info("User entered " + value + " to input field");
			}
		} catch (Exception e) {
			System.out.println("Not able to enter" + value + " to input field " + e.getMessage());
			TestLogger.log.info("Not able to enter " + value + " to input field " + e.getMessage());
		}

	}

	public static void clickButton(String locatorType, String locator) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement btnElement = driver.findElement(ActionKeyword.getObject(locatorType, locator));
			wait.until(ExpectedConditions.elementToBeClickable(btnElement));

			if (btnElement.isDisplayed()) {
				btnElement.click();
				System.out.println("User clicked on button");
				TestLogger.log.info("Clicked on the button");
			}
		} catch (Exception e) {
			System.out.println("Not able to click the button " + e.getMessage());
			TestLogger.log.info("Not able to click the button " + e.getMessage());
		}

	}

	public static void clickLink(String locatorType, String locator) throws Exception {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			WebElement linkElement = driver.findElement(ActionKeyword.getObject(locatorType, locator));
			wait.until(ExpectedConditions.elementToBeClickable(linkElement));

			if (linkElement.isDisplayed()) {
				linkElement.click();
				System.out.println("User clicked on link");
				TestLogger.log.info("Clicked on the link");
			}
		} catch (Exception e) {
			System.out.println("Not able to click the link " + e.getMessage());
			TestLogger.log.info("Not able to click the link " + e.getMessage());
		}

	}

	public static void selectValue(String locatorType, String locator, String value) throws Exception {

		try {
			Select drpDownBox = new Select(driver.findElement(ActionKeyword.getObject(locatorType, locator)));

			drpDownBox.selectByVisibleText(value);

			if (drpDownBox.getAllSelectedOptions().contains(value)) {
				System.out.println("User selected " + value + " from the drop down");
				TestLogger.log.info("Selected the " + value + " from the drop down");
			}
		} catch (Exception e) {
			System.out.println("Unable to select the " + value + " from the drop down. " + e.getMessage());
			TestLogger.log.info("Unable to select the " + value + " from the drop down. " + e.getMessage());
		}

	}

	public static void clickCheckBox(String locatorType, String locator) throws Exception {

		try {
			WebElement checkBoxElement = driver.findElement(ActionKeyword.getObject(locatorType, locator));

			checkBoxElement.click();
			System.out.println("Clicked on check box");
			TestLogger.log.info("Clicked on check box");
		} catch (Exception e) {
			System.out.println("Unable to check off the check box: " + e);
			TestLogger.log.info("Unable to check off the check box: " + e);
		}

	}

	public static void clickRadioButton(String locatorType, String locator) throws Exception {
		try {
			WebElement radioButtonElement = driver.findElement(ActionKeyword.getObject(locatorType, locator));

			radioButtonElement.click();
		} catch (Exception e) {
			System.out.println("Unable to find the element: " + e);
		}
	}

	public static boolean verifyElementEnable(String locatorType, String locator) throws Exception {

		boolean flag = false;

		WebElement element = driver.findElement(ActionKeyword.getObject(locatorType, locator));

		if (element.isEnabled()) {
			flag = true;
		}

		return flag;

	}

	public static boolean verifyElementDisable(String locatorType, String locator) throws Exception {
		boolean flag = false;

		WebElement element = driver.findElement(ActionKeyword.getObject(locatorType, locator));

		if (!element.isEnabled()) {
			flag = true;
		}

		return flag;
	}

	public static boolean verifyText(String locatorType, String locator, String value) throws Exception {

		WebElement element = driver.findElement(ActionKeyword.getObject(locatorType, locator));

		return element.getText().equalsIgnoreCase(value);

	}

	public static boolean verifyTitle(String value) {

		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(value));
		String boolPageTitle = driver.getTitle();
		System.out.println("Page title is: " + boolPageTitle);

		if (boolPageTitle.contains(value)) {
			System.out.println("Page title is correct");
			flag = true;
		} else {
			System.out.println("Page title is incorrect");
			flag = false;
		}

		Assert.assertTrue(flag);
		return flag;
	}

	public static void waitForPageTitle(String pageTitle, int numberOfSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, numberOfSeconds);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}

	public static void waitForPageLoad(int numberOfSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, numberOfSeconds);

		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	public static void closeBrowser() {

		try {
			driver.quit();
			TestLogger.log.info("Quitting the browser");
		} catch (Exception e) {
			System.out.println("Unable to close the browser: " + e);
			TestLogger.log.info("Unable to close the browser: " + e);
		}

	}

	public static void takeScreenShotofFailure(String screenShot) throws Exception {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		System.out.println("Before taking screen shot");
		File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("Created imgage file");
		String screenShotName = screenShot + "_" + timeStamp + ".png";

		// It will store all the screenshots in test-output/screenshots folder
		String destDir = System.getProperty("user.dir") + "/" + "test-output/ScreenShots";
		new File(destDir).mkdirs();
		String destFile = destDir + "/" + screenShotName;
		FileUtils.copyFile(imageFile, new File(destFile));
		System.out.println("Saving file");
	}

	public static void launchMobileApp(String browserName, String deviceName, String platformVersion,
			String platformName, String appPackage, String appActivity)
					throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability("platformName", platformName);
		capabilities.setCapability("appPackage", appPackage);
		capabilities.setCapability("appActivity", appActivity);

		driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

}
