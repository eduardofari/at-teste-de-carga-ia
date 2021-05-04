package Standard.utils.evidencia;

import Standard.factory.WebDriverFactory;
import Standard.utils.exceptions.ExceptionUtils;
import Standard.utils.others.SeleniumUtils;
import Standard.utils.readers.Config;
import com.itextpdf.text.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Evidencia {

    private static Document document;
    private static String nomeCt;
    private static int passeds;
    private static int errors;
    private static String nomeEvidencia;
    private static String caminho;

    public Evidencia(String cenario, String testName) {
        document = new Document();
        passeds = 0;
        errors = 0;
        nomeCt = cenario + "_" + testName;
        EvidenciaIterator.setPathToSaveEvidence(Config.getProperty("path.save.evidencias"));
        document.open();
        Cover.insertCover();
    }

    public static void addPassedText(String text) {
        setPassed(1);
        addFormatedText(text, FontFactory.HELVETICA, Font.BOLDITALIC, BaseColor.GREEN.darker());
    }

    public static void addPassedText(String text, int size) {
        setPassed(1);
        addFormatedText(text, FontFactory.HELVETICA, size, Font.BOLDITALIC, BaseColor.GREEN.darker(), 0, 0, 0);
    }

    public static void addFailedText(String text) {
        seterror(1);
        addFormatedText(text, FontFactory.HELVETICA, Font.BOLDITALIC, BaseColor.RED);
    }

    public static void addFailedText(String text, int size) {
        seterror(1);
        addFormatedText(text, FontFactory.HELVETICA, size, Font.BOLDITALIC, BaseColor.RED, 0, 0, 0);
    }

    public static void addFormatedText(String text, String name, int style, BaseColor color) {
        addFormatedText(text, name, 12, style, color, 0, 0, 0);
    }

    public static void addFormatedText(String text, String name, float size, int style, BaseColor color,
                                       int spaceBefore, int spaceAfter, int align) {
        try {
            Font font = FontFactory.getFont(name, size, style, color);
            Paragraph paragraph = new Paragraph(text, font);
            paragraph.setSpacingBefore(spaceBefore);
            paragraph.setSpacingAfter(spaceAfter);
            paragraph.setAlignment(align);
            document.add(paragraph);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void addExternalImage(String path, int align, float ySize, float xSize) {
        try {
            Image image = Image.getInstance(path);
            image.setAlignment(align);
            image.scaleAbsolute(ySize, xSize);
            document.add(image);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void insertPrint() {
        TakesScreenshot ts = (TakesScreenshot) WebDriverFactory.getCurrentRunningDriver();
        byte[] imagem = ts.getScreenshotAs(OutputType.BYTES);

        try {
            Image image = Image.getInstance(imagem);
            image.scaleToFit(520f, 520f);
            document.add(image);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void addNewPage() {
        document.newPage();
    }

    public static void addText(String text) {
        try {
            document.add(new Paragraph(text));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void print(String text, List<WebElement> values) {
        print(text, values.toArray(new WebElement[0]));
    }

    public static void print(String text, List<WebElement> values, WebElement... outerValues) {
        for (WebElement outerValue : outerValues) {
            values.add(outerValue);
        }
        print(text, values.toArray(new WebElement[values.size()]));
    }

    public static void print(String text) {
        addNewPage();
        insertPrint();
        addText(text);
    }

    public static void print(String text, WebElement... values) {
        addNewPage();
        if (values.length > 0) {
            SeleniumUtils.centralize(values);

            CssIterator.markWebElement(values);
        }
        insertPrint();
        if (values.length > 0) {
            CssIterator.markOffWebElement(values);
        }
        addText(text);
    }

    public static void addPrintPassed(String text) {
        addNewPage();
        insertPrint();
        addPassedText(text);
    }

    public static void addPrintFailed(String text) {
        addNewPage();
        insertPrint();
        addFailedText(text);
    }

    public static void addPrintPassed(String text, WebElement... elements) {
        addNewPage();
        print("", elements);
        addPassedText(text);
    }

    public static void addPrintFailed(String text, WebElement... elements) {
        addNewPage();
        print("", elements);
        addFailedText(text);
    }

    public static String getNome() {
        return nomeEvidencia;
    }

    public static void setNomeEvidencia(String nomeEvidencia) {
        Evidencia.nomeEvidencia = nomeEvidencia;
    }

    public static String getCaminho() {
        return caminho;
    }

    public static void setCaminho(String caminho) {
        Evidencia.caminho = caminho;
    }

    public static Document getDocument() {
        return document;
    }

    public static String getNomeCt() {
        return nomeCt;
    }

    public static int getPassed() {
        return passeds;
    }

    public static void setPassed(int passed) {
        Evidencia.passeds += passed;
    }

    public static int getErrors() {
        return errors;
    }

    public static void seterror(int error) {
        Evidencia.errors += error;
    }

}