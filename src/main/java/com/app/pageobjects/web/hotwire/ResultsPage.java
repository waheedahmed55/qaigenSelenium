package com.app.pageobjects.web.hotwire;

import com.framework.base.BasePage;
import com.framework.core.Reporting;
import com.framework.core.Wrappers;
import org.openqa.selenium.WebDriver;

public class ResultsPage extends BasePage{

	private Reporting reporter;
	private WebDriver driver;
	private Wrappers wrapper;

	//PageUiObjects
	private final String imgLoader = "cssselector:=span.loader";
	private final String lblResults = "cssselector:=p.showing-results";

	public ResultsPage(WebDriver Driver, Reporting Reporter){
		reporter = Reporter;
		driver = Driver;
		wrapper = new Wrappers(driver, reporter);
	}

	public ResultsPage waitForLoader() throws InterruptedException {
		boolean isLoaderVisible = wrapper.isElementDisplayed(imgLoader);
		while (isLoaderVisible) {
			try {
				isLoaderVisible =  wrapper.isElementDisplayed(imgLoader);
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		return this;
	}

	public int getResultsCount() {
		wrapper.click(lblResults);
		String label = wrapper.getElement(lblResults).getText();
		int cnt = Integer.parseInt(label.split(" ")[6]);
		reporter.writeToTestLevelReport("Get Results Count", "Results should be displayed", "Results count is " + cnt, "Pass");
		return cnt;

	}
}
