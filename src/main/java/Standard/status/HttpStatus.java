package Standard.status;

import Standard.factory.WebDriverFactory;
import Standard.inspect.Inspecionador;
import Standard.utils.others.SeleniumUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HttpStatus {

    @FindBy
    private final String XPATH_ERRO_400 = "//h1[contains(text(),'JBWEB000065: HTTP Status 400')]";
    @FindBy
    private final String XPATH_ERRO_404 = "//h1[contains(text(),'JBWEB000065: HTTP Status 404')]";
    @FindBy
    private final String XPATH_ERRO_500 = "//h1[contains(text(),'JBWEB000065: HTTP Status 500')]";
    @FindBy
    private final String XPATH_ERRO_FORA = "//*[@id='frmLogin']/table/tbody/tr[2]/td";
    @FindBy
    private final String XPATH_ERRO_404_NEW = "/html/body/h1/text()";
    @FindBy
    private final String ERROR_CODE = "//div[@class='error-code']";


    /**
     * @param passo Passo em que esta sendo validado a Page
     */
    public boolean errorPage(String passo) {

        String url = WebDriverFactory.getCurrentRunningDriver().getCurrentUrl();

        boolean erro400 = SeleniumUtils.isWebElement(XPATH_ERRO_400);
        boolean erro404 = SeleniumUtils.isWebElement(XPATH_ERRO_404);
        boolean erroN404 = SeleniumUtils.isWebElement(XPATH_ERRO_404_NEW);
        boolean erro500 = SeleniumUtils.isWebElement(XPATH_ERRO_500);
        boolean erroFora = SeleniumUtils.isWebElement(XPATH_ERRO_FORA);
        boolean errorCode = SeleniumUtils.isWebElement(ERROR_CODE);

        if (erro400 || erro500 || erro404 || erroFora || erroN404 || errorCode) {
            WebElement msgErro = null;
            if (erro400) {
                msgErro = SeleniumUtils.getWebElement(XPATH_ERRO_400);
            }
            if (erro500) {
                msgErro = SeleniumUtils.getWebElement(XPATH_ERRO_500);
            }
            if (erro404) {
                msgErro = SeleniumUtils.getWebElement(XPATH_ERRO_404);
            }
            if (erroFora) {
                msgErro = SeleniumUtils.getWebElement(XPATH_ERRO_FORA);
            }
            if (erroN404) {
                msgErro = SeleniumUtils.getWebElement(XPATH_ERRO_404_NEW);
            }
            if (errorCode) {
                msgErro = SeleniumUtils.getWebElement(ERROR_CODE);
            }

            String msg = "O sistema está fora do ar!";
            Inspecionador.TipoTeste("falha", msg + " - " + msgErro.getText(), passo);
            return true;
        } else {
            String msg = "O sistema está funcional, url acessada: " + url;
            Inspecionador.TipoTeste("sucesso", msg, "inicial");
            return false;
        }
    }
}
