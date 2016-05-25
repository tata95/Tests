package lib;

import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Татьяна on 17.05.2016.
 */
public class Init {

    private static WebDriver driver;

    private static HashMap<Object, Object> stash = new HashMap<>();

    public static HashMap<Object, Object> getStash () {
        if (null == stash)
            stash = new HashMap<>();
        return stash;
    }

    public static void clearStash () {
        stash.clear();
    }

    public static void setStash(Object key, Object value) {
        stash.put(key, value);
    }

    public static WebDriver getDriver() throws IOException {
        if (null == driver) {
            createWebDriver();
        }
       return driver;
    }

    public static void setDriver(WebDriver driver) {
        Init.driver = driver;
    }

    public static void createWebDriver() throws IOException {
        String toResourse="src/test/resources/webdrivers/";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (getStash().get("browser").toString()) {
            case "Firefox":
              //  capabilities.setBrowserName("firefox");
                setDriver(new FirefoxDriver(capabilities));
                break;
            case "Chrome":
                File chromeDriver = new File(toResourse + "chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
                capabilities.setBrowserName("chrome");
                setDriver(new ChromeDriver(capabilities));
                break;
            case "IE":
                File IEDriver = new File(toResourse + "IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", IEDriver.getAbsolutePath());
                capabilities.setBrowserName("internet explorer");
                setDriver(new InternetExplorerDriver(capabilities));
                break;
        }
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

}
