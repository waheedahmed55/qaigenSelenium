package com.framework;

public class Global {

	//Define all public static variables
	public static boolean flgJenkinsHtml = false;
	public static int g_iTestSuiteNo;

	public static Object[][] getBrowsers(){
		String[] browser = System.getProperty("browserName").split(",");
		final int size = browser.length;
		Object[][] browsers = new Object[size][1];
		for(int i=0;i<browser.length;i++) {
			System.out.println(browser[i]);
			browsers[i][0] = browser[i];
		}
		return browsers;
	}
}
