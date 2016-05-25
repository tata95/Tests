package pages;

import lib.Init;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;

/**
 * Created by Татьяна on 23.05.2016.
 */
public class BranchesAndATMsPage extends AnyPage {

    public static final String XPATH_NEAREST_LOCATION = "//div[contains(@id, 'branchList' )]" +
            "//span[contains(@class, 'filial') or contains(@class, 'itt')]";
    /**
     * Количество найденных ближайших банкоматов и отделений
     */
    int sizeList = 0;

    @FindBy(xpath = XPATH_NEAREST_LOCATION )
    List<WebElement> nearestLocation;

    @FindBy(xpath = "//div[contains(@id, 'branchList' )]//span[contains(@class, 'filial') or contains(@class, 'itt')]" +
            "/..//p[contains(text(), 'На расстоянии')]")
    List<WebElement> txtdistanceToLocation;

    @FindBy(xpath = "//label[contains(@for, 'map-filter-type-filial') and contains(text(), 'Отделения')]")
    WebElement lFilial;

    @FindBy(xpath = "//label[contains(@for, 'map-filter-type-terminal') and contains(text(), 'Платёжные устройства')]")
    WebElement lTerminal;

    @FindBy(xpath = "//div[contains(@id, 'branchList' )]//span[contains(text(), 'Показать еще')]/..")
    WebElement bShowMore;

    /**
     * Конструктор для класс BranchesAndATMsPage
     * @throws IOException
     */
    public BranchesAndATMsPage() throws IOException {
        new WebDriverWait(Init.getDriver(), 60)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath
                                ("//h1[contains(@class, 'header_widget') and contains(text(), 'Отделения и банкоматы')]")));
    }

    /**
     * Проверяет порядок найденных ближайших локаций
     * @param nearest - список найденных локаций
     * @param txtDistance - список значений расстояния
     */
    public void distanceToLocation(List<WebElement> nearest, List<WebElement> txtDistance) {
        Double prevElement = null;
        String prevElementMeasure = null;
        Double element = null;
        String elementMeasure = null;
        sizeList = nearest.size();
        if (sizeList == 0)System.out.println("Ближайших отделений не найдено");
        else {
            for (int i = 0; i < sizeList; i++) {
                System.out.println(Integer.toString(i));
                int length = txtDistance.get(i).getText().length();
                if (i>=1){
                    if (txtDistance.get(i-1).getText().substring(length-9, length-7).equals("км"))
                    {
                        prevElement = Double.parseDouble(txtDistance.get(i-1).getText().substring(14, length-10));
                        prevElementMeasure = "км";
                    }
                    else {
                        prevElement = Double.parseDouble(txtDistance.get(i-1).getText().substring(14, length-9));
                        prevElementMeasure = "м";
                    }
                    if (txtDistance.get(i).getText().substring(length-9, length-7).equals("км")) {
                        element = Double.parseDouble(txtDistance.get(i).getText().substring(14, length-10));
                        elementMeasure = "км";
                    }
                    else {
                        element = Double.parseDouble(txtDistance.get(i).getText().substring(14, length-9));
                        elementMeasure = "м";

                    }
                    if (elementMeasure.equals("км") && (prevElementMeasure.equals("м")))
                        Assert.assertTrue(element<prevElement);
                    if ((elementMeasure.equals("км") && (prevElementMeasure.equals("км"))) ||
                            (elementMeasure.equals("м") && (prevElementMeasure.equals("м"))))
                        Assert.assertTrue(element>=prevElement);
                    Assert.assertTrue("Единицы измерения расположены неверно",
                            elementMeasure.equals("км") && (prevElementMeasure.equals("км")));

                }
            }
        }
    }

    /**
     * Проверяет цвет значка Сбербанка
     * @param color - цвет
     */
    public void checkColor(String color) throws IOException {
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(XPATH_NEAREST_LOCATION )));
        sizeList = nearestLocation.size();
        if (sizeList == 0)
            System.out.println("Ближайших отделений не найдено");
        else {
            for (int i = 0; i < sizeList; i++) {
                Assert.assertTrue("", nearestLocation.get(i).getCssValue("background-image").contains(color));
            }
        }
    }

    /**
     * Выделить чекбокс "Отделения"
     * @throws IOException
     */
    public void selectFilial() throws IOException {
      //  Assert.assertTrue(cbFilial.isDisplayed());
       // click(lFilial);
       checkColor("green");

    }

    /**
     * Проверяет количество ближайших локаций
      */
    public void countFilial() throws IOException {
        Assert.assertTrue("Список ближайших локаций содержит меньше 2-х значений", sizeList > 1);
    }

    /**
     * Проверяет порядок найденных ближайших локаций, при выделение чекбокса "Отделения"
     */
    public void distanceToFilial() {
       distanceToLocation(nearestLocation, txtdistanceToLocation);
    }

    /**
     * Отметить чекбокс "Платёжные устройства"
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectTerminal() throws IOException, InterruptedException {
        click(lTerminal);
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(XPATH_NEAREST_LOCATION)));

        sizeList = nearestLocation.size();
        int greenElement = 0;
        int orangeElement = 0;
        if (sizeList == 0)System.out.println("Ближайших отделений не найдено");
        else {
            for (int i = 0; i < sizeList; i++) {
                if(nearestLocation.get(i).getCssValue("background-image").contains("green")) greenElement++;
                else if (nearestLocation.get(i).getCssValue("background-image").contains("orange"))
                    orangeElement++;
            }
        }
        System.out.printf("Green:" + Integer.toString(greenElement) + " Orange:" + Integer.toString(orangeElement));
        Assert.assertTrue("Оранжевых элементов меньше 1", orangeElement >= 1);
    }

    /**
     * Проверяет порядок расположения найденных ближайших локаций,
     * при выбранных значения "Отделения", "Платёжные устройства"
     */
    public void distanceToTerminal() {
        distanceToLocation(nearestLocation, txtdistanceToLocation);
    }

    /**
     * Осуществление нажатия на кнопку "Показать ещё", и проверка располодения найденных ближайших локаций
     * @throws IOException
     * @throws InterruptedException
     */
    public void selectShowMore() throws IOException, InterruptedException {
        Assert.assertTrue("Кнопка не отображена", bShowMore.isEnabled());
            click(bShowMore);
            new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(XPATH_NEAREST_LOCATION )));
            int sizeListAfter = nearestLocation.size();
            Assert.assertTrue("Список Ближайшие к нам не увеличился не увеличился", sizeListAfter > sizeList);
            distanceToLocation(nearestLocation, txtdistanceToLocation);
    }

    /**
     * Снять выделения с чекбокса "Отделения"
     * @throws IOException
     * @throws InterruptedException
     */
    public void deselectFilial() throws IOException, InterruptedException {
        click(lFilial);
        new WebDriverWait(Init.getDriver(), 30)
                .until(ExpectedConditions.presenceOfElementLocated
                        (By.xpath(XPATH_NEAREST_LOCATION )));
        checkColor("orange");
    }

    /**
     * Проверяет порядок расположения найденных ближайших локаций
     */
    public void distanceLocationDeselectFilial(){
        distanceToLocation(nearestLocation, txtdistanceToLocation);
    }


}
