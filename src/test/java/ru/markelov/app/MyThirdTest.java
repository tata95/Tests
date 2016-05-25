package ru.markelov.app;

import lib.Init;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.BranchesAndATMsPage;
import stepDefinitions.CommonStepDefinitionsTest3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class MyThirdTest {
    @BeforeClass
    public static void BeforeTest() throws IOException {
        System.out.println("test3");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("urlTest3", property.get("db.urlTest3"));

    }

    @Test
    public  void test() throws IOException, InterruptedException {

        CommonStepDefinitionsTest3 commonStepDefinitionsTest3 = new CommonStepDefinitionsTest3();
        commonStepDefinitionsTest3.openBranchesAndATMs();
        BranchesAndATMsPage branchesAndATMsPage = new BranchesAndATMsPage();
        branchesAndATMsPage.selectFilial();
        branchesAndATMsPage.countFilial();
        branchesAndATMsPage.distanceToFilial();
        branchesAndATMsPage.selectTerminal();
        branchesAndATMsPage.distanceToTerminal();
        branchesAndATMsPage.selectShowMore();
        branchesAndATMsPage.deselectFilial();
        branchesAndATMsPage.distanceLocationDeselectFilial();
    }

    @After
    public void after () throws IOException{
        getDriver().quit();
        Init.clearStash();
    }
}

