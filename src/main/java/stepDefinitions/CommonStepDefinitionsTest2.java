package stepDefinitions;

import lib.Init;

import java.io.IOException;

import static lib.Init.getDriver;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class CommonStepDefinitionsTest2 {
    public CommonStepDefinitionsTest2() throws IOException {

    }

    /**
     * Открыть страницу "Отделения и банкоматы"
     * @throws IOException
     */

    public  void openCurrencyConversion() throws IOException {
        getDriver().get(Init.getStash().get("urlTest2").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
