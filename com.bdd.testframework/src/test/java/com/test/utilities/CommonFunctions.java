package com.test.utilities;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class CommonFunctions {

	public static WebDriver driver;
	public static Logger log;

	public CommonFunctions(WebDriver driver) {
		log = Logger.getLogger(CommonFunctions.class);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//Log4j.properties");
		CommonFunctions.driver = driver;
	}

	/**
	 * This method is used for maximizing the browser and navigating to required url
	 * 
	 * @param url
	 */
	public void openPage(String url) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader.getConfigPropertyValue("Timeout")),
				TimeUnit.SECONDS);
		driver.get(url);

	}

	public static String getTitle() {
		return driver.getTitle();
	}

	public static void switchToIframe(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * This method is used to return a web element
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement webElmnt(String locator) {
		if (locator.startsWith("xpath")) {
			return driver.findElement(By.xpath(locator.substring(6)));
		}

		else if (locator.startsWith("css"))
			return driver.findElement(By.cssSelector(locator.substring(4)));

		else if (locator.startsWith("id"))
			return driver.findElement(By.id(locator.substring(3)));

		else if (locator.startsWith("class"))
			return driver.findElement(By.className(locator.substring(6)));

		else if (locator.startsWith("name"))
			return driver.findElement(By.name(locator.substring(5)));

		else if (locator.startsWith("tag"))
			return driver.findElement(By.tagName(locator.substring(4)));

		else if (locator.startsWith("link"))
			return driver.findElement(By.linkText(locator.substring(5)));

		else
			CommonFunctions.insertLogMessage("Unable to locate web element with the locator format: " + locator);
		throw new IllegalArgumentException("Invalid locator format: " + locator);
	}

	/**
	 * This method is used to insert Log Message
	 * 
	 * @param message
	 */
	public static void insertLogMessage(String message) {
		log.info(message);
	}

	/**
	 * This method is used to Hover on element through mouse
	 * 
	 * @param locator
	 */
	public static void mouseHover(String locator) {
		By byLocator = elementLocator(locator);
		WebElement element = driver.findElement(byLocator);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * This method is used to Hover on element through mouse
	 * 
	 * @param locator
	 */
	public static void mouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	/**
	 * This method is used to return a By
	 * 
	 * @param locator
	 * @return
	 */
	public static By elementLocator(String locator) {
		if (locator.startsWith("xpath")) {
			return By.xpath(locator.substring(6));
		}

		else if (locator.startsWith("css"))
			return By.cssSelector(locator.substring(4));

		else if (locator.startsWith("id"))
			return By.id(locator.substring(3));

		else if (locator.startsWith("class"))
			return By.className(locator.substring(6));

		else if (locator.startsWith("name"))
			return By.name(locator.substring(5));

		else if (locator.startsWith("tag"))
			return By.tagName(locator.substring(4));

		else if (locator.startsWith("link"))
			return By.linkText(locator.substring(5));

		else
			CommonFunctions.insertLogMessage("Invalid locator format: " + locator);
		throw new IllegalArgumentException("Invalid locator format: " + locator);
	}

	/**
	 * Click through javascript
	 * 
	 * @param locator
	 */

	public static void executethroughScript(String locator) {
		CommonFunctions.insertLogMessage("clicking through Java script executor to the web-element: " + locator);
		By byLocator = elementLocator(locator);
		WebElement element = driver.findElement(byLocator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		CommonFunctions.insertLogMessage("clicked through Java script executor to the web-element: " + locator);
	}

	/**
	 * Click through javascript
	 * 
	 * @param locator
	 */

	public static void executethroughJavaScript(WebElement element) {
		CommonFunctions.insertLogMessage("clicking through Java script executor to the web-element");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		CommonFunctions.insertLogMessage("clicked through Java script executor to the web-element");
	}

	/**
	 *
	 * @param locator
	 * @param option
	 *            This method is used To select option from drop down
	 */

	public static void selectFromDropdown(String locator, String option) {
		CommonFunctions.insertLogMessage(
				"Attempting to select the option: '" + option + "' from dropdown having xpath: " + locator);
		By byLocator = elementLocator(locator);
		try {
			Select dropdown = new Select(driver.findElement(byLocator));
			dropdown.selectByVisibleText(option);
		} catch (InvalidSelectorException e) {
			CommonFunctions.insertLogMessage("Option:" + option + " is not visible for selection");
			e.printStackTrace();
		}
	}

	public static void click(By by) {
		try {

			WebElement input = driver.findElement(by);
			new Actions(driver).moveToElement(input).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void click(WebElement input) {
		try {
			new Actions(driver).moveToElement(input).click().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the text.
	 *
	 * @param by
	 *            the by
	 * @return the text
	 */
	public static String getText(By by) {
		String value = null;
		try {
			if (isElementPresent(by)) {
				value = driver.findElement(by).getText().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Checks if is element present.
	 *
	 * @param by
	 *            the by
	 * @return true, if is element present
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/*
	 * getText
	 * 
	 * 
	 */
	public static String getText(WebElement obj) {
		String text = null;
		try {
			if (isElementPresent(obj)) {
				text = obj.getText().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;

	}

	/*
	 * is element present
	 */
	public static boolean isElementPresent(WebElement obj) {
		boolean elementPresent;
		try {
			elementPresent = obj.isEnabled();
			return elementPresent;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Send keys.
	 *
	 * @param by
	 *            the by
	 * @param String
	 *            the keys
	 */
	public static void sendKeys(By by, String keys) {
		driver.findElement(by).sendKeys(keys);
	}

	/**
	 * Send keys.
	 *
	 * @param by
	 *            the by
	 * @param String
	 *            the keys
	 */
	public static void sendKeys(WebElement element, String keys) {
		element.sendKeys(keys);
	}

	/**
	 * Gets the text by attribute value.
	 *
	 * @param by
	 *            the by
	 * @return the text
	 */
	public static String getAttribute(By by, String attibute) {
		String value = null;
		try {
			if (isElementPresent(by)) {
				value = driver.findElement(by).getAttribute(attibute);
			} else {
				value = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * Gets the text by attribute value.
	 *
	 * @param by
	 *            the by
	 * @return the text
	 */
	public static String getAttribute(WebElement element, String attibute) {
		String value = null;
		try {
			if (isElementPresent(element)) {
				value = element.getAttribute(attibute);
			} else {
				value = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * This method is used to close browser
	 */
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
}
