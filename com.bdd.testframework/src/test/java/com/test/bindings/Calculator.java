package com.test.bindings;

import com.test.utilities.SiteFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Calculator {	
	
	private SiteFactory factory;
	
	public Calculator() {
		factory = SiteFactory.getInstance();
	}
	
	 @Given("^On home page, user navigates to KiwiSaver Retirement Calculator page$")
	 public void navigate_KiwiSaverRetirementCalculatorPage(){
		 factory.CalculatorAction().navigate_KiwiSaverRetirementCalculatorPage();
	 }
	 
	 @And("^On KiwiSaver Retirement Calculator page, user clicks on the information icon besides Current age input field$")
	 public void click_Information_Icon(){
		 factory.CalculatorPage().switchToIframe();
		 factory.CalculatorAction().click_InformationIcon();
		 
	 }
	 @Then("^Validate that appropriate message '(.*)' is displayed below the input field$")
	 public void validate_InformationIconMessage(String message){
		 factory.CalculatorAction().get_InformationIconMessage(message);

	 } 
	 @Then("^On KiwiSaver Retirement Calculator page, user enters values against all input fields '(.*)'$")
	 public void enterValues(String userDetailsData){
		 factory.CalculatorPage().switchToIframe();
		 factory.CalculatorAction().enter_CurrentAge(userDetailsData);
		 factory.CalculatorAction().enter_EmploymentStatus(userDetailsData);
		 factory.CalculatorAction().enter_SalaryPerYearBeforeTax(userDetailsData);
		 factory.CalculatorAction().enter_KiwiSaverMemberContribution(userDetailsData);
		 factory.CalculatorAction().enter_PIR(userDetailsData);
		 factory.CalculatorAction().enter_CurrentKiwiSaverBalance(userDetailsData);
		 factory.CalculatorAction().enter_VoluntaryContributionAmount(userDetailsData);
		 factory.CalculatorAction().enter_VoluntaryContributionsFrequency(userDetailsData);
		 factory.CalculatorAction().enter_RiskProfile(userDetailsData);
		 factory.CalculatorAction().enter_SavingaGoalAtRetirement(userDetailsData);
	 }
	 
	 @Given("^user clicks on View your KiwiSaver retirement projections button$")
	 public void click_ViewYourKiwiSaverRetirementProjectionsButton(){
		 factory.CalculatorAction().click_ViewYourKiwiSaverRetirementProjectionsButton();
	 }
	 @Given("^validate that kiwisaver balance '(.*)' is displayed$")
	 public void click_ViewYourKiwiSaverRetirementProjectionsButton(String resultData){
		 factory.CalculatorAction().validateCalculationResult(resultData);
	 }
}
