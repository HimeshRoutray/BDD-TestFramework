package com.test.runner;

import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false,
		features = "classpath:featureFiles",
		glue = {"com.test.bindings"},
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		monochrome=true,
		tags={"@SampleTest"}
		)


public class TestRunner {

}
