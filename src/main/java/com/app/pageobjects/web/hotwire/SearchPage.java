package com.app.pageobjects.web.hotwire;

import com.framework.base.BasePage;
import com.framework.core.Reporting;
import com.framework.core.Wrappers;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {

	private Reporting reporter;
	private WebDriver driver;
	private Wrappers wrapper;

	//PageUiObjects
	private final String lnkVacation = "linktext:=Vacations";
	private final String edtFlyFrom = "id:=origin";
	private final String edtFlyTo = "name:=destination";
	private final String edtStartDate = "cssselector:=input.startDate";
	private final String edtEndDate = "cssselector:=input.endDate";
	private final String lstStartHour = "id:=startHour";
	private final String lstEndHour = "id:=endHour";
	private final String btnFindAVacation = "xpath:=//button/span[text()='Find a vacation']";


	public SearchPage(WebDriver Driver, Reporting Reporter){
		reporter = Reporter;
		driver = Driver;
		wrapper = new Wrappers(driver, reporter);
	}


	public SearchPage selectPackage(String packageName) {
		wrapper.click(lnkVacation);
		wrapper.click("xpath:=//label[text()='" + packageName + "']");
		return this;
	}

	public SearchPage enterFlightDetails(String fromCityCode, String toCityCode, String startDate, String startHour, String endDate, String endHour) {
		String xpathForCity = "xpath:=//a/strong[text()='%s']";

		wrapper.enterText(edtFlyFrom, fromCityCode);
		wrapper.click(String.format(xpathForCity, fromCityCode));
		wrapper.enterText(edtFlyTo, toCityCode);
		wrapper.click(String.format(xpathForCity, toCityCode));
		wrapper.enterText(edtStartDate, startDate);
		wrapper.selectOptionFromList(lstStartHour, startHour);
		wrapper.enterText(edtEndDate, endDate);
		wrapper.selectOptionFromList(lstEndHour, endHour);

		reporter.writeToTestLevelReport("Enter required details", "All fields should be filled", "All fields filled successfully", "Pass");
		return this;
	}

	public ResultsPage findAVacation() {
		wrapper.click(btnFindAVacation);
		return new ResultsPage(driver, reporter);
	}

}
