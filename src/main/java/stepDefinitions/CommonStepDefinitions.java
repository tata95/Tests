package stepDefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import lib.Init;
import pages.InsuranceTravelPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static lib.Init.getDriver;
import static lib.Init.setStash;

/**
 * Created by Татьяна on 18.05.2016.
 */
public class CommonStepDefinitions {
    InsuranceTravelPage insuranceTravelPage;
    public  CommonStepDefinitions() throws IOException {

    }

    @Before
    public static void BeforeTest() throws IOException {
        System.out.println("tesdt1");
        Properties property = new Properties();
        property.load(new FileInputStream("src/test/java/config/application.properties"));

        setStash("browser", property.getProperty("db.browser"));
        setStash("url", property.get("db.url"));
        setStash("TotalCost", property.get("db.TotalCost"));
        setStash("TotalCostSufficient", property.get("db.TotalCostSufficient"));
        setStash("TotalCostSport", property.get("db.TotalCostSport"));
        setStash("SportSumma", property.get("db.SportSumma"));
        setStash("TotalCostProvident", property.get("db.TotalCostProvident"));
        setStash("TotalCostFinish", property.get("db.TotalCostFinish"));

    }

    /**
     * Открыть страницу "Страхование путешественников"
     * @throws IOException
     */
    @Given("Открыть страницу страхование путешественников")
    public  void openInsuranceTravel() throws IOException {
        getDriver().get(Init.getStash().get("url").toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        insuranceTravelPage = new InsuranceTravelPage();
    }

    @Given("Проверить значения по умолчанию")
    public void checkDefaultValues() throws IOException {
        insuranceTravelPage.checkDefaultValues();
    }

    public void checkAvailabilityTabs() throws IOException {
        insuranceTravelPage.checkAvailabilityTabs();
    }

    public void checkTotalCost() throws IOException, InterruptedException {
        insuranceTravelPage.checkTotalCost();
    }

    public void selectSufficient() throws IOException {
        insuranceTravelPage.selectSufficient();
    }

    public void checkTotalCostAfterSufficient() throws IOException, InterruptedException {
        insuranceTravelPage.checkTotalCostAfterSufficient();
    }

    public void selectSport() throws IOException, InterruptedException {
        insuranceTravelPage.selectSport();
    }

    public void checkValueSport() throws IOException {
        insuranceTravelPage.checkValueSport();
    }

    public void selectProvident() throws IOException, InterruptedException {
        insuranceTravelPage.selectProvident();
    }
    public void selectProtectionLuggage() throws IOException, InterruptedException {
        insuranceTravelPage.selectProtectionLuggage();
    }


}
