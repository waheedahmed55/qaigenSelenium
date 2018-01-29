package com.framework.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

	private String storagePath;

	//Constructor
	public Driver() {
		
		//Get Root Path
		String workingPath = System.getProperty("user.dir");
		storagePath = workingPath;
	}

	public WebDriver getWebDriver(String browser) throws MalformedURLException {
		String osName = System.getProperty("os.name");
        String webDriverType = browser;

		System.out.println("Executing Tests on OS " + osName);

        if (webDriverType.equalsIgnoreCase("firefox") || webDriverType.isEmpty()){
			if(osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac")) {
				System.setProperty("webdriver.gecko.driver", storagePath + "/drivers/geckodriver");
			}
			else {
				System.setProperty("webdriver.gecko.driver", storagePath + "/drivers/geckodriver.exe");
			}
			return new FirefoxDriver();
        }
        else if (webDriverType.equalsIgnoreCase("chrome")){
			if(osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("mac")) {
				System.setProperty("webdriver.chrome.driver", storagePath + "/drivers/chromedriver");
				return new ChromeDriver();
			}
			else {
				System.setProperty("webdriver.chrome.driver", storagePath + "/drivers/chromedriver.exe");
				return new ChromeDriver();
			}
        }
        else if (webDriverType.equalsIgnoreCase("ie")){
            System.setProperty("webdriver.ie.driver", storagePath + "/drivers/IEDriverServer32.exe");
            return new InternetExplorerDriver();
        }
        else {
            System.out.println("Driver type " + webDriverType + " is invalid");
            return null;
        }
	}

	public WebDriver getRemoteWebDriver(String URL, DesiredCapabilities dc) throws MalformedURLException {
		return new RemoteWebDriver(new URL(URL),dc);
	}

	public Platform getPlatform(String platformName) {
		String osName = platformName.toUpperCase();

		if(osName.equals("WIN8.1"))
			return Platform.WIN8_1;
		else if (osName.equals("WIN8"))
			return Platform.WIN8;
		else if (osName.equals("LINUX"))
			return Platform.LINUX;
		else if (osName.equals("MAC"))
			return Platform.MAC;
		else if (osName.equals("WIN"))
			return Platform.WINDOWS;
		else
			return Platform.ANY;
	}
}
