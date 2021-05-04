package Standard.inspect;

import Standard.factory.WebDriverFactory;
import Standard.utils.others.SeleniumUtils;
import Standard.utils.selenium.CssIterator;
import Standard.utils.selenium.audiUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//-------MAPEIA A TELA DE EDIÇÃO DE EMPRESA------//
public class CamposController {

    private int QT_CamposValidados;
    private int QTBancoVazio;
    private int QTTelaVazia;
    private int QT_CamposDivergentes;
    private int QTXpathBroken;

    private List<String> Nm_Campo_Validado = new ArrayList<>();
    private List<String> Nm_Campo_TelaVazia = new ArrayList<>();
    private List<String> Nm_Campo_BDVazio = new ArrayList<>();
    private List<String> Nm_Campo_Divergente = new ArrayList<>();
    private List<String> Nm_Campo_Quebrado = new ArrayList<>();
    private List<String> Nm_Xpath_Campo = new ArrayList<>();


    public void CampoView(boolean is_BD, String banco, String xpathCampo, String NmCampo) {
        try {
            if (is_BD) {
                CampoVer(banco, xpathCampo, NmCampo);
            } else {
                SeleniumUtils.isWebElement(xpathCampo);
                WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
                WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso", "Campo validado: " +NmCampo , "meio");
                CssIterator.markOffWebElement(driver, webElement);
            }
        } catch (Exception e) {
            Inspecionador.TipoTeste("falha", "Campo não encontrado: "+ NmCampo, "final");
        }
    }

