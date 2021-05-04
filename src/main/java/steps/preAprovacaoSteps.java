package steps;

import Standard.factory.UrlAccess;
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

    @Então("validar fila Auditoria")
    public void validarFilaAuditoria() {
        SeleniumUtils.switchJanela();
       // audit.alert();
        while (!util.isAlert()) {
            audit.validarGTO();
            SeleniumUtils.scroolPositivo();
            camposController.CampoClick(btnConfirmar, "Botão Confirmar");
        }
        if (SeleniumUtils.isAlert()) {
            if (SeleniumUtils.getTextoAlert().equals("Não existem fichas à serem Auditadas.")) {
                Inspecionador.TipoTeste("sucesso", "Não há mais filtro: " + SeleniumUtils.getTextoAlert(), "final");
            }else{
                Inspecionador.TipoTeste("falha", "Um Alert foi apresentado: " + SeleniumUtils.getTextoAlert(), "final");
                SeleniumUtils.alertAccept();
                validarFilaAuditoria();
            }
        } else {
            validarFilaAuditoria();
        }
    }
}
