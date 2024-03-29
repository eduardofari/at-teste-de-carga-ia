package steps;

import Standard.factory.UrlAccess;
import Standard.factory.WebDriverFactory;
import Standard.inspect.CamposController;
import Standard.inspect.Inspecionador;
import Standard.utils.evidencia.Evidencia;
import Standard.utils.others.GerarArquivoCSV;
import Standard.utils.others.SeleniumUtils;
import Standard.utils.selenium.audiUtil;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.openqa.selenium.support.FindBy;
import pageObject.preAprovacaoPageObject;
import java.lang.System;

import java.io.IOException;

public class preAprovacaoSteps {

    private UrlAccess urlAccess = new UrlAccess();
    private preAprovacaoPageObject audit = new preAprovacaoPageObject();
    private audiUtil util = new audiUtil();
    private GerarArquivoCSV arqCsv = new GerarArquivoCSV();
    CamposController camposController = new CamposController();

    String ultimaGto;

    @Dado("que acessei o {string} {string} {string} e realizei o Login")
    public void queAcesseiOPortalPreAprovacaoERealizeiOLogin(String tipoSistema, String nm_Portal, String cenario) {
        new Evidencia(tipoSistema + " - " + nm_Portal, "IA");
        urlAccess.setURL(tipoSistema, nm_Portal, "IA");
        audit.login();
    }

    @Dado("que acessei o Pre Aprovacao e realizei o Login")
    public void queAcesseiOPortalPreAprovacaoERealizeiOLogin() {
        new Evidencia("Portal" + " - " + "Preaprovacao", "IA");
        urlAccess.setURL("Portal", "preaprovacao", "IA");
        audit.login();
    }

    @Quando("clicado na aba ficha clinica e acessado consolidacao")
    public void clicadoNaAbaFichaClinicaEAcessadoConsolidacao() throws IOException {
        audit.fichaClinica();
        audit.auditoriaInicial();
        SeleniumUtils.switchJanela();

    }

    @E("aceitar o alert")
    public void aceitarAlert() {
        audit.alert();
    }

    @E("digitado o numero da gto no campo nr ficha")
    public void digitadoONumeroDaGtoNoCampoNrFicha() throws IOException {
        arqCsv.pegarUmelementoCSV();
        this.ultimaGto = arqCsv.getUltimaGto();
        audit.nrGto(ultimaGto);
    }

    @Quando("validar o campo histórico risco")
    public void validarOCampoHistoricoRisco() {
        audit.historicoRisco();
    }

    @E("a senha apresentada no campo senha")
    public void aSenhaApresentadaNoCampoSenha() {
        audit.validarGTO();

    }

    @Então("deve ser clicado no botão confirmar")
    public void deve_ser_clicado_no_botao_confirmar() {
        audit.Confirmar();
        //  audit.Consolidar();
    }

    @E("validar o protocolo {string} do {string}")
    public void validarOProtocoloDoDentista(String protocolo, String dentista) {
        audit.senhaProtocolo(protocolo, dentista);
    }

    @FindBy
    public String btnConfirmar = "//input[@value='Confirmar']";
    @FindBy
    public String jnlConfirma = "(//div[contains(.,'Escolha a opção desejada!close')])[2]";
    @FindBy
    public String btnConfirmarAuditoria = "//span[contains(.,'Confirmar Auditoria')]";
    @FindBy
    public String jnlConfirma_ = "//div[@class='ui-dialog-titlebar  ui-widget-header  ui-corner-all  ui-helper-clearfix'][contains(.,'Escolha a opção desejada!close')]";

    @Então("validar fila Auditoria")
    public void validarFilaAuditoria() {
        try {
            //  SeleniumUtils.waitAlertPresent();

            if (SeleniumUtils.isAlertPresent()) {
                String textAlert = WebDriverFactory.getCurrentRunningDriver().switchTo().alert().getText();
                if (textAlert.equals("Não existem fichas à serem Auditadas.") ||
                        textAlert.equals("Não existem fichas à serem Auditadas") ||
                        textAlert.equals("Alert text : Não existem fichas à serem Auditadas.")) {
                    Inspecionador.TipoTeste("sucesso", "Não há mais filtro: " + textAlert, "final");
                    SeleniumUtils.alertAccept();
                } else {
                    Inspecionador.TipoTeste("falha", "Um Alert foi apresentado: " + SeleniumUtils.getTextoAlert(), "final");
                    SeleniumUtils.alertAccept();
                    validarFilaAuditoria();
                }
            } else {
                audit.validarGTO();
                SeleniumUtils.scroolPositivo();
                camposController.CampoClick(btnConfirmar, "Botão Confirmar");
                if (SeleniumUtils.isWebElement(jnlConfirma)) {
                    SeleniumUtils.wait(1000);
                    //camposController.CampoClick(btnConfirmarAuditoria, "Botão Confirmar Auditoria");
                    validarFilaAuditoria();
                }
                validarFilaAuditoria();
            }
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível validar a Fila: " + e, "final");
        }
    }
}
