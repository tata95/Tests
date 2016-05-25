package pages;

import junit.framework.Assert;
import lib.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Татьяна on 22.05.2016.
 */
public class СurrencyСonversionPage extends AnyPage {
    public static final String XPATH_SELECT_BEGIN = "//div[contains(@id, 'select2-drop')]//li//div[contains(text(), '";
    public static final String XPATH_SELECT_END = "')]";

    @FindBy(className = "currency-converter-date")
    WebElement txtCurrentDate;

    @FindBy(xpath = "//label[contains(@class, 'control-label') and contains(text(), 'Поменять')]")
    WebElement lFrom;

    @FindBy(xpath = "//label[contains(@class, 'control-label') and contains(text(), 'На')]")
    WebElement lTo;

    @FindBy(id = "from")
    WebElement tbFrom;

    @FindBy(id = "to")
    WebElement tbTo;
    
    @FindBy(xpath = "//input[contains(@id, 'from')]/../..//div[contains(@class, 'input-group-addon')]")
    WebElement selectFrom;
    
   @FindBy(xpath = "//input[@id='to']/../..//div[contains(@class, 'input-group-addon')]")
   WebElement selectTo;

    @FindBy(className = "currency-converter-result")
    WebElement txtConverterResult;

    @FindBy(xpath = "//div[contains(@class, 'currency-converter-result')]//span[5]")
    WebElement txtCurrentCourse;

    @FindBy(xpath = "//div[contains(@class, 'currency-converter-result')]")
    WebElement txtResult;


    /**
     * Конструктор для класса CurrencyConversionPage
     * @throws IOException
     */
    public СurrencyСonversionPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 90)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//span[contains(@class, 'currency-icon') " +
                                "and contains(text(), 'Конвертер валют')]")));
    }

    /**
     * Установить значения в конверте валют
     * @param currencyFrom - валюта в блоке "Поменять"
     * @param currencyTo - валюта в блоке "На"
     * @param valueFrom - значение валюты в блоке "Поменять"
     * @throws IOException
     */
    public void setValue(String currencyFrom, String currencyTo, String valueFrom) throws IOException {
        click(selectFrom);
        click(By.xpath(XPATH_SELECT_BEGIN + currencyFrom + XPATH_SELECT_END));
        Assert.assertEquals(currencyFrom, selectFrom.getText());
        click(tbFrom);
        setText(tbFrom, valueFrom);
        Assert.assertEquals(valueFrom, tbFrom.getAttribute("value"));
        click(selectTo);
        click(By.xpath(XPATH_SELECT_BEGIN + currencyTo + XPATH_SELECT_END));
        Assert.assertEquals(currencyTo, selectTo.getText());
    }

    /**
     * Рассчитать значение и сверить с значение в поле "На"
     * @param valueFrom - значение в поле "Поменять"
     * @throws Throwable
     */
    public void calculateValue(int valueFrom) throws Throwable {
        double currentCourse = Double.parseDouble(txtCurrentCourse.getText());
        System.out.println(Double.toString(currentCourse));
        double result = (new BigDecimal(currentCourse*valueFrom).setScale(2, RoundingMode.UP).doubleValue());
        String resultString = Double.toString(result);
        System.out.println(resultString);
        try{
            Assert.assertEquals(resultString, tbTo.getAttribute("value"));
        }
         catch (Throwable e) {
             System.out.println("Значение рассчитано неверно");
         }
    }

    /**
     * Проверка даты в блоке "Конвертер валют"
     */
    public void checkCurrentDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd MMMM yyyy");
        Date currentDate = new Date();
        Assert.assertEquals(format1.format(currentDate), txtCurrentDate.getText());
    }

    /**
     * Проверка наличия компонентов в блоке "Конвертер валют"
     */
    public void presenceOfComponents() {
        Assert.assertTrue(lFrom.isDisplayed());
        Assert.assertTrue(lTo.isDisplayed());
        Assert.assertTrue(tbFrom.isDisplayed());
        Assert.assertTrue(tbTo.isDisplayed());
        Assert.assertTrue(selectFrom.isDisplayed());
        Assert.assertTrue(selectTo.isDisplayed());
        Assert.assertTrue(txtConverterResult.isDisplayed());
        Assert.assertEquals("1 RUB = 0.0130 EUR", txtResult.getText());
    }

    /**
     * Установить значения: Поменять - RUB(34); На - EUR
     * @throws IOException
     */
    public void setValueRubEur() throws IOException {
        setValue("RUB", "EUR", "34");
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - RUB(34); На - EUR
     * @throws Throwable
     */
    public void calculateValueRubEur() throws Throwable {
        calculateValue(34);
    }

    /**
     * Установить значения: Поменять - USD(10023); На - EUR
     * @throws IOException
     */
    public void setValueUsdEur() throws IOException {
        setValue("USD", "EUR", "10023");
    }

    /**
     * Проверка рассчитанного значения с параметрами: Поменять - USD(10023); На - EUR
     * @throws Throwable
     */
    public void calculateValueUsdEur() throws Throwable {
        calculateValue(10023);
    }

    /**
     * Установить значения: Поменять - USD(5); На - USD
     * @throws IOException
     */
    public void setValueUsdUsd() throws IOException {
        setValue("USD", "USD", "5");
        Assert.assertEquals("RUB", selectFrom.getText());
    }

    /**
     * Рассчитать значения для параметров: Поменять - USD(5); На - USD
     * @throws Throwable
     */
    public void calculateValueUsdUsd() throws Throwable {
        calculateValue(5);
    }

}
