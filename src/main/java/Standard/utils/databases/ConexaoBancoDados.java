package Standard.utils.databases;

import Standard.factory.ConnectionFactory;
import Standard.utils.readers.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class ConexaoBancoDados {

    static String USER = Config.getProperty("oracle.username.hml");
    static String PASSWORD = Config.getProperty("oracle.password.hml");
    static String PORT = Config.getProperty("oracle.portnumber.hml");
    static String SERVICENAME = Config.getProperty("oracle.servername.hml");
    static String SERVER = Config.getProperty("oracle.servico.hml");

    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    String cdAssociado;
    String cdEmpresa;
    String dadoDap;

    private ConnectionFactory connectionFactory = new ConnectionFactory();
    private ConsumePropertiesAPI consumePropertiesAPI;
    private String ambiente;

    public static String dentistaGTO = "SELECT * FROM TBOD_CIR_DENTISTA dent INNER JOIN TBOD_FICHA fc ON dent.CD_CIR_DENTISTA = fc.CD_CIR_DENTISTA and ROWNUM < 1001";
    public static String sqlAssociadoGto = "select * from tbod_associado a inner join tbod_ficha fc on a.cd_associado = fc.cd_associado WHERE a.ID_INATIVO = 'N' and ROWNUM < 1001";
    public static String sqlGTO = "SELECT * FROM TBOD_FICHA WHERE CD_ETAPA_ATUAL = 2 AND TO_DATE(DT_REALIZ_ETAPA, 'mm/dd/yyyy') BETWEEN TO_DATE('01/01/2020', 'mm/dd/yyyy') AND TO_DATE('12/31/2020', 'mm/dd/yyyy') and ROWNUM < 1001";
    //public static String sqlGTO = "SELECT * FROM TBOD_FICHA WHERE CD_ETAPA_ATUAL = 2 AND TO_DATE(DT_REALIZ_ETAPA, 'dd/mm/yyyy') BETWEEN TO_DATE('01/01/2020', 'dd/mm/yyyy') AND TO_DATE('31/12/2020', 'dd/mm/yyyy') and ROWNUM < 1001";
    //public static String sqlGTO = "SELECT * FROM TBOD_FICHA WHERE CD_ETAPA_ATUAL = 2 AND TO_DATE(DT_REALIZ_ETAPA, 'dd/mm/yyyy') BETWEEN TO_DATE('01/01/2020', 'dd/mm/yyyy') AND TO_DATE('31/12/2020', 'dd/mm/yyyy') and ROWNUM < 1001";
    public static String BDliberacaoDeSenhaDAP = "SELECT DISTINCT T2.CD_CIR_DENTISTA,T2.SENHA, (SELECT T1.CD_ASSOCIADO FROM TBIA_TEMP_AUTOMACAO_PRE T1 WHERE T1.CD_CIR_DENTISTA = T2.CD_CIR_DENTISTA AND ROWNUM <= 1) CD_ASSOCIADO,(SELECT T1.CD_EVENTO FROM TBIA_TEMP_AUTOMACAO_PRE T1 WHERE T1.CD_CIR_DENTISTA = T2.CD_CIR_DENTISTA AND T1.CD_ASSOCIADO = T2.CD_ASSOCIADO AND T1.CD_EVENTO = T2.CD_EVENTO AND ROWNUM <=1) CD_EVENTO, T2.ID_PROTOCOLO FROM TBIA_TEMP_AUTOMACAO_PRE T2 WHERE ROWNUM <= 10000";

    String dado;
    String dadoSenha;

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Conectando ao banco de dados");
            con = DriverManager.getConnection("jdbc:oracle:thin:@" + SERVICENAME + ":" + PORT + ":" + SERVER, USER, PASSWORD);
            System.out.println("Conexao realizada com sucesso!");
            return con;
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public void connection(String sql) throws SQLException {
        getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
    }

    public void consultaNumeroAssociado(String sql) throws SQLException {
        List<String> listaTabelaBd = new ArrayList<String>();
        Random rdAssociado = new Random();
        try {
            getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listaTabelaBd.add(rs.getString("CD_ASSOCIADO"));
                //System.out.println("CD Associado: "+listaAssociados);
            }

            for (int i = 0; i < 1; i++) {
                this.cdAssociado = listaTabelaBd.get(rdAssociado.nextInt(listaTabelaBd.size()));
                System.out.println("Número do Associado: " + this.cdAssociado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fecharConexaoBancoOracle();
    }

    public void consultaBD(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {
            /*getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();*/

            while (rs.next()) {
                listaTabelaBd.add(rs.getString(coluna));
                //System.out.println("Lista: " + coluna);
            }

            for (int i = 0; i < 2; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
                System.out.println("Dado Ramdom " + dadoRandom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //fecharConexaoBancoOracle();
    }

    public void consultaNumeroEmpresa(String sql) throws SQLException {
        List<String> listaTabelaBd = new ArrayList<String>();
        Random rdAssociado = new Random();
        try {
            getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listaTabelaBd.add(rs.getString("CD_EMPRESA"));
            }

            for (int i = 0; i < 1; i++) {
                this.cdEmpresa = listaTabelaBd.get(rdAssociado.nextInt(listaTabelaBd.size()));
                System.out.println("Número da empresa: " + this.cdEmpresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fecharConexaoBancoOracle();
    }

    public String getDentista() throws SQLException {
        consultaBD("CD_CIR_DENTISTA");
        return this.dado;
    }

    public String getQueryDap(String pesqDadosDap) {
        if (pesqDadosDap.equalsIgnoreCase("queryDentista")) {
            consultaBDDentista("CD_CIR_DENTISTA");

            System.out.println("Print getQueryDap dentista: " + this.dado);

        }else if(pesqDadosDap.equalsIgnoreCase("querySenha")) {
            consultaBDSenha("SENHA");

            System.out.println("Print getQueryDap senha: " + this.dado);

        }else if(pesqDadosDap.equalsIgnoreCase("queryAssociado")) {
            consultaBD("CD_ASSOCIADO");

        }else if(pesqDadosDap.equalsIgnoreCase("queryEvento")) {
            consultaBD("CD_EVENTO");

        }else if(pesqDadosDap.equalsIgnoreCase("queryProtocolo")) {
            consultaBD("ID_PROTOCOLO");

        }
        return this.dado;
    }

    /*public String getDentistaLogin(String pesquiCd_cir_dentista) throws SQLException {
        consultaBD(BDliberacaoDeSenhaDAP, "CD_CIR_DENTISTA"*//*, "SENHA"*//*);

        return this.dado;
    }

    public String getSenha(String pesquiSenhaDAP) throws SQLException {
        consultaBD(BDliberacaoDeSenhaDAP, "SENHA");
        return this.dado;
    }*/
    public String getAssociado() throws SQLException {
        consultaBD(/*dentistaGTO, */"CD_ASSOCIADO");
        return this.dado;
    }
    public String getEvento() throws SQLException {
        consultaBD(/*dentistaGTO, */"CD_EVENTO");
        return this.dado;
    }

    public String getProtocolo() throws SQLException {
        consultaBD(/*dentistaGTO, */"ID_PROTOCOLO");
        return this.dado;
    }
    public String getGto(String pesqGto) throws SQLException {
        if (pesqGto.equalsIgnoreCase("dentistaGTO")) {
            consultaBD(/*dentistaGTO, */"NR_FICHA");
        } else if (pesqGto.equalsIgnoreCase("sqlGTO")) {
            consultaBD(/*sqlGTO, */"NR_FICHA");
        }
        return this.dado;
    }

    public String getGtoAtual() throws SQLException {
        consultaBD(/*sqlGTO, */"NR_FICHA");
        return this.dado;
    }

    public String getGtoBeneficiario() throws SQLException {
        consultaBD(/*sqlAssociadoGto, */"CD_ASSOCIADO");
        return this.dado;
    }

    public String getGtoDAP() throws SQLException {
        consultaBD(/*BDliberacaoDeSenhaDAP, */"CD_CIR_DENTISTA");
        return this.dado;
    }

    public static void fecharConexaoBancoOracle() throws SQLException {
        try {
            if (ps != null && !ps.isClosed()) {
                rs.close();
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    //*****************************************************************************************************

    public void consultaBDDentista(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {

            while (rs.next()) {
                listaTabelaBd.add(rs.getString("CD_CIR_DENTISTA"));
                //System.out.println("CD Associado: "+listaAssociados);
            }
            System.out.println("Coluna " + coluna);

            for (int i = 0; i < 1; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultaBDSenha(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {

            while (rs.next()) {
                listaTabelaBd.add(rs.getString("SENHA"));
                //System.out.println("CD Associado: "+listaAssociados);
            }
            System.out.println("Coluna " + coluna);

            for (int i = 0; i < 1; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultaBDAssociado(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {
            while (rs.next()) {
                listaTabelaBd.add(rs.getString("CD_ASSOCIADO"));
                //System.out.println("CD Associado: "+listaAssociados);
            }

            for (int i = 0; i < 1; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultaBDEvento(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {
            while (rs.next()) {
                listaTabelaBd.add(rs.getString("CD_EVENTO"));
                //System.out.println("CD Associado: "+listaAssociados);
            }

            for (int i = 0; i < 1; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void consultaBDProtocolo(String coluna){
        List<String> listaTabelaBd = new ArrayList<String>();
        Random dadoRandom = new Random();
        try {
            while (rs.next()) {
                listaTabelaBd.add(rs.getString("ID_PROTOCOLO"));
                //System.out.println("CD Associado: "+listaAssociados);
            }

            for (int i = 0; i < 1; i++) {
                this.dado = listaTabelaBd.get(dadoRandom.nextInt(listaTabelaBd.size()));
                System.out.println("Dado de " + coluna.toLowerCase() + ": " + this.dado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
