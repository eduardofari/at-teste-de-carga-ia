package Standard.utils.selenium;

import Standard.factory.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class audiUtil {

	public static WebDriver driver;
	WebElement webElement;
	private WebDriverWait wait;
	public Integer timeToBrooke;

	public audiUtil() {
		timeToBrooke = Integer.parseInt(getProperty("time.to.brooke"));
		driver = WebDriverFactory.getCurrentRunningDriver();
	}

	/* switchTo_Nova_Janela */
	public void switchToNewWindow(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();
		Set<String> openedWindows = driver.getWindowHandles();
		if (openedWindows.size() > 1) {
			for (String newWindow : openedWindows) {
				driver.switchTo().window(newWindow);
			}
			driver.close();
			driver.switchTo().window(mainWindow);
		}
	}

	/**
	 * TakesScreenshot
	 */
	public static void takeScreenshot(String fileName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Date data = new Date();
		try {
			FileUtils.copyFile(scrFile, new File(".//target//evidencias" + fileName + data + ".png"), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Mata_Processos
	public static void KillProcess() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String lerArquivo(String path) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			while (reader.ready()) {
				sb.append(reader.readLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void highLightElement(WebDriver driver, String xpath) {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].style.border='3px solid red'", xpath);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean highlightElement(WebElement element) {
		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='3px solid red'", element);
				driver = null;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	/**
	 * Retorna_IP_Maquina_Execucao
	 */
	public String getIpClient() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception exception) {
			throwException(exception);
		}
		return null;
	}

	/**
	 * Retorna_Nome_Maquina
	 */
	public String getPcName() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (Exception exception) {
			throwException(exception);
		}
		return null;
	}

	public void throwException(Exception exception) {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(exception.getMessage() + "\n");
		for (StackTraceElement trace : exception.getStackTrace()) {
			stringBuilder.append(trace.toString() + "\n");
		}

	}

	/**
	 * Usuario_Logado_Maquina_Execuï¿½ï¿½o
	 */
	public String getWindowsUser() {
		return System.getProperty("user.name");
	}

	/**
	 * Copia_para_Origem_Destino
	 */
	public void copyFile(String pathWithExtensionFileOrigem, String pathWithExtensionFileDetino) {
		try {
			FileUtils.copyFile(new File(pathWithExtensionFileOrigem), new File(pathWithExtensionFileDetino));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Start_(.Bat)
	 */
	public void acionarBat(String path, String nomeBat) {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", nomeBat);
		File dir = new File(path);
		pb.directory(dir);
		Process p = null;
		try {
			p = pb.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.destroy();
	}

	/**
	 * Verifica_Existencia_Arquivo_Passando_Caminho_Nome_Extensao
	 */
	public boolean isFile(String path) {
		return new File(path).exists();
	}

	public boolean UnhighlightElement(WebElement element) {
		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border=''", element, "");
				driver = null;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return false;
	}

	public String formataData(String data) {
		return formataData(data, "");
	}

	public String formataData(String data, String pattern) {

		if (data.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {

			String ano = data.substring(0, 4);
			String mes = data.substring(5, 7);
			String dia = data.substring(8, 10);

			return dia + "/" + mes + "/" + ano;
		}

		return data;
	}

	/**
	 * Retorna_Data_Atual
	 */
	public String insertDateNow() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			return formatDate.format(new Date());
		} catch (Exception exception) {
			throwException(exception);
		}
		return null;
	}

	/**
	 * Retorna_Data_Atual_Sem_mascara
	 */
	public String insertDateNowSemMascara() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
			return formatDate.format(new Date());
		} catch (Exception exception) {
			throwException(exception);
		}
		return null;
	}

	/**
	 * Retorna_Hora_Atual
	 */
	public String insertTimeNow() {
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
		return formatTime.format(new Date());
	}

	public WebElement getWebElement(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public void Enter(String xpath) {
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.ENTER);
	}

	public void DOWN(String xpath) {
		driver.findElement(By.xpath(xpath)).sendKeys(Keys.DOWN);
	}

	public void DigitePorCaracter(String xpath, String value) {
		String val = value;
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();

		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			element.sendKeys(s);
		}
	}

	public List<WebElement> getWebElements_xpath(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public List<WebElement> getWebElements_id(String id) {
		return driver.findElements(By.id(id));
	}

	public List<WebElement> getWebElements_name(String name) {
		return driver.findElements(By.name(name));
	}

	public List<WebElement> getWebElements_className(String className) {
		return driver.findElements(By.className(className));
	}

	public Select getSelect(String xpath) {
		return new Select(getWebElement(xpath));
	}

	public void waitWebElementVisible(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeToBrooke);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception exception) {
			throwException(exception);
		}
	}

	public boolean waitWebElementVisible_(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeToBrooke);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception exception) {
			throwException(exception);
		}
		return false;
	}

	public boolean testExistsElement(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	public boolean testElements(String xpath) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			driver.findElement(By.xpath(xpath)).isDisplayed();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	public void waitWebElementClickable(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeToBrooke);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		} catch (Exception exception) {
			throwException(exception);
		}
	}

	public void clickJavascript(String xpath) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickJavascriptVariante(String xpath) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[1].click();", element);
	}

	public void scroolOnElement(WebElement webElement) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",webElement);
	}

	public void scroolPositivo() {
		JavascriptExecutor jsP;
		jsP = (JavascriptExecutor) driver;
		jsP.executeScript("scrollBy(0, 450)", "");
	}

	public void scroolPositivoCurto() {
		JavascriptExecutor jsP;
		jsP = (JavascriptExecutor) driver;
		jsP.executeScript("scrollBy(0, 250)", "");
		System.out.println("SCROLL DOWN");
	}

	public void scroolNegativo() {
		JavascriptExecutor jsN;
		jsN = (JavascriptExecutor) driver;
		jsN.executeScript("scrollBy(0, -300)", "");
	}

	public void cliks(String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void waitWebElementSelectable(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeToBrooke);
			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
		} catch (Exception exception) {
			throwException(exception);
		}
	}

	public void waitAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeToBrooke);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception exception) {
			throwException(exception);
		}
	}

	public void sendText(String texto, String xpath) {
		waitWebElementVisible(xpath);
		getWebElement(xpath).clear();
		getWebElement(xpath).sendKeys(texto);
	}

	public void click(String xpath) {
		waitWebElementClickable(xpath);
		getWebElement(xpath).click();
	}

	public void selectCombo(String value, String xpath) {
		waitWebElementSelectable(xpath);
		getSelect(xpath).selectByValue(value);
	}

	public void switchJanela() {
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
		}
	}

	public static String backToParentBrowser(WebDriver driver) {
		String parentWindow = driver.getWindowHandle();
		return parentWindow;
	}

	public void switchToJanela() {
		driver.switchTo().window(audiUtil.backToParentBrowser(driver));
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public WebDriver switchToFrame(String xpath) {
		return driver.switchTo().frame(getWebElement(xpath));
	}

	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception exception) {
			throwException(exception);
		}
	}

	public String getTextoAlert() {
		wait(2000);
		waitAlertPresent();
		return driver.switchTo().alert().getText();
	}

	public void alertAccept() {
		waitAlertPresent();
		driver.switchTo().alert().accept();
	}

	public void alertCancel() {
		waitAlertPresent();
		driver.switchTo().alert().dismiss();
	}

	public boolean isAlert() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUrl(String xpath) {
		return driver.getCurrentUrl();
	}

	public void get(String url) {
		System.out.println(driver != null ? "DRIVER OK" : "DRIVER NULO!");
		driver.get(url);
	}

	public String getText_Xpath(String xpath) {
		return driver.findElement(By.xpath(xpath)).getText();
	}

	public String getDiaMesAno(){
		Date calendario = new Date();
		SimpleDateFormat dh = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("A data atual é: "+dh.format(calendario));
		String data = dh.format(calendario);
		return data;
	}

	public String getText_Id(String id) {
		return driver.findElement(By.id(id)).getText();
	}

	public String getText_Name(String name) {
		return driver.findElement(By.name(name)).getText();
	}

	public String getText_ClassName(String className) {
		return driver.findElement(By.className(className)).getText();
	}

	public String getText_LinkText(String linkText) {
		return driver.findElement(By.linkText(linkText)).getText();
	}

	public String getText_CssSelector(String cssSelector) {
		return driver.findElement(By.cssSelector(cssSelector)).getText();
	}

	public String getText_TagName(String tagName) {
		return driver.findElement(By.tagName(tagName)).getText();
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public void updatePage() {
		driver.navigate().refresh();
	}

	public void Close() {
		driver.quit();
	}

	public void closeAba() {
		driver.close();
	}

	public static void centralize(WebElement... elements) {
		for (WebElement element : elements) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
			break;
		}
	}

	/**
	 * Le_Arquivo_Properties_e_Retorna_valor_correspondente_pela_chave_informada
	 */
	public String getProperty(String value) {

		Properties properties = null;
		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream("./src/main/resources/properties/config.properties");
			properties = new Properties();
			properties.load(inputStream);
		} catch (Exception exception) {
			throwException(exception);
		}

		return properties.getProperty(value).trim();
	}

	public String waitExistGetText(String elemento, int timeOut) throws InterruptedException {
		for (int i = 1; i <= timeOut; i++) {
			try {
				WebElement element = driver.findElement(By.xpath(elemento));
				if (element.isDisplayed()) {
					String texto = element.getText();
					return texto;
				}
			} catch (Exception e) {
				if (i == (timeOut / 2)) {
					driver.navigate().refresh();
					Thread.sleep(2000);
				} else if (i == (timeOut - 1)) {
					driver.quit();
				}
				Thread.sleep(2000);
			}
		}
		return elemento;
	}

	public WebElement waitWebElementVisible_web(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitURL(String url) {
		wait.until(ExpectedConditions.urlContains(url));
	}

	public void waitFrameAndSwitch(WebElement frame) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public void waitTextInElement(WebElement elemento, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(elemento, text));
	}

	public WebElement waitWebElementClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public Alert getAlert() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert;
	}

	public boolean elementExists(String xpath) {
		try {
			moveToElement(xpath);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	public WebElement selectItemComboBox(String nomeItem) {
		return driver.findElement(By.linkText(nomeItem));
	}

	public void moveToElement(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Actions acao = new Actions(driver);
		acao.moveToElement(element).build().perform();
	}

	public void home(WebElement elemento) {
		Actions builder = new Actions(driver);
		WebElement el = elemento;
		builder.keyDown(Keys.CONTROL).moveByOffset(10, 25).clickAndHold(el).build().perform();
	}

	public Object executeJs(String script) {
		if (driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) driver).executeScript(script);
		} else {
			throw new IllegalStateException("This driver does not support JavaScript!");
		}
	}

	public Object executeJs(String script, WebElement elemento) {
		if (driver instanceof JavascriptExecutor) {
			return ((JavascriptExecutor) driver).executeScript(script, elemento);
		} else {
			throw new IllegalStateException("This driver does not support JavaScript!");
		}
	}

	public boolean elementExistsAndIsDisplay(By byLocator) {
		try {
			WebElement elemento = driver.findElement(byLocator);
			return elemento.isDisplayed();
		} catch (NoSuchElementException ex) {
			return false;
		}
	}

	public void uploadfile(String path) throws AWTException, InterruptedException {
		StringSelection s = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(4000);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	public void pasteAll() throws Exception
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	}

	public void selectAll() throws Exception
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_A);
	}

	public void robotZoomOut() throws Exception
	{
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_SUBTRACT);
		robot.keyRelease(KeyEvent.VK_SUBTRACT);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void escape () {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
		}
	}

	public void hitKey(Robot robot, int mode, int keycode) {
		robot.keyPress(mode);
		robot.keyPress(keycode);
		robot.keyRelease(mode);
		robot.keyRelease(keycode);
		robot.waitForIdle();
	}

	public void enterInput(Robot robotKeyInput, int keyInputs[][]) {
		for (int i = 0; i < keyInputs.length; i++) {
			String strKeyInput = "KeyPress=>";
			final int noOfKeyInputs = keyInputs[i].length;
			for (int j = 0; j < noOfKeyInputs; j++) {
				robotKeyInput.keyPress(keyInputs[i][j]);
				strKeyInput += (Integer.toHexString(keyInputs[i][j])) + ":";
			}

			strKeyInput += "KeyRelease=>";
			for (int j = noOfKeyInputs - 1; j >= 0; j--) {
				robotKeyInput.keyRelease(keyInputs[i][j]);
				strKeyInput += (Integer.toHexString(keyInputs[i][j])) + ":";
			}
			System.out.println(strKeyInput);
		}
	}

	public void simulate(){
		try {
			Robot robot = new Robot();

			// Clique do mouse
			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			// Pressionamento de tecla
			robot.keyPress(KeyEvent.VK_A);
			robot.keyRelease(KeyEvent.VK_A);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public void combination() throws AWTException {
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_SHIFT);
		r.keyPress(KeyEvent.VK_F1);
		r.keyRelease(KeyEvent.VK_F1);
		r.keyRelease(KeyEvent.VK_SHIFT);
		r.keyRelease(KeyEvent.VK_CONTROL);
	}
}
