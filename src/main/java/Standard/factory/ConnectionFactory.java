package Standard.factory;

import Standard.enums.PropertiesEnum;
import Standard.utils.databases.ConsumePropertiesAPI;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static ConnectionFactory singleton;
    private String ambiente;
    private String userKeyProperties;
    private ConsumePropertiesAPI consumePropertiesAPI;

    public ConnectionFactory() {
        this.consumePropertiesAPI = new ConsumePropertiesAPI();
        ambiente = System.getProperty("ambiente.teste");
        if (ambiente == null) {
            System.setProperty("ambiente.teste", "HML");
            ambiente = "HML";
        }
        ambiente = ambiente.toUpperCase();
    }

    public static ConnectionFactory getInstance() {
        if (singleton == null) {
            singleton = new ConnectionFactory();
        }
        return singleton;
    }

    /**
     * Abre uma conexão com um banco de dados Oracle
     *
     * @author Fabio Lima
     */

    public Connection getConnection() throws SQLException {
        final String SERVER = this.consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("ORACLE_SERVER_NAME" /*+ ambiente*/));
        final String SERVICENAME = this.consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("ORACLE_SERVICE" /*+ ambiente*/));
        final String PORT = this.consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("ORACLE_PORT_NUMBER" /*+ ambiente*/));
        final String USER = this.consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("ORACLE_USER_NAME" /*+ ambiente*/));
        final String PASSWORD = this.consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("ORACLE_PASSWORD" /*+ ambiente*/));

        OracleDataSource ods = new OracleDataSource();
        ods.setServerName(SERVER);
        ods.setServiceName(SERVICENAME);
        ods.setPortNumber(Integer.parseInt(PORT));
        ods.setDriverType("thin");
        ods.setUser(USER);
        ods.setPassword(PASSWORD);
        return ods.getConnection();

    }

    /**
     * Método para finalizar um PreparedStatement
     *
     * @param pst PreparedStatement a ser fechado
     * @author Fabio Lima
     */
    public void close(PreparedStatement pst) {
        try {
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }


    /**
     * Fecha um PreparedStatement e um ResultSet
     *
     * @param pst PreparedStatement a ser fechado
     * @param rs  ResultSet a ser fechado
     * @author Fabio Lima
     */
    public void close(PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            close(pst);
        } catch (SQLException ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

}