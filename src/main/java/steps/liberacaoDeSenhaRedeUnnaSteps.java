package steps;

import Standard.factory.UrlAccess;
import Standard.inspect.Inspecionador;
import Standard.utils.evidencia.Evidencia;
import Standard.utils.others.LendoArquivoCSV;
import com.opencsv.exceptions.CsvException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pageObject.liberacaoDeSenhaRedeUnnaPageObject;
import pageObject.preAprovacaoPageObject;

import java.io.IOException;

public class liberacaoDeSenhaRedeUnnaSteps {

    private final UrlAccess urlAccess = new UrlAccess();
    private final liberacaoDeSenhaRedeUnnaPageObject libSenha = new liberacaoDeSenhaRedeUnnaPageObject();
    private preAprovacaoPageObject audit = new preAprovacaoPageObject();
    private LendoArquivoCSV arq = new LendoArquivoCSV();

    String dentista;
    String senha;
    String associado;
    String evento;
    String protocolo;
    String numGto;

    @Dado("que acessei o portal rede unna e realizei o Login")
    public void queAcesseiOPortalRedeUnnaERealizeiOLogin() throws IOException, CsvException {
        new Evidencia("Rede Unna", "HML");
        try {
            arq.LerLinhas();
            arq.getters();

            this.dentista = arq.getCd_cir_dentista();
            this.senha = arq.getSenha();

            urlAccess.setURL("Portal", "redeunna", "ia");
            libSenha.login(dentista, senha);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "----ERRO: ", "final");
        }
    }

    @Dado("que realizei o login com {string} e {string}")
    public void queAcesseiOPortalRedeUnnaERealizeiOLogin2(String usu, String senha) throws IOException, CsvException {
        new Evidencia("Rede Unna", "HML");

        this.dentista = usu;
        this.senha = senha;

        urlAccess.setURL("Portal", "redeunna", "ia");
        libSenha.login(dentista, senha);

    }

    @Quando("clicado na opção Solicitação autorização e confirmado o pop-up")
    public void clicado_na_opção_Solicitação_autorização_e_confirmado_o_pop_up() {
        libSenha.solicitaçãoAutorizaçãoEpopUp();
    }

    @Quando("inserido o telefone e codigo do beneficiario {string} e clicado em consultar e selecionar")
    public void inserido_o_telefone_e_codigo_do_beneficiario_e_clicado_em_consultar_e_selecionar2(String associado) {
        this.associado = associado;

        libSenha.telefoneCodigoAssociado(associado);
        libSenha.confirmarAssociado();
    }

    @Quando("selecionado uma especialidade e clicado em confirmar")
    public void selecionado_uma_especialidade_e_clicado_em_confirmar() {
        libSenha.selecionarEspecialidadeConfirmar();
    }

    @Quando("inserido o telefone e codigo do beneficiario e clicado em consultar e selecionar")
    public void inserido_o_telefone_e_codigo_do_beneficiario_e_clicado_em_consultar_e_selecionar() {
        try {
            this.associado = arq.getCd_associado();

            libSenha.telefoneCodigoAssociado(associado);
            libSenha.confirmarAssociado();
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "----ERRO: ", "final");
        }
    }

    @Então("validar o protocolo e clicar em ok")
    public void validar_o_protocolo_e_clicar_em_ok() {
        try {
            this.protocolo = arq.getId_protocolo();
            libSenha.validarProtocolo(protocolo, dentista);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "----ERRO: ", "final");
        }
    }

    @Então("validar o protocolo {string} e clicar em ok")
    public void validar_o_protocolo_e_clicar_em_ok2(String protocolo) {
        this.protocolo = protocolo;
        libSenha.validarProtocolo(protocolo, dentista);
    }

    @Quando("capturado o numero da gto inserir eventos e salvar em um arquivo csv")
    public void capturado_o_numero_da_gto_inserir_eventos_e_salvar_em_um_arquivo_csv() {
        try {
            this.evento = arq.getCd_evento();
            this.numGto = libSenha.pegarGto();
            libSenha.confirmarMsgGto();
            libSenha.inserirEventos(evento);
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "----ERRO: ", "final");
        }
    }

    @Quando("capturado o numero da gto inserir eventos {string} e salvar em um arquivo csv")
    public void capturado_o_numero_da_gto_inserir_eventos_e_salvar_em_um_arquivo_csv2(String evento) {
        this.evento = evento;
        this.numGto = libSenha.pegarGto();
        libSenha.confirmarMsgGto();
        libSenha.inserirEventos(evento);
    }

    @Então("deve ser clicado no botão enviar guia")
    public void deve_ser_clicado_no_botão_enviar_guia() {
        try {
            libSenha.enviarGuia(this.dentista, this.senha);

        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "----ERRO: ", "final");
        }
    }

    @E("digito o numero da gto no campo nr ficha")
    public void digitadoONumeroDaGtoNoCampoNrFicha2() throws IOException {
        audit.nrGto(this.numGto);
    }
}
