package Standard.utils.others;

import java.util.Date;

public class EncapXlsx {

    private double codPlano;
    private String siglaPlano;
    private double empresa;
    private double codAssociado;
    private String cpf;
    private Date dtNasc;
    private String nomeAssociado;
    private String proposta;
    private double codDentista;
    private String senhaDentista;
    private double gto;
    private String statusGto;
    private String statusOrcamento;
    private double numSeq;
    private String evento;
    private String descEvento;
    private double regiao;
    private String posPag;
    private double ValorEvento;
    private double TotalorçamentoEvento;
    private double valorPos;
    private double totalValorPos;

    public EncapXlsx() {
    }

    public EncapXlsx(double codPlano, String siglaPlano, double empresa, double codAssociado, String cpf, Date dtNasc, String nomeAssociado, String proposta, double codDentista, String senhaDentista, double gto, String statusGto, String statusOrcamento, double numSeq, String evento, String descEvento, double regiao, String posPag, double valorEvento, double totalorçamentoEvento, double valorPos, double totalValorPos) {
        this.codPlano = codPlano;
        this.siglaPlano = siglaPlano;
        this.empresa = empresa;
        this.codAssociado = codAssociado;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.nomeAssociado = nomeAssociado;
        this.proposta = proposta;
        this.codDentista = codDentista;
        this.senhaDentista = senhaDentista;
        this.gto = gto;
        this.statusGto = statusGto;
        this.statusOrcamento = statusOrcamento;
        this.numSeq = numSeq;
        this.evento = evento;
        this.descEvento = descEvento;
        this.regiao = regiao;
        this.posPag = posPag;
        ValorEvento = valorEvento;
        TotalorçamentoEvento = totalorçamentoEvento;
        this.valorPos = valorPos;
        this.totalValorPos = totalValorPos;
    }

    public double getCodPlano() {
        return codPlano;
    }

    public void setCodPlano(double codPlano) {
        this.codPlano = codPlano;
    }

    public String getSiglaPlano() {
        return siglaPlano;
    }

    public void setSiglaPlano(String siglaPlano) {
        this.siglaPlano = siglaPlano;
    }

    public double getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public double getCodAssociado() {
        return codAssociado;
    }

    public void setCodAssociado(int codAssociado) {
        this.codAssociado = codAssociado;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(Date dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getNomeAssociado() {
        return nomeAssociado;
    }

    public void setNomeAssociado(String nomeAssociado) {
        this.nomeAssociado = nomeAssociado;
    }

    public String getProposta() {
        return proposta;
    }

    public void setProposta(String proposta) {
        this.proposta = proposta;
    }

    public double getCodDentista() {
        return codDentista;
    }

    public void setCodDentista(int codDentista) {
        this.codDentista = codDentista;
    }

    public String getSenhaDentista() {
        return senhaDentista;
    }

    public void setSenhaDentista(String senhaDentista) {
        this.senhaDentista = senhaDentista;
    }

    public double getGto() {
        return gto;
    }

    public void setGto(double gto) {
        this.gto = gto;
    }

    public String getStatusGto() {
        return statusGto;
    }

    public void setStatusGto(String statusGto) {
        this.statusGto = statusGto;
    }

    public String getStatusOrcamento() {
        return statusOrcamento;
    }

    public void setStatusOrcamento(String statusOrcamento) {
        this.statusOrcamento = statusOrcamento;
    }

    public double getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(int numSeq) {
        this.numSeq = numSeq;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public double getRegiao() {
        return regiao;
    }

    public void setRegiao(int regiao) {
        this.regiao = regiao;
    }

    public String getPosPag() {
        return posPag;
    }

    public void setPosPag(String posPag) {
        this.posPag = posPag;
    }

    public double getValorEvento() {
        return ValorEvento;
    }

    public void setValorEvento(double valorEvento) {
        ValorEvento = valorEvento;
    }

    public double getTotalorçamentoEvento() {
        return TotalorçamentoEvento;
    }

    public void setTotalorçamentoEvento(double totalorçamentoEvento) {
        TotalorçamentoEvento = totalorçamentoEvento;
    }

    public double getValorPos() {
        return valorPos;
    }

    public void setValorPos(double valorPos) {
        this.valorPos = valorPos;
    }

    public double getTotalValorPos() {
        return totalValorPos;
    }

    public void setTotalValorPos(double totalValorPos) {
        this.totalValorPos = totalValorPos;
    }
}
