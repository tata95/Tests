package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

/**
 * Created by Татьяна on 20.05.2016.
 */
public class InsuranceTravelPage extends AnyPage {
    public static final String XPATH_BOX_TITLE_BEGIN = "//div[contains(@class, 'box-title') and contains(text(), ";
    public static final String XPATH_BOX_RECOMMENDED_BEGIN
            = "//span[contains(@class, 'b-form-box-title ng-binding') and contains(text(), ";
    public static final String XPATH_BOX_TITLE_END = ")]/..";

    @FindBy(name = "startDate")
    private WebElement elementStartDate;

    @FindBy(xpath = "//h2[text()='Страхование путешественников']")
    private WebElement initElement;

    @FindBy(css = ".ng-binding.ng-scope.b-dropdown-title")
    WebElement elementContry;

    @FindBy(name = "finishDate")
    WebElement elementFinishDate;

    @FindBy(name = "duration")
    WebElement elementDuration;

    @FindBy(name = "insuredCount60")
    WebElement elementCount60;

    @FindBy(name = "insuredCount2")
    WebElement elementCount2;

    @FindBy(name = "insuredCount70")
    WebElement elementCount70;

    @FindBy(xpath = XPATH_BOX_TITLE_BEGIN + "'Минимальная'" + XPATH_BOX_TITLE_END)
    WebElement elementMinimal;

    @FindBy(xpath = XPATH_BOX_TITLE_BEGIN + "'Достаточная'" + XPATH_BOX_TITLE_END)
    WebElement elementSufficient;

    @FindBy(xpath = XPATH_BOX_TITLE_BEGIN + "'Максимальная'" + XPATH_BOX_TITLE_END)
    WebElement elementMaxsimum;

    @FindBy(xpath = XPATH_BOX_RECOMMENDED_BEGIN  + "'Спортивный'" + XPATH_BOX_TITLE_END)
    WebElement elementSport;

    @FindBy(xpath = XPATH_BOX_RECOMMENDED_BEGIN  + "'Предусмотрительный'" + XPATH_BOX_TITLE_END)
    WebElement elementProvident;

    @FindBy(xpath = XPATH_BOX_RECOMMENDED_BEGIN  + "'Особый случай'" + XPATH_BOX_TITLE_END)
    WebElement elementSpecialCase;

    @FindBy(xpath = XPATH_BOX_RECOMMENDED_BEGIN  + "'Защита багажа'" + XPATH_BOX_TITLE_END)
    WebElement elementProtectionLuggage;

    @FindBy(xpath = XPATH_BOX_RECOMMENDED_BEGIN  + "'Личный адвокат'" + XPATH_BOX_TITLE_END)
    WebElement elementPersonalLawyer;

    @FindBy(xpath = "//span[contains(@class, 'img-active-2')]")
    WebElement elementRegistration;

    @FindBy(xpath = "//span[contains(@class, 'img-active-3')]")
    WebElement elementConfirmation;

    @FindBy(xpath = "//dt[contains(@class, 'form-ins-prem') and contains(text(), 'Итоговая стоимость')]/..//dd[1]//span[1]")
    WebElement elementTotalCost;

    @FindBy(xpath = "//li[contains(text(), 'Активные виды спорта')]")
    WebElement elementParamSport1;

    @FindBy(xpath = "//li[contains(text(), 'Защита спортинвентаря')]")
    WebElement elementParamSport2;

    @FindBy(xpath = "//li[contains(text(), 'Ski-pass / Лавина')]")
    WebElement elementParamSport3;

    @FindBy(xpath = "//span[contains(text(), 'Спортивный')]/..//span[4]")
    WebElement elementSportSumma;


