package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.test.utilities.CommonFunctions;
import com.test.utilities.PropertyReader;

public class CalculatorPage {
	
	public CalculatorPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//*[@id='ubermenu-section-link-kiwisaver-ps']")
	WebElement kiwiSaverSubMenu;

	@FindBy(how = How.XPATH, using = "//*[@id='ubermenu-item-cta-kiwisaver-calculators-ps']")
	WebElement kiwiSaverCalculatorsLink;

	@FindBy(how = How.XPATH, using = "//*[@label='Current age']//child::*[@class='icon']")
	WebElement clickHereToGetStartedButton;

	@FindBy(how = How.XPATH, using = "//*[text()='Click here to get started.']")
	WebElement currentAgeLabelInformationIcon;

	@FindBy(how = How.XPATH, using = "//*[@label='Current age']//child::*[@class='message-row ng-scope']")
	WebElement currentAgeLabelInformationIconMessage;

	@FindBy(how = How.XPATH, using = "//iframe[@src='/calculator-widgets/kiwisaver-calculator/?gclid=&referrer=https%3A%2F%2Fwww.westpac.co.nz%2F&parent=3956&host=calculator-embed']")
	WebElement iframeLocator;

	@FindBy(how = How.XPATH, using = "//label[text()='Current age']//following::input[1]")
	WebElement currentAgeTextBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Employment status']//following::*[text()='Select'][1]")
	WebElement employmentStatusDropDown;

	@FindBy(how = How.XPATH, using = "//label[text()='Salary or wages per year (before tax)']//following::input[1]")
	WebElement salaryPerYearBeforeTaxTextBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Prescribed investor rate (PIR)']//following::div[@class='control-well'][1]")
	WebElement pirDropDown;

	@FindBy(how = How.XPATH, using = "//label[text()='Current KiwiSaver balance']//following::input[1]")
	WebElement currentKiwiSaverBalanceTextBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Voluntary contributions']//following::input[1]")
	WebElement contributionAmountTextBox;

	@FindBy(how = How.XPATH, using = "//label[text()='Voluntary contributions']//following::*[text()='Frequency']")
	WebElement volContriFrequencyDropDown;

	@FindBy(how = How.XPATH, using = "//label[text()='Savings goal at retirement']//following::input[1]")
	WebElement savingaGoalAtRetirementTextBox;

	@FindBy(how = How.XPATH, using = "//button//child::*[text()='View your KiwiSaver retirement projections']")
	WebElement ViewRetirementProjectionsButon;

	@FindBy(how = How.XPATH, using = "//*[text()='At age 65, your KiwiSaver balance is estimated to be:']//following::span")
	WebElement calculationResult;

	public String getTitle() {
		return CommonFunctions.getTitle();
	}

	public void navigate_KiwiSaverRetirementCalculatorPage() {
		CommonFunctions.click(kiwiSaverSubMenu);
		CommonFunctions.click(clickHereToGetStartedButton);
	}

	public void switchToIframe() {
		CommonFunctions.switchToIframe(iframeLocator);
	}

	public void switchToDefaultContent() {
		CommonFunctions.switchToDefaultContent();
	}

	public void click_InformationIcon() {

		CommonFunctions.executethroughJavaScript(currentAgeLabelInformationIcon);
	}

	public String get_InformationIconMessage() {
		return CommonFunctions.getText(currentAgeLabelInformationIconMessage);
	}

	public void enter_CurrentAge(String age) {
		CommonFunctions.sendKeys(currentAgeTextBox, age);
	}

	public void enter_EmploymentStatus(String empStatus) {

		CommonFunctions.click(employmentStatusDropDown);
		CommonFunctions.click(CommonFunctions.webElmnt(
				PropertyReader.getGenericPropertyValue("employmentStatusDropDownValue").replace("{0}", empStatus)));

	}

	public void enter_SalaryPerYearBeforeTax(String salary) {

		CommonFunctions.click(salaryPerYearBeforeTaxTextBox);
		CommonFunctions.sendKeys(salaryPerYearBeforeTaxTextBox, salary);

	}

	public void enter_KiwiSaverMemberContribution(String kiwiMemberContribution) {

		CommonFunctions.click(CommonFunctions.webElmnt(PropertyReader
				.getGenericPropertyValue("kiwiSaverMemContriRadioButon").replace("{0}", kiwiMemberContribution)));
	}

	public void enter_PIR(String pir) {

		CommonFunctions.click(pirDropDown);
		CommonFunctions.click(CommonFunctions
				.webElmnt(PropertyReader.getGenericPropertyValue("pirDropDownValue").replace("{0}", pir)));
	}

	public void enter_CurrentKiwiSaverBalance(String balance) {

		CommonFunctions.click(currentKiwiSaverBalanceTextBox);
		CommonFunctions.sendKeys(currentKiwiSaverBalanceTextBox, balance);
	}

	public void enter_VoluntaryContributionsAmount(String amount) {

		CommonFunctions.click(contributionAmountTextBox);
		CommonFunctions.sendKeys(contributionAmountTextBox, amount);
	}

	public void enter_VoluntaryContributionsFrequency(String frequency) {

		CommonFunctions.click(volContriFrequencyDropDown);
		CommonFunctions.click(CommonFunctions.webElmnt(
				PropertyReader.getGenericPropertyValue("volContriFrequencyDropDownValue").replace("{0}", frequency)));
	}

	public void enter_RiskProfile(String risk) {

		CommonFunctions.click(CommonFunctions
				.webElmnt(PropertyReader.getGenericPropertyValue("riskProfileRadioButton").replace("{0}", risk)));
	}

	public void enter_SavingaGoalAtRetirement(String goal) {
		CommonFunctions.click(savingaGoalAtRetirementTextBox);
		CommonFunctions.sendKeys(savingaGoalAtRetirementTextBox, goal);
	}

	public void click_ViewYourKiwiSaverRetirementProjectionsButton() {
		CommonFunctions.executethroughJavaScript(ViewRetirementProjectionsButon);
	}

	public String getCalculationResult() {
		String text = CommonFunctions.getAttribute(calculationResult, "innerText");
		String lines[] = text.split("\n");
		return lines[1];
	}

}
