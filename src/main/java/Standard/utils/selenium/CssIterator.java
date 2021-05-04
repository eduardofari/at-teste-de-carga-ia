package Standard.utils.selenium;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Standard.factory.WebDriverFactory;

public class CssIterator {

	public static List<String> style;

public static void markWebElement(WebElement ... elements) {
		
		style = new LinkedList<>();
		
		JavascriptExecutor js=(JavascriptExecutor)WebDriverFactory.getCurrentRunningDriver();
		for (WebElement element : elements) {
			style.add(element.getCssValue("border").toString());			
			js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid Red;');", element);
		}
	}

public static void markWebElement_(WebElement ... elements) {
	
	style = new LinkedList<>();
	
	JavascriptExecutor js=(JavascriptExecutor)WebDriverFactory.getCurrentRunningDriver();
	for (WebElement element : elements) {
		style.add(element.getCssValue("border").toString());			
		js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid Darkred;');", element);
	}
}

	/**
	 * Este_metodo_retira o_javascript de_marcacao da_pagina web
	 */
	public static void markOffWebElement(WebDriver driver, WebElement... elements) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		int i = 0;
		for (WebElement element : elements) {
			js.executeScript("arguments[0].setAttribute('style', '" + style.get(i++) + "');", element);
		}
	}

}
