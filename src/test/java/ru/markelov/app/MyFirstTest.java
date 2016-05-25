package ru.markelov.app;

import lib.Init;
import org.junit.After;
import org.junit.Test;
import stepDefinitions.CommonStepDefinitions;

import java.io.IOException;

import static lib.Init.getDriver;


/**
 * Created by student on 12.05.2016.
 */

public class MyFirstTest {



    @Test
    public void test() throws IOException, InterruptedException {

        CommonStepDefinitions commonStepDefinitions = new CommonStepDefinitions();
        commonStepDefinitions.openInsuranceTravel();
        commonStepDefinitions.checkDefaultValues();
        commonStepDefinitions.checkAvailabilityTabs();
        commonStepDefinitions.checkTotalCost();
        commonStepDefinitions.selectSufficient();
        commonStepDefinitions.checkTotalCostAfterSufficient();
        commonStepDefinitions.selectSport();
        commonStepDefinitions.checkValueSport();
        commonStepDefinitions.selectProvident();
        commonStepDefinitions.selectProtectionLuggage();


    }

    @After
    public void after() throws IOException {
        getDriver().quit();
        Init.clearStash();
    }


}
