package Standard.utils.evidencia;

import Standard.factory.WebDriverFactory;
import Standard.utils.others.SeleniumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

public class CssIterator {
    private static WebElement tela = SeleniumUtils.getWebElement("/html");
    private static List<WebElement> style;

    public static void markWebElement(WebElement... elements) {

        style = new LinkedList<WebElement>();

        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getCurrentRunningDriver();
        for (WebElement element : elements) {
            style.add(element);
            js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid Darkgreen;');", element, tela);
        }
    }

    static void markOffWebElement(WebElement... elements) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverFactory.getCurrentRunningDriver();

        int i = 0;
        for (WebElement element : elements) {
            js.executeScript("arguments[0].setAttribute('style', '" + style.get(i++) + "');", element, tela);
        }
    }

}
