package com.framework.base;

import com.framework.core.Reporting;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected BasePage() {

    }

    protected  <T> T getNewInstanceOfClass(Class<T> clazz, WebDriver driver, Reporting reporter) {
        if(this.getClass().equals(clazz)) {
            return (T)this;
        }

        try {
            return (T) clazz.getDeclaredConstructors()[0].newInstance(driver, reporter);
        }
        catch(Exception e) {
            return null;
        }

    }
}