    /**
     * VERIFICA OS CAMPOS CONFORME O NOME
     *
     * @param banco   : O dado que é esperado ser apresentado em tela
     * @param nmCampo : Nome do campo (xpath). O xpath deve ser 'public static
     *                String' na classe {@code CamposReposit}
     * @author Thiago de Moraes
     **/
    public void CampoVer(String banco, String xpathCampo, String nmCampo) {
        WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
        try {
            if (SeleniumUtils.isWebElement(xpathCampo)) {
                if (banco == null || "".equals(banco)) {
                    Nm_Campo_BDVazio.add("Campo: " + nmCampo.toUpperCase());
                    QTBancoVazio++;
                    if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                            || "".equals(cod.getAttribute("value").trim())) {
                        Inspecionador.TipoTeste("sucesso",
                                "O campo " + nmCampo.toUpperCase() + " está vazio em tela e no banco de dados: OK ", "meio");
                        Nm_Campo_Validado.add("Campo: " + nmCampo.toUpperCase());
                        QT_CamposValidados++;
                    } else {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase()
                                        + "' está vazio no Banco de Dados, mas em tela: " + cod.getAttribute("value"),
                                "final");
                        QTBancoVazio++;
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;
                    }
                } else {
                    if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                            || "".equals(cod.getAttribute("value").trim())) {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' está preenchido no Banco de Dados: " + banco + " mas está vazio em tela",
                                "meio");
                        Nm_Campo_TelaVazia.add("Campo: " + nmCampo.toUpperCase());
                        QTTelaVazia++;
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;

                    } else if (cod.getAttribute("value").trim().equals(banco) || cod.getText().equals(banco)) {
                        Inspecionador.TipoTeste("sucesso", "O campo '" + nmCampo.toUpperCase() + "' foi validado",
                                "meio");

                        Nm_Campo_Validado.add("Campo: " + nmCampo.toUpperCase());
                        QT_CamposValidados++;
                    } else {
                        Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' não confere", "meio");
                        Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                                + cod.getAttribute("value"));
                        QT_CamposDivergentes++;
                    }
                }
            } else {
                Nm_Xpath_Campo.add("\n Campo " + nmCampo.toUpperCase() + "xpath: " + xpathCampo);
                QTXpathBroken++;
                Inspecionador.TipoTeste("falha", "O campo '" + nmCampo.toUpperCase() + "' não confere", "meio");

            }
        } catch (Exception e) {
            String erro = "Não foi possivel validar o campo: " + nmCampo.toUpperCase();
            Nm_Campo_Quebrado.add("Campo: " + nmCampo.toUpperCase());
            Nm_Campo_Divergente.add("\n Campo: " + nmCampo.toUpperCase() + " Banco: " + banco + " Tela: "
                    + cod.getAttribute("value"));
            QT_CamposDivergentes++;
            Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }

    public void CalculosCampos() {

        //MENSAGENS DO LOG
        String CalcDivergentes = "Existe(m) " + this.QT_CamposDivergentes + " Divergente(s) entre Banco de Dados e Tela ";

        String CalcQuebrados = "Existe(m) " + QTXpathBroken + " campo(s) com dados quebrado(s) na tela ";

        String CalcBDVazio = "Existe(m) " + QTBancoVazio + " Vazio(s) no Banco de Dados ";

        String CalcTelaVazio = "Existe(m) " + QTTelaVazia + " Vazio(s) na Tela ";

        String CalcValidados = "Existe(m) " + QT_CamposValidados + " campo(s) validado(s) com sucesso ";


        if (Nm_Campo_BDVazio.size() == Nm_Campo_TelaVazia.size() && QTBancoVazio != 0) {
            if (Nm_Campo_BDVazio.equals(Nm_Campo_TelaVazia)) {
                Inspecionador.getMessage(CalcTelaVazio + "\n e " + CalcBDVazio +
                        "\n Campo(s) vazio(s) de Tela:" + Nm_Campo_TelaVazia +
                        "\n Campo(s) vazio(s) de Banco de Dados:" + Nm_Campo_BDVazio);
            }
            if (QTXpathBroken > 0) {
                Inspecionador.getMessage(CalcQuebrados + "\n Campo(s) Quebrado(s): " + Nm_Campo_Quebrado);
                Inspecionador.getMessage("Xpath dos campos quebrados: " + Nm_Xpath_Campo);
            }
            if (QTBancoVazio > 0) {
                Inspecionador.getMessage(CalcBDVazio + "\n Campo(s) " + Nm_Campo_BDVazio);
            }
            if (QTTelaVazia > 0) {
                Inspecionador.getMessage(CalcTelaVazio + "\n Campo(s) " + Nm_Campo_TelaVazia);
            }
            if (QT_CamposDivergentes > 0) {
                Inspecionador.getMessage(CalcDivergentes + "\n Campo(s) Divergente(s): " + Nm_Campo_Divergente);
            }
            if (QT_CamposValidados > 0) {
                Inspecionador.getMessage(CalcValidados + " Sendo: " + Nm_Campo_Validado);
            }
        }
    }

    public void ElementExistsAndIsDisplay(String element, String tipoVariavel, String nmCampo) {
        //Verifica se elemento esta visivel
        try {
            if (tipoVariavel.equalsIgnoreCase("xpath")) {
                SeleniumUtils.isWebElement(element);
                WebElement cod = SeleniumUtils.getWebElement(element);
                WebElement webElement = SeleniumUtils.getWebElement(element);
                WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso", "Realizada a ação visualizar campo '" + nmCampo.toUpperCase() + "'", "meio");
                CssIterator.markOffWebElement(driver, webElement);
                cod.isDisplayed();
            } else if (tipoVariavel.equalsIgnoreCase("id")) {
                SeleniumUtils.isWebElement(element);
                WebElement cod = SeleniumUtils.getWebElementId(element);
                WebElement webElement = SeleniumUtils.getWebElementId(element);
                WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso", "Realizada a ação visualizar campo '" + nmCampo.toUpperCase() + "'", "meio");
                CssIterator.markOffWebElement(driver, webElement);
                cod.isDisplayed();
            } else if (tipoVariavel.equalsIgnoreCase("name")) {
                SeleniumUtils.isWebElement(element);
                WebElement cod = SeleniumUtils.getWebElementName(element);
                WebElement webElement = SeleniumUtils.getWebElementName(element);
                WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso", "Realizada a ação visualizar campo '" + nmCampo.toUpperCase() + "'", "meio");
                CssIterator.markOffWebElement(driver, webElement);
                cod.isDisplayed();
            } else if (tipoVariavel.equalsIgnoreCase("classname") || tipoVariavel.equalsIgnoreCase("className")) {
                SeleniumUtils.isWebElement(element);
                WebElement cod = SeleniumUtils.getWebElementClassName(element);
                WebElement webElement = SeleniumUtils.getWebElementClassName(element);
                WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso", "Realizada a ação visualizar campo '" + nmCampo.toUpperCase() + "'", "meio");
                CssIterator.markOffWebElement(driver, webElement);
                cod.isDisplayed();
            }
            ;
        } catch (NoSuchElementException ex) {
            ex.getMessage();
        }
    }

    public boolean elementExistsAndIsDisplay_Xpath(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.xpath(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistDisplay_(String xpath) {
        WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
        WebElement elemento = driver.findElement(By.xpath(xpath));
        return elemento.isDisplayed();
    }

    public boolean elementExistsAndIsDisplay_Id(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.id(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistsAndIsDisplay_Name(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.name(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistsAndIsDisplay_ClassName(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.className(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistsAndIsDisplay_LinkText(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.linkText(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistsAndIsDisplay_CssSelector(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.cssSelector(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean elementExistsAndIsDisplay_TagName(String var) {
        try {
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            WebElement elemento = driver.findElement(By.tagName(var));
            return elemento.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }


    public void CampoClick(String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            CssIterator.markWebElement(webElement);
            Inspecionador.TipoTeste("sucesso", "Realizada a ação de clicar no campo '" + nmCampo.toUpperCase() + "'", "meio");
            CssIterator.markOffWebElement(driver, webElement);
            cod.click();
        } catch (Exception e) {
            String erro = "Não foi possível clicar no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }

    public void DoubleClick(String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            Actions codi = new Actions(driver);
            if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                    || "".equals(cod.getAttribute("value").trim())) {
                codi.doubleClick();
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
                CssIterator.markOffWebElement(driver, webElement);
            } else {
                Inspecionador.TipoTeste("falha",
                        "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
            }

        } catch (Exception e) {
            String erro = "Não foi possível clicar no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }

    public void JustClick(String xpathCampo) {
        try {
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            cod.click();
        } catch (Exception e) {
            Inspecionador.TipoTeste("falha", "Não possivel realizar o clique", "meio");
        }
    }

    public void ClickBuilder(String xpathCampo, String nmCampo) {
        try {
            for (String page : WebDriverFactory.getCurrentRunningDriver().getWindowHandles()) {
                WebDriverFactory.getCurrentRunningDriver().switchTo().window(page);
                Actions builder = new Actions(WebDriverFactory.getCurrentRunningDriver());
                WebElement btnToClick = SeleniumUtils.getWebElement(xpathCampo);
                builder.moveToElement(btnToClick).build().perform();
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de clicar no campo '" + nmCampo.toUpperCase() + "'", "meio");
            }
        } catch (Exception e) {
            String erro = "Não foi possível clicar no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "meio");
        }
    }

    /**
     * @param dados      os dados a serem inseridos no campo desejado
     * @param xpathCampo xpath do campo
     * @param nmCampo    nome para a {@link Standard.utils.evidencia.Evidencia} do campo
     * @author Thiago de Moraes;
     */
    public void CampoSend(String dados, String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            cod.clear();
            if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                    || "".equals(cod.getAttribute("value").trim())) {
                cod.sendKeys(dados);
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
                CssIterator.markOffWebElement(driver, webElement);
            } else {
                Inspecionador.TipoTeste("falha",
                        "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
            }
        } catch (Exception e) {
            String erro = "Não foi possivel inserir dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }
    public void CampoSend_(String dados, String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                    || "".equals(cod.getAttribute("value").trim())) {
                cod.sendKeys(dados);
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
                CssIterator.markOffWebElement(driver, webElement);
            } else {
                Inspecionador.TipoTeste("falha",
                        "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
            }
        } catch (Exception e) {
            String erro = "Não foi possível inserir dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", " - Elemento não selecionável", "meio");
        }
    }

    /**
     * @param dados      os dados a serem inseridos no campo desejado
     * @param xpathCampo xpath do campo
     * @param nmCampo    nome para a {@link Standard.utils.evidencia.Evidencia} do campo
     * @author Thiago de Moraes;
     */
    public void CampoClickSend(String dados, String xpathCampo, String nmCampo) {
        try {
            audiUtil util = new audiUtil();
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            if (cod.getAttribute("value") == null || " ".equals(cod.getAttribute("value").trim())
                    || "".equals(cod.getAttribute("value").trim()) /*|| "_".equals(cod.getAttribute("value").trim())*/) {
                //xpathCampo.replaceAll("_", "");
                cod.click();
                cod.sendKeys(dados);
                CssIterator.markWebElement(webElement);
                Inspecionador.TipoTeste("sucesso",
                        "Realizada a ação de inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
                CssIterator.markOffWebElement(driver, webElement);
            } else {
                Inspecionador.TipoTeste("falha",
                        "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
            }
        } catch (Exception e) {
            String erro = "Não foi possivel inserir dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }

    //Metodo do Excel que não consegui fazer funcionar - João Paulo
    public static WebDriver driver;

    public void lerValorDeUmaCelularPeloNomeDaColuna(String dados) throws IOException {
        try {
            FileInputStream file = new FileInputStream("target\\evidencias\\arquivos\\MassaFrontOrçamento.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int noOfColumns = sheet.getRow(0).getLastCellNum();
            //System.out.println(noOfColumns);
            String[] Headers = new String[noOfColumns];
            for (int j = 0; j < noOfColumns; j++) {
                Headers[j] = sheet.getRow(9).getCell(j).getStringCellValue();
            }
            for (int a = 0; a < noOfColumns; a++) {
                if (Headers[a].equals("GTO")) {
                    driver.findElement(By.name("GTO")).sendKeys(sheet.getRow(1).getCell(a).getStringCellValue());
                    //Thread.sleep(2000);
                    break;
                } else {
                    Inspecionador.TipoTeste("falha",
                            "O campo '" + dados.toUpperCase() + "' não pode ser capturado do excel", "final");
                }
            }
            file.close();
            System.exit(0);
        } catch (Exception e) {
            String erro = "Não foi possivel inserir dados no campo: " + dados.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }

    public void ClearSendCampo(String dados, String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            cod.clear();
            cod.sendKeys(dados);
            //CssIterator.markWebElement(webElement);
            Inspecionador.TipoTeste("sucesso",
                    "Realizada a ação de apagar e inserir dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
            //CssIterator.markOffWebElement(driver, webElement);
        } catch (Exception e) {
            String erro = "Não foi possivel inserir dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }

    public void ClearEspacoBranco(String xpathCampo, String nmCampo) {
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            cod.clear();
            //CssIterator.markWebElement(webElement);
            Inspecionador.TipoTeste("sucesso",
                    "Realizada a ação de apagar dados no campo '" + nmCampo.toUpperCase() + "' ", "meio");
            //CssIterator.markOffWebElement(driver, webElement);
        } catch (Exception e) {
            String erro = "Não foi possivel apagar os dados no campo: " + nmCampo.toUpperCase();
            Inspecionador.TipoTeste("falha", erro + " " + e, "final");
        }
    }

    public String separarCodigoMensagem(String xpathCampo, String nmCampo) {
        String nroImportacao = "0";
        try {
            SeleniumUtils.isWebElement(xpathCampo);
            WebElement cod = SeleniumUtils.getWebElement(xpathCampo);
            WebElement webElement = SeleniumUtils.getWebElement(xpathCampo);
            WebDriver driver = WebDriverFactory.getCurrentRunningDriver();
            String texto = cod.getText();
            nroImportacao = texto.replaceAll("[^0-9]", "").trim();
            System.out.println(nroImportacao.trim());
            CssIterator.markWebElement(webElement);
            Inspecionador.TipoTeste("sucesso",
                    "Realizada a ação de capturar o codigo da mensagem '" + nmCampo.toUpperCase() + "' ", "meio");
            CssIterator.markOffWebElement(driver, webElement);
            return nroImportacao;
        } catch (Exception e) {
            Inspecionador.TipoTeste("falha",
                    "O campo '" + nmCampo.toUpperCase() + "' não pode ser editado, já está preenchido", "final");
        }
        return nroImportacao;
    }

    public void SimpleSendKeys(String xpathCampo, String data, String nmCampo) {
        WebElement element = SeleniumUtils.getWebElement(xpathCampo);
        element.click();
        element.sendKeys(data);
    }
}
