package com.tests.ui.web.hotwire;

import com.app.enums.hotwire.HotwireConfigKeys;
import com.app.enums.hotwire.HotwireDataKeys;
import com.app.pageobjects.web.hotwire.*;

import com.framework.Global;
import com.framework.base.BaseSeleniumWebTest;
import com.framework.handlers.DataHandler;
import com.framework.helpers.Generic;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Date;


public class TestsForSearch extends BaseSeleniumWebTest {

    private final String configFile = "hotwireconfig.json";
    private final String dataFile = "staticdata.json";
    DataHandler dHandler;

    //DataHandler
    @DataProvider(name = "getBrowsers", parallel = true)
    public static Object[][] getBrowsers() {
        return Global.getBrowsers();
    }

    @Factory(dataProvider = "getBrowsers")
    //Created with values from @DataHandler in @Factory
    public TestsForSearch(String browser) {
        this.browser = browser;
    }

    @BeforeClass
    public void beforeClass() throws IOException {
        setClassName(this);
        super.beforeClass();

        dHandler = new DataHandler(configFile, dataFile);
    }

    @BeforeMethod
    public void beforeMethod(Method method) throws MalformedURLException {
        super.beforeMethod(method);
        setWebDriver();
    }

    @Test
    public void testShouldDisplayResultsForBundleSearch() throws InterruptedException {

        String url = dHandler.getAppConfig(HotwireConfigKeys.URL).getAsString();

        SearchPage searchPage = new LaunchApplication(driver, Reporter)
            .launchHotwire(url);

        String fromCity = dHandler.getAppData(HotwireDataKeys.FROM_CITY).getAsString();
        String toCity = dHandler.getAppData(HotwireDataKeys.TO_CITY).getAsString();


        Date date = new Date();
        String departureDate = Generic.addDays(date, 1);
        String returnDate = Generic.addDays(date, 20);

        String packageName = "Flight + Hotel + Car";
        String startHour = "Evening";
        String endHour = "Morning";

        ResultsPage resultsPage = searchPage.selectPackage(packageName)
            .enterFlightDetails(fromCity, toCity,departureDate, startHour, returnDate, endHour)
            .findAVacation();


        int resultsCnt = resultsPage.waitForLoader()
            .getResultsCount();
        Thread.sleep(1000);

        //Assert
        Assert.assertTrue(resultsCnt > 0);
    }


    @AfterMethod
    public void afterMethod(Method method){
        super.afterMethod(method);
    }

    @AfterClass
    public void afterClass(){
        super.afterClass();
    }
}


