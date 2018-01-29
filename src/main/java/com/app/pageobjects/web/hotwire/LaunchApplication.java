package com.app.pageobjects.web.hotwire;

import com.framework.core.Reporting;
import org.openqa.selenium.WebDriver;

public class LaunchApplication {
	
	private Reporting reporter;
	private WebDriver driver;
	
	public LaunchApplication(WebDriver GDriver,  Reporting GReporter){
		reporter = GReporter;
		driver = GDriver;
	}	
	
	public SearchPage launchHotwire(String url){
		driver.get(url);
		reporter.writeToTestLevelReport("Navigate to specified URL", "URL: " , "Navigated to URL: " , "Done");
		return new SearchPage(driver, reporter);
	}
}
