package pageObject;

import Standard.inspect.CamposController;
import Standard.inspect.Inspecionador;
import Standard.utils.others.GerarArquivoCSV;
import Standard.utils.others.SeleniumUtils;
import Standard.utils.services.APIUploader_Imagem_IA;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class liberacaoDeSenhaRedeUnnaPageObject {

    private final CamposController camposController = new CamposController();
    private final SeleniumUtils utils = new SeleniumUtils();
    private GerarArquivoCSV gerarArquivoCSV = new GerarArquivoCSV();

    public String valueGTO;

    @FindBy
    public String usuario = "//*[@id='txtUsuario']";
    @FindBy
    public String password = "//*[@id='txtSenha']";
    @FindBy
    public String btnEntrar = "//*[@id='btnLogin']";
    @FindBy
    public String erroLogin = "/html/body/form/table/tbody/tr/td/table[1]/tbody/tr/td/table/tbody/tr[7]/td/table[2]/tbody/tr[2]/td/ul/li";

    public void login(String cd_cir_Dentista, String senha) {
        camposController.CampoSend(cd_cir_Dentista, usuario, "Usuário");
        camposController.CampoSend(senha, password, "Senha");
        camposController.CampoClick(btnEntrar, "Botao Entrar");
        if (SeleniumUtils.isWebElement(erroLogin)) {
            Inspecionador.TipoTeste("erro", "Dentista de Primeiro acesso", "final");
        }
    }

    @FindBy
    public String frameOpcoes = "/html/body/table/tbody/tr[3]/td[1]/iframe";
    @FindBy
    public String frameSolicitacaoAut = "/html/body/table/tbody/tr[3]/td[2]/iframe";
    @FindBy
    public String janelaAtencao = "(//h5[contains(.,'Atenção')])[1]";
    @FindBy
    public String solAut = "//*[@id='gc']";
    @FindBy
    public String btnOkAtencao = "//*[@id='dialog-instrucoes-pdf-lib-senha']/div/div/div[3]/button";

    public void solicitaçãoAutorizaçãoEpopUp() {
        try {
            utils.switchToFrame(frameOpcoes);
            camposController.CampoClick(solAut, "Solicitar Autorização");
            utils.switchToDefault();
            utils.switchToFrame(frameSolicitacaoAut);
            camposController.CampoView(false, "", janelaAtencao, "Pop-Up Atenção");
            camposController.CampoClick(btnOkAtencao, "Botão Ok");
            utils.wait(7000);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível clicar no campo Solicitar Autorização " + e,
                    "final");
        }
    }

    @FindBy
    public String SelecionarEspec = "//*[@id='especialidadeAutoComplete']";
    @FindBy
    public String especialidade = "//li[@id='awesomplete_list_1_item_1']";
    @FindBy
    public String btnConfirmar = "//button[@id='confirmarEtapa1']";

    public void selecionarEspecialidadeConfirmar() {
        try {
            camposController.CampoClick(SelecionarEspec, "Selecionar Especialidade");
            camposController.CampoClick(especialidade, "Especialidade");
            camposController.CampoClick(btnConfirmar, "Confirmar");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível selecionar uma Especialidade " + e,
                    "final");
        }
    }

    @FindBy
    public String telAssociado = "//input[@id='celBeneficiario']";
    @FindBy
    public String codAssociado = "//*[@id='cpfCarteirinha']";
    @FindBy
    public String btnConsultar = "//button[@id='btnConsultarBeneficiario']";
    @FindBy
    public String btnSelecionar = "(//button[@type='button'])[4]";
    @FindBy
    public String btnConfirmarAssociado = "//input[@id='Enviar']";

    public void telefoneCodigoAssociado(String associado) {
        try {
            camposController.CampoSend("(11) 98542-1125", telAssociado, "Telefone");
            camposController.CampoSend(associado, codAssociado, "Codigo do Associado");
            camposController.CampoClick(btnConsultar, "Consultar");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel inserir o telefone e o codigo do associado "
                    + e, "final");
        }
    }

    public void confirmarAssociado() {
        try {
            utils.wait(2000);
            camposController.CampoClick(btnSelecionar, "Selecionar");
            camposController.CampoClick(btnConfirmarAssociado, "Confirmar Associado");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel confirmar o associado "
                    + e, "final");
        }
    }

    @FindBy
    public String janelaProtocolo = "//h5[@id='tipoProtocolo']";
    @FindBy
    public String btnOkProtocolo = "//button[@onclick='liberaSenha();']";
    @FindBy
    public String msgJaPossuiGuia = "//*[@id='dialog-usar-nova-ficha']/div";
    @FindBy
    public String liberarNovaSenha = "(//button[contains(@type,'button')])[14]";
    @FindBy
    public String protocoloTela = "//h5[@id='tipoProtocolo']";

    public void validarProtocolo(String protocoloCSV, String dentista) {

        /*Fazer a validação com o banco do protocolo*/
        camposController.CampoView(false, "", janelaProtocolo, "Protocolo");
        /*String id_protocolo;
        SeleniumUtils.getWebElement(protocoloTela).getText();
        switch (SeleniumUtils.getWebElement(protocoloTela).getText()) {
            case "Protocolo Alfa":
                id_protocolo = "1";
                break;
            case "Protocolo Beta":
                id_protocolo = "2";
                break;
            case "Protocolo Zeta":
                id_protocolo = "4";
                break;
            case "Protocolo Gama":
                id_protocolo = "5";
                break;
            default:
                id_protocolo = "0";
        }
        if (id_protocolo.equals(protocoloCSV)) {
            camposController.CampoClick(btnOkProtocolo, "Confirmação do Protocolo");
        } else {
            Inspecionador.TipoTeste("erro", "Protocolo do CSV para o Dentista "
                    + dentista + " Não é igual ao Protocolo em tela" + SeleniumUtils.getWebElement(protocoloTela).getText(), "final");
        }*/
        camposController.CampoClick(btnOkProtocolo, "Confirmação do Protocolo");
        utils.wait(2000);
        if (SeleniumUtils.isWebElement(msgJaPossuiGuia)) {
            camposController.CampoClick(liberarNovaSenha, "Liberar Nova Senha");
        } else {
            //silent
        }
       /*else {
            Inspecionador.TipoTeste("erro", "não foi possível liberar nova senha ", "final");
        }*/
    }


    @FindBy
    public String msgGto = "(//h5[@class='modal-title'])[2]";
    @FindBy
    public String btnOkMsgGto = "(//button[@type='button'][contains(.,'Ok')])[1]";

    public void confirmarMsgGto() {
        try {
            camposController.CampoView(false, "", msgGto, "Mensagem GTO - Plano");
            camposController.CampoClick(btnOkMsgGto, "Mensagem GTO - Plano");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível confirmar a mensagem da GTO " + e, "final");
        }
    }

    @FindBy
    public String numGto = "//h6[@id='txtNumeroDaGuia']";

    public String pegarGto() {
        try {
            WebElement element = SeleniumUtils.getWebElement(numGto);
            this.valueGTO = element.getText();
            Inspecionador.TipoTeste("sucesso", "GTO: " + valueGTO, "meio");
            return this.valueGTO;
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível pegar o numero da GTO " + e, "final");
        }
        return null;
    }

    @FindBy
    public String codEvento = "//input[@id='codigo1']";
    @FindBy
    public String selecEvento = "//*[@id='cboDenteRegiao']";
    @FindBy
    public String eventoDent = "//*[@id='cboDenteRegiao']/option[2]";
    @FindBy
    public String btnOkEvento = "//input[@value='Ok'][2]";
    //*[@id='btnAtualizarDenteRegiao']
    @FindBy
    public String denteRegiao = "//input[contains(@id,'local1')]";


    @FindBy
    public String face = "//input[@id='face1']";
    @FindBy
    public String face1 = "//input[@id='checkOclusal']";
    @FindBy
    public String face2 = "//input[@id,'checkVestibular')]";
    @FindBy
    public String face3 = "//input[@id,'checkLingual')]";
    @FindBy
    public String face4 = "//input[@id,'checkDistal')]";
    @FindBy
    public String face5 = "//input[@id,'checkMesial')]";
    @FindBy
    public String descricao = "//input[contains(@id,'procedimentoDescricao1')]";
    @FindBy
    public String btnOkFace = "//*[@id='btnAtualizarFace']";

    public void inserirEventos(String evento) throws AWTException {
        Robot robot = new Robot();

        try {

            camposController.CampoSend(evento, codEvento, "Evento");
            camposController.CampoClick(descricao, "Descrição");

            utils.scroolPositivo();

            SeleniumUtils.wait(2000);

            camposController.CampoClick(denteRegiao, "Dente/Região");

            camposController.CampoSend_("11", denteRegiao, "Dente/Região");
            if (SeleniumUtils.isAlert()) {
                SeleniumUtils.alertAccept();
                camposController.CampoSend("AS", denteRegiao, "Dente/Região");
            }

            SeleniumUtils.wait(5000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            SeleniumUtils.wait(2000);

            System.out.println("FACE - IF");

            camposController.CampoSend_("OV", face, "Face");
            System.out.println("OV - DIGITADO");
            SeleniumUtils.wait(5000);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            SeleniumUtils.wait(2000);

            SeleniumUtils.wait(2000);
            //    }
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível inserir eventos no campo " + e, "final");
        }
    }

    @FindBy
    public String btnAddEventos = "//h6[@class='principal-color'][contains(.,'ADICIONAR EVENTOS')]";

    public void adicionarEventos() {
        try {
            camposController.CampoClick(btnAddEventos, "Adicionar Eventos");
        } catch (Exception e) {
            Inspecionador.TipoTeste("falha", "Não foi possível adicionar eventos " + e, "meio");
        }
    }

    @FindBy
    public String ultimoProtocolo = "//*[@id='dialog']/div";
    @FindBy
    public String btnUltimoProt = "//button[@onclick='enviaFormulario()']";
    @FindBy
    public String btnEnviarGto = "//input[@id='EnviarGuiaSuperior']";
    @FindBy
    public String ultimaMsg = "//*[@id='dialog-confirmComum']/div";
    @FindBy
    public String btnOkUltimaMsg = "(//button[@type='button'][contains(.,'Ok')])[7]";

    public void enviarGuia(String dentista, String senha) {
        String ficha = this.valueGTO;
        try {
            APIUploader_Imagem_IA uploader_imagem_ia = new APIUploader_Imagem_IA();
            camposController.CampoClick(btnEnviarGto, "Enviar Guia");
            camposController.CampoView(false, "", ultimoProtocolo, "Protocolo");
            camposController.CampoClick(btnUltimoProt, "Botão OK");
            camposController.CampoView(false, "", ultimaMsg, "Mensagem de Confirmação da GTO");
            camposController.CampoClick(btnOkUltimaMsg, "Botão Ok");
            Inspecionador.TipoTeste("sucesso", "Liberação de Senha Concluída com Sucesso", "final");
            gerarArquivoCSV.gerarExcreverLerArquivoCSV(this.valueGTO);
            utils.wait(5000);
            uploader_imagem_ia.validadorSucesso(dentista, senha, ficha);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel enviar a GTO " + e, "final");
        }
    }
}

/*
* public void confirmarProtocolo() {
        try {
            camposController.CampoView(false, "", ultimoProtocolo, "Protocolo");
            camposController.CampoClick(btnUltimoProt, "Botão OK");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possivel confirmar o protocolo " + e, "final");
        }
    }
* */