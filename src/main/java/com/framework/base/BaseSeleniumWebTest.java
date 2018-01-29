package com.framework.base;

import com.framework.core.Wrappers;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class BaseSeleniumWebTest extends BaseTest {

    public void afterMethod(Method method){
        super.afterMethod(method);

        if(driver != null) {
            try{
                driver.quit();
                driver = null;
            }
            catch (Exception e){}
        }
    }

    protected void setWebDriver() throws MalformedURLException {
        if(driver==null){
            driver = execDriver.getWebDriver(browser);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Reporter.setDriver(driver);
            doAction = new Wrappers(driver,Reporter);
        }
    }

}
