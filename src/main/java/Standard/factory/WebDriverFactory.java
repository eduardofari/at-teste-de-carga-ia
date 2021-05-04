package Standard.factory;

import Standard.utils.others.InfraUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private static WebDriver driver;

    public static void startChromeDriver() {

        if (InfraUtils.getOsName().contains("Windows")) {
            System.out.println("Chrome Driver");
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "/var/.chromedriver/chromedriver");
        }

        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--enable-popup-blocking");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-default-apps");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--javascriptEnabled");
        chromeOptions.addArguments("--applicationCacheEnabled");
        chromeOptions.addArguments("--lang=pt-BR");
        chromeOptions.addArguments("--incognito");
        if (!InfraUtils.getOsName().contains("Windows")) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--window-size=1366,764");
        }

        driver = new ChromeDriver(chromeOptions);

        // Correção_Thiago_Moraes:
        driver.manage().deleteAllCookies();
    }

    public static WebDriver getCurrentRunningDriver() {
        return driver;
    }

    public static void killCurrentRunningDriver() {
        driver.quit();
    }

}
