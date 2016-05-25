package stepDefinitions;

import lib.Init;

import java.io.IOException;

import static lib.Init.getDriver;

/**
 * Created by Татьяна on 23.05.2016.
 */
public class CommonStepDefinitionsTest3 {
    public CommonStepDefinitionsTest3() throws IOException {

    }

    /**
     * Открыть страницу "Конвертер валют"
     * @throws IOException
     */

    public  void openBranchesAndATMs() throws IOException {
        getDriver().get(Init.getStash().get("urlTest3").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
