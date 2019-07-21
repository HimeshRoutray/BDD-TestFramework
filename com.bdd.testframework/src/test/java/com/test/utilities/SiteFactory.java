package com.test.utilities;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import com.test.actions.CalculatorAction;
import com.test.pages.CalculatorPage;
import com.test.utilities.TestDataLoader;

public class SiteFactory {
	private static volatile SiteFactory factory;
	private WebDriver driver;

	private SiteFactory() {
	}

	public static SiteFactory getInstance() {
		if (factory == null) {
			synchronized (SiteFactory.class) {
				if (factory == null)
					factory = new SiteFactory();
			}
		}
		return factory;
	}

	public void getDriver(String driverType) {
		if (driverType != null) {
			if (driverType.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "BrowserDrivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("useAutomationExtension", false);
				driver = new ChromeDriver(options);
			} 
			else if (driverType.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "BrowserDrivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} 
			else if (driverType.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.ie.driver", "BrowserDrivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else {
				String browserT = "invalid";
				Assert.assertEquals(browserT, "invalid1", "Invalid BrowserType");

			}
		} else {
			Assert.fail(driverType + "is null");
		}
	}

	public CommonFunctions getCommonFunctions() {
		return new CommonFunctions(driver);
	}

	public TestDataLoader getTestDataLoader() {
		return new TestDataLoader();
	}

	public CalculatorAction CalculatorAction() {
		return new CalculatorAction(this);
	}

	public CalculatorPage CalculatorPage() {
		return new CalculatorPage(driver);
	}

}
