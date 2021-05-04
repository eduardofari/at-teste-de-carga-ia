package Standard.utils.others;

import Standard.factory.WebDriverFactory;
import Standard.utils.exceptions.ExceptionUtils;
import Standard.utils.readers.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SeleniumUtils {
    private WebDriverWait wait;

    public WebDriver driver;

    static Integer timeToBrooke = Integer.parseInt(Config.getProperty("time.to.brooke"));
    WebElement webElement;

    public static WebElement getWebElement(String xpath) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(xpath));
    }

    public static WebElement getWebElementXpath(String el) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.xpath(el));
    }

    public static WebElement getWebElementId(String el) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.id(el));
    }

    public static WebElement getWebElementName(String el) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.name(el));
    }

    public static WebElement getWebElementClassName(String el) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.className(el));
    }

    public static WebElement getWebElementLinkText(String el) {
        return WebDriverFactory.getCurrentRunningDriver().findElement(By.linkText(el));
    }


    public static List<WebElement> getWebElements(String xpath) {
        return WebDriverFactory.getCurrentRunningDriver().findElements(By.xpath(xpath));
    }

    public static Select getSelect(String xpath) {
        return new Select(getWebElement(xpath));
    }

    public static void waitWebElementVisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void waitWebElementInvisible(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void waitWebElementClickable(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);

        }
    }

    public static boolean elementClickable(String xpath) {
        boolean status = false;
        WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            status = true;
        } catch (Exception exception) {
            //silent
            //   ExceptionUtils.throwException(exception);
        }
        return status;

    }

    public static void waitWebElementSelectable(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void waitAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void sendText(String texto, String xpath) {
        waitWebElementVisible(xpath);
        getWebElement(xpath).clear();
        getWebElement(xpath).sendKeys(texto);
    }

    public static void click(String xpath) {
        waitWebElementClickable(xpath);
        getWebElement(xpath).click();
    }

    public static void selectCombo(String value, String xpath) {
        waitWebElementSelectable(xpath);
        getSelect(xpath).selectByValue(value);
    }

    public static void switchJanela() {
        for (String handle : WebDriverFactory.getCurrentRunningDriver().getWindowHandles()) {
            WebDriverFactory.getCurrentRunningDriver().switchTo().window(handle);
        }
    }

    public static void switchToDefault() {
        WebDriverFactory.getCurrentRunningDriver().switchTo().defaultContent();
    }

    public static WebDriver switchToFrame(String xpath) {
        return WebDriverFactory.getCurrentRunningDriver().switchTo().frame(getWebElement(xpath));
    }

    public static void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static String getTextoAlert() {
        wait(2000);
        waitAlertPresent();
        return WebDriverFactory.getCurrentRunningDriver().switchTo().alert().getText();
    }

    public static void alertAccept() {
        WebDriverFactory.getCurrentRunningDriver().switchTo().alert().accept();
    }

    public static boolean isAlertPresent() {
        boolean status = false;
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverFactory.getCurrentRunningDriver(), timeToBrooke);
            wait.until(ExpectedConditions.alertIsPresent());
            status = true;
        } catch (Exception exception) {
         //Silent
        }
        return status;
    }
    public static void waitAlertAccept() {
        waitAlertPresent();
        WebDriverFactory.getCurrentRunningDriver().switchTo().alert().accept();
    }

    public static void alertCancel() {
        waitAlertPresent();
        WebDriverFactory.getCurrentRunningDriver().switchTo().alert().dismiss();
    }

    public static boolean isAlert() {
        try {
            WebDriverFactory.getCurrentRunningDriver().switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUrl() {
        return WebDriverFactory.getCurrentRunningDriver().getCurrentUrl();
    }

    public static void maximizeWindow() {
        WebDriverFactory.getCurrentRunningDriver().manage().window().maximize();
    }

    public static boolean isWebElement(String xpath) {
        boolean status = false;
        WebDriverFactory.getCurrentRunningDriver().manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        try {
            SeleniumUtils.getWebElement(xpath);
            status = true;
        } catch (Exception exception) {
            // silence
        }
        WebDriverFactory.getCurrentRunningDriver().manage().timeouts().implicitlyWait(timeToBrooke, TimeUnit.SECONDS);
        return status;
    }

    public static void centralize(WebElement... elements) {
        for (WebElement element : elements) {
            ((JavascriptExecutor) WebDriverFactory.getCurrentRunningDriver())
                    .executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
            break;
        }
    }

    public void scroolPositivoCurto() {
        JavascriptExecutor jsP;
        jsP = (JavascriptExecutor) driver;
        jsP.executeScript("scrollBy(0, 250)", "");
        System.out.println("SCROLL DOWN");
    }

    public static void scroolPositivo() {
        WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
        JavascriptExecutor jsP;
        jsP = (JavascriptExecutor) driver;
        jsP.executeScript("scrollBy(0, 550)", "");
    }

    public String geraCPF() throws Exception {
        int digito1 = 0, digito2 = 0, resto = 0;
        String nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();

        //numeros gerados
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);

        int soma = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
        int valor = (soma / 11) * 11;
        digito1 = soma - valor;
        //Primeiro resto da divisão por 11.
        resto = (digito1 % 11);
        if (digito1 < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        int soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
        int valor2 = (soma2 / 11) * 11;
        digito2 = soma2 - valor2;
        //Primeiro resto da divisão por 11.
        resto = (digito2 % 11);
        if (digito2 < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        //Concatenando os numeros
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + "." + String.valueOf(n4) +
                String.valueOf(n5) + String.valueOf(n6) + "." + String.valueOf(n7) + String.valueOf(n8) +
                String.valueOf(n9) + "-";
        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        numeroGerado = numerosContatenados + nDigResult;
        System.out.println("CPF Gerado " + numeroGerado);
        return numeroGerado;
    }

}