    /**
     * Коснтруктор для класса InsuranceTravelPage
     * @throws IOException
     */
    public InsuranceTravelPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//h2[text()='Страхование путешественников']")));
    }

    /**
     * Проверяет значения по умолчанию
     * @throws IOException
     */
    public  void checkDefaultValues() throws IOException {
        Assert.assertEquals("Весь мир, кроме США и РФ", elementContry.getAttribute("innerHTML"));

        Assert.assertTrue("Проверка на начало даты не пройдена", elementStartDate.getText().isEmpty());

        Assert.assertTrue("Проверка на окончании даты не пройдена", elementFinishDate.getText().isEmpty());

        Assert.assertEquals("15", elementDuration.getAttribute("value"));

        Assert.assertEquals( "1", elementCount60.getAttribute("value"));

        Assert.assertEquals("0", elementCount2.getAttribute("value"));

        Assert.assertEquals("0", elementCount70.getAttribute("value"));

        click(elementMinimal);
        Assert.assertEquals("rgba(255, 167, 21, 1)",elementMinimal.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementSufficient.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementMaxsimum.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementSport.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementProvident.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementSpecialCase.getCssValue("border-top-color"));

        click(elementProtectionLuggage);
        Assert.assertEquals("rgba(235, 237, 236, 1)", elementProtectionLuggage.getCssValue("border-top-color"));

        Assert.assertEquals("rgba(235, 237, 236, 1)", elementPersonalLawyer.getCssValue("border-top-color"));
    }

    /**
     * Проверка доступности вкладок "Оформление", "Подтверждение"
     * @throws IOException
     */
    public void checkAvailabilityTabs () throws IOException {
        Assert.assertFalse(elementRegistration.isDisplayed()&&  elementRegistration.isEnabled());
        Assert.assertFalse(elementConfirmation.isDisplayed() && elementConfirmation.isEnabled());
    }

    /**
     * Проверка значения "Итоговая стоимость"
     * @throws IOException
     * @throws InterruptedException
     */
    public void checkTotalCost () throws IOException, InterruptedException {
        Thread.sleep(6000);
        Assert.assertTrue(elementTotalCost.getText().contains(Init.getStash().get("TotalCost").toString()));
    }

    /**
     * Выбор значения "Достаточная" в блоке "Выберите сумму страховой защиты"
     * @throws IOException
     */
    public void selectSufficient () throws IOException {
        click(elementSufficient);
        Assert.assertEquals("rgba(255, 167, 21, 1)", elementSufficient.getCssValue("border-top-color"));
    }

    /**
     * Проверка значения "Итоговая стоимость" после выбора значения "Достаточная"
     * @throws IOException
     * @throws InterruptedException
     */
    public void checkTotalCostAfterSufficient () throws IOException, InterruptedException {
        Thread.sleep(6000);
        Assert.assertTrue(elementTotalCost.getText().contains(Init.getStash().get("TotalCostSufficient").toString()));
    }

    /**
     * Выбор значения "Спортивный" в секции "Рекомендуем предусмотреть"
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectSport () throws IOException, InterruptedException {
        click(elementSport);
        Assert.assertEquals("rgba(255, 167, 21, 1)", elementSport.getCssValue("border-top-color"));
        Thread.sleep(6000);
        Assert.assertTrue(elementTotalCost.getText().contains(Init.getStash().get("TotalCostSport").toString()));
    }

    /**
     * Проверка текста значения "Спортивный" в блоке "Рекомендуем предусмотреть"
     * @throws IOException
     */
    public void checkValueSport () throws IOException {
        Assert.assertEquals("Активные виды спорта", elementParamSport1.getText());
        Assert.assertEquals("Защита спортинвентаря", elementParamSport2.getText());
        Assert.assertEquals("Ski-pass / Лавина", elementParamSport3.getText());
        Assert.assertTrue(elementSportSumma.getText().contains(Init.getStash().get("SportSumma").toString()));
    }

    /**
     * Выбор значения "Предусмотрительный" в секции "Рекомендуем предусмотреть" и проверка значения "Итоговая стоимость"
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectProvident () throws IOException, InterruptedException {
        click(elementProvident);
        Assert.assertEquals("rgba(255, 167, 21, 1)", elementProvident.getCssValue("border-top-color"));
        Thread.sleep(6000);
        Assert.assertTrue(elementTotalCost.getText().contains(Init.getStash().get("TotalCostProvident").toString()));
    }

    /**
     * В секции "Рекомендуем предусмотреть" выбор значения "Защита багажа", отлючение значения "Спортивный",
     * проверка значения "Итоговая стоимость"
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectProtectionLuggage () throws IOException, InterruptedException {
        click(elementProtectionLuggage);
        Assert.assertEquals("rgba(255, 167, 21, 1)", elementProtectionLuggage.getCssValue("border-top-color"));
        click(elementSport);
        Assert.assertEquals("rgba(235, 237, 236, 1)", elementSport.getCssValue("border-top-color"));
        Thread.sleep(6000);
        Assert.assertTrue(elementTotalCost.getText().contains(Init.getStash().get("TotalCostFinish").toString()));
    }






}
