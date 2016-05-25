package ru.markelov.app;

import lib.Init;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.СurrencyСonversionPage;
import stepDefinitions.CommonStepDefinitionsTest2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class MySecondTest {
    @BeforeClass
    public static void BeforeTest() throws IOException {
        System.out.println("test2");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("urlTest2", property.get("db.urlTest2"));

    }

    @Test
    public  void test() throws Throwable {

        CommonStepDefinitionsTest2 commonStepDefinitionsTest2 = new CommonStepDefinitionsTest2();
        commonStepDefinitionsTest2.openCurrencyConversion();
        СurrencyСonversionPage currencyConversionPage = new СurrencyСonversionPage();
        currencyConversionPage.checkCurrentDate();
        currencyConversionPage.presenceOfComponents();
        currencyConversionPage.setValueRubEur();
        currencyConversionPage.calculateValueRubEur();
        currencyConversionPage.setValueUsdEur();
        currencyConversionPage.calculateValueUsdEur();
        currencyConversionPage.setValueUsdUsd();
        currencyConversionPage.calculateValueUsdUsd();
    }

    @After
    public void after () throws IOException{
        getDriver().quit();
        Init.clearStash();
    }
}

