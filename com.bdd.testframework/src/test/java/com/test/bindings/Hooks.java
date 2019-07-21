package com.test.bindings;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.test.utilities.CommonFunctions;
import com.test.utilities.PropertyReader;
import com.test.utilities.SiteFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	SiteFactory factory;

	@Before
    public void beforeScenario() {
		 factory = SiteFactory.getInstance();
		 factory.getDriver(PropertyReader.getConfigPropertyValue("browser"));
         factory.getCommonFunctions().openPage(PropertyReader.getConfigPropertyValue("url"));		 
    } 
 
	@After
    public void afterScenario(Scenario scenario){
		Reporter.loadXMLConfig(System.getProperty("user.dir") + new File(PropertyReader.getConfigPropertyValue("reportConfigPath")));
		Reporter.assignAuthor("Himesh Routra");
		Reporter.setSystemInfo("Application Name", "Westpac");
	    Reporter.setSystemInfo("Machine", "Windows 10 " + "64 Bit");
		Reporter.setSystemInfo("Browser", PropertyReader.getConfigPropertyValue("browser"));
		if (scenario.isFailed()) {
			 String screenshotName = scenario.getName().replaceAll(" ", "_");
			 try {
			 File sourcePath = ((TakesScreenshot) CommonFunctions.driver).getScreenshotAs(OutputType.FILE);
			 File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
			 Files.copy(sourcePath, destinationPath);   
			 Reporter.addScreenCaptureFromPath(destinationPath.toString());
			 } catch (IOException e) {
				 e.printStackTrace();
			 } 
		}
        factory.getCommonFunctions().closeDriver();
    }
}
