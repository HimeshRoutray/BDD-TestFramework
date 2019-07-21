package com.test.actions;

import org.junit.Assert;
import com.test.utilities.SiteFactory;

public class CalculatorAction {

	private SiteFactory factory;

	public CalculatorAction(SiteFactory factory) {
		this.factory = factory;
	}

	public void navigate_KiwiSaverRetirementCalculatorPage() {

		factory.CalculatorPage().navigate_KiwiSaverRetirementCalculatorPage();
	}

	public void click_InformationIcon() {

		factory.CalculatorPage().click_InformationIcon();
	}

	public void get_InformationIconMessage(String JsonData) {
		String informationExpected = factory.getTestDataLoader().getData(JsonData).get("CurrentAgeInformation")
				.toString();
		String informationActual = factory.CalculatorPage().get_InformationIconMessage();
		Assert.assertEquals(informationExpected, informationActual);
	}

	public void enter_CurrentAge(String ageDetails) {
		String age = factory.getTestDataLoader().getData(ageDetails).get("CurrentAge").toString();
		factory.CalculatorPage().enter_CurrentAge(age);
	}

	public void enter_EmploymentStatus(String empploymentDetails) {
		String empStatus = factory.getTestDataLoader().getData(empploymentDetails).get("EmploymentStatus").toString();
		if (empStatus.equals("skip")) {
			return;
		}
		factory.CalculatorPage().enter_EmploymentStatus(empStatus);

	}

	public void enter_SalaryPerYearBeforeTax(String salaryDetailsData) {
		String salary = factory.getTestDataLoader().getData(salaryDetailsData).get("SalaryPerYearBeforeTax").toString();
		if (salary.equals("skip"))
			return;
		factory.CalculatorPage().enter_SalaryPerYearBeforeTax(salary);
	}

	public void enter_KiwiSaverMemberContribution(String kiwiSaverMemberContriDetails) {
		String memberContribution = factory.getTestDataLoader().getData(kiwiSaverMemberContriDetails)
				.get("KiwiSaverMemberContribution").toString();
		if (memberContribution.equals("skip"))
			return;
		factory.CalculatorPage().enter_KiwiSaverMemberContribution(memberContribution);
	}

	public void enter_PIR(String pirDetails) {
		String pir = factory.getTestDataLoader().getData(pirDetails).get("PIR").toString();
		if (pir.equals("skip"))
			return;
		factory.CalculatorPage().enter_PIR(pir);
	}

	public void enter_CurrentKiwiSaverBalance(String currentBalanceDetails) {
		String currentBalance = factory.getTestDataLoader().getData(currentBalanceDetails)
				.get("CurrentKiwiSaverBalance").toString();
		if (currentBalance.equals("skip"))
			return;
		factory.CalculatorPage().enter_CurrentKiwiSaverBalance(currentBalance);
	}

	public void enter_VoluntaryContributionAmount(String voluntaryContributionsDetails) {
		String amount = factory.getTestDataLoader().getData(voluntaryContributionsDetails).get("VoluntaryContributions")
				.toString();
		if (amount.equals("skip"))
			return;
		factory.CalculatorPage().enter_VoluntaryContributionsAmount(amount);
	}

	public void enter_VoluntaryContributionsFrequency(String frequencyDetails) {
		String frequency = factory.getTestDataLoader().getData(frequencyDetails).get("VoluntaryContributionsFrequency")
				.toString();
		if (frequency.equals("skip"))
			return;
		factory.CalculatorPage().enter_VoluntaryContributionsFrequency(frequency);

	}

	public void enter_RiskProfile(String riskDetails) {
		String risk = factory.getTestDataLoader().getData(riskDetails).get("RiskProfile").toString();
		if (risk.equals("skip"))
			return;
		factory.CalculatorPage().enter_RiskProfile(risk);
	}

	public void enter_SavingaGoalAtRetirement(String savingsGoalDetails) {
		String goal = factory.getTestDataLoader().getData(savingsGoalDetails).get("SavingaGoalAtRetirement").toString();
		if (goal.equals("skip"))
			return;
		factory.CalculatorPage().enter_SavingaGoalAtRetirement(goal);
	}

	public void click_ViewYourKiwiSaverRetirementProjectionsButton() {

		factory.CalculatorPage().click_ViewYourKiwiSaverRetirementProjectionsButton();
	}

	public void validateCalculationResult(String JsonData) {
		String expectedResult = factory.getTestDataLoader().getData(JsonData).get("RetirementBalance").toString();
		String actualResult = factory.CalculatorPage().getCalculationResult();
		Assert.assertEquals(expectedResult, actualResult);
	}

}
