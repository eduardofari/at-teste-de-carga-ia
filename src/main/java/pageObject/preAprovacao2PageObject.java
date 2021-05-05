package pageObject;

import Standard.inspect.CamposController;
import Standard.inspect.Inspecionador;
import Standard.utils.others.GerarArquivoCSV;
import Standard.utils.others.SeleniumUtils;
import Standard.utils.readers.Config;
import Standard.utils.selenium.audiUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class preAprovacao2PageObject {

    private CamposController camposController = new CamposController();
    private audiUtil util = new audiUtil();
    private GerarArquivoCSV gerarArquivoCSV = new GerarArquivoCSV();

    @FindBy
    private String userlogin = "/html/body/form/table/tbody/tr[4]/td/table/tbody/tr/td[2]/input";
    @FindBy
    private String usersenha = "/html/body/form/table/tbody/tr[4]/td/table/tbody/tr/td[5]/input";
    @FindBy
    private String btnEntrar = "/html/body/form/table/tbody/tr[4]/td/table/tbody/tr/td[7]/input[2]";

    public void login() {
        try {
            camposController.CampoSend(Config.getProperty("audit.login2"), userlogin, "Login");
            camposController.CampoSend(Config.getProperty("audit.senha2"), usersenha, "Senha");
            camposController.CampoClick(btnEntrar, "Botão Entrar");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível validar o login \n" + e, "final");
        }
    }

    @FindBy
    private String fichaClinica = "/html/body/table/tbody/tr[1]/td/table/tbody/tr[2]/td/ul/li/a";

    public void fichaClinica() {
        try {
            SeleniumUtils.waitWebElementClickable(fichaClinica);
            camposController.CampoClick(fichaClinica, "Ficha Clínica");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel validar a aba \n" + e, "final");
        }
    }

    @FindBy
    private String consolidacao = "(//a[contains(.,'Consolidação')])[1]";

    @FindBy
    private String auditoria = "//a[contains(.,'Auditoria Inicial')]";

    public void auditoriaInicial() {
        try {
            camposController.CampoClick(auditoria, "Auditoria");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível acessar auditoria inicial \n" + e, "final");
        }
    }

    public void alert() {
        try {
            SeleniumUtils.switchJanela();
            SeleniumUtils.waitAlertAccept();
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não há Alert para selecionar: \n" + e, "final");
        }
    }

    @FindBy
    private String nrGto = "//input[contains(@name,'numeroDaGuia')]";
    @FindBy
    private String campBenef = "//input[@name='codigoNaOperadora']";

    //Falta pegar a GTO do mesmo jeito que o Rede Unna pega o dentista, na ordem do primeiro pro ultimo
    //No momento está pegnado as informações do gto.csv, mas só pega o ultimo registro
    public void nrGto(String ultimaGto) {
        try {
            camposController.CampoSend(ultimaGto, nrGto, "Numero GTO");
            camposController.CampoClick(campBenef, "Clique para carregar a pagina");
            util.wait(7000);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel digitar a GTO \n" + e, "final");
        }
    }

    @FindBy
    public String telaHistoricoRisco = "//*[@id='divHistRisco']";
    @FindBy
    public String abaHistRisco = "//td[@id='td_hist_risco']";

    public void historicoRisco() {
        try {
            camposController.CampoClick(abaHistRisco, "Aba Histórico de Risco");
            camposController.CampoView(false, "", telaHistoricoRisco, "Histórico de Risco");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel validar o Histórico de Risco \n" + e, "final");
        }
    }

    @FindBy
    public String cdDentisaAprovacao = "//input[@id='cdCirDentista']";
    @FindBy
    public String senhaLiberada = "//input[@name='senhaLiberada']";
    @FindBy
    public String numGto = "//input[@id='numeroDaGuia']";


    public void validarGTO() {
        try {
            camposController.CampoView(false, "", numGto, "GTO");
            WebElement nr_ficha = SeleniumUtils.getWebElement(numGto);
            String nr_FichaValue = nr_ficha.getAttribute("value");

            camposController.CampoView(false, "", cdDentisaAprovacao, "Dentista");
            WebElement codDentista = SeleniumUtils.getWebElement(cdDentisaAprovacao);
            String dentistaValue = codDentista.getAttribute("value");

            camposController.CampoView(false, "", senhaLiberada, "Senha do Protocolo");
            WebElement txtSenha = SeleniumUtils.getWebElement(senhaLiberada);
            String senhaValue = txtSenha.getAttribute("value");

            camposController.CampoClick(abaHistRisco, "Aba Histórico de Risco");
            SeleniumUtils.waitWebElementVisible(telaHistoricoRisco);
            camposController.CampoView(false, "", telaHistoricoRisco, "Histórico de Risco");

            gerarArquivoCSV.arquivoCsvDentistaSenhaProtocolo(nr_FichaValue, dentistaValue, senhaValue.trim());
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível validar os dados da GTO \n" + e, "final");
        }
    }

    public void senhaProtocolo(String protocolo, String dentista) {
        try {
            camposController.CampoView(false, "", cdDentisaAprovacao, "Dentista");
            camposController.CampoView(false, "", senhaLiberada, "Senha do Protocolo");

            WebElement corSenha = SeleniumUtils.getWebElement(senhaLiberada);
            String cor = corSenha.getAttribute("style");

            WebElement codDentista = SeleniumUtils.getWebElement(cdDentisaAprovacao);
            String dentistaValue = codDentista.getAttribute("value");

            WebElement txtSenha = SeleniumUtils.getWebElement(senhaLiberada);
            String senhaValue = txtSenha.getAttribute("value");

            // if (dentistaValue.equals(dentista)) {
            switch (cor) {
                case "background-color: rgb(102, 255, 102)":
                    if (protocolo.equals("1")) {
                        Inspecionador.TipoTeste("sucesso", "Protocolo Alfa: " + cor, "meio");
                    } else {
                        //   Inspecionador.TipoTeste("erro", "Protocolo Alfa: " + cor, "final");
                    }
                    break;
                case "background-color: rgb(255,255,0)":
                    if (protocolo.equals("2")) {
                        Inspecionador.TipoTeste("sucesso", "Protocolo Beta: " + cor, "meio");
                    } else {
                        //   Inspecionador.TipoTeste("erro", "Protocolo Beta: " + cor, "final");
                    }
                    break;
                case "background-color: rgb(255, 0, 0)":
                    if (protocolo.equals("4")) {
                        Inspecionador.TipoTeste("sucesso", "Protocolo Zeta: " + cor, "meio");
                    } else {
                        //     Inspecionador.TipoTeste("erro", "Protocolo Zeta: " + cor, "final");
                    }
                    break;
                case "background-color: rgb(66, 170, 255)":
                    if (protocolo.equals("5")) {
                        Inspecionador.TipoTeste("sucesso", "Protocolo Gama: " + cor, "meio");
                    } else {
                        //    Inspecionador.TipoTeste("erro", "Protocolo Gama: " + cor, "final");
                    }
                    break;
                //     }
            }
            gerarArquivoCSV.arquivoCsvDentistaSenhaProtocolo("GTO", dentistaValue, senhaValue.trim());
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível validar a senha do protocolo \n" + e, "final");
        }
    }

    public void validarImagemPreAprovacao() {
        try {

        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível validar Imagem da GTO \n" + e, "final");
        }
    }

    @FindBy
    public String btnConsolidar = "//input[contains(@name,'consolidar')]";



    public void Confirmar() {
        try {

          //  SeleniumUtils.waitAlertAccept();
        //    Inspecionador.TipoTeste("sucesso", "Teste finalizado com sucesso", "final");

        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível clicar em Confirmar \n" + e, "final");
        }
    }
    public void Consolidar() {
        try {
            camposController.CampoClick(btnConsolidar, "Botão Consolidação");
            util.scroolPositivo();
            SeleniumUtils.waitAlertAccept();
            Inspecionador.TipoTeste("sucesso", "Teste finalizado com sucesso", "final");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel clicar em Consolidar \n" + e, "final");
        }
    }
}
