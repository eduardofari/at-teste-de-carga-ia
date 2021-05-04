package Standard.enums;

public enum PropertiesEnum {

    ORACLE_PASSWORD("/connection/DCMS_CONSULTA/oracle.password." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_CLASS_FOR_NAME("/connection/DCMS_CONSULTA/oracle.classforname." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_PORT_NUMBER("/connection/DCMS_CONSULTA/oracle.portnumber." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_SERVER_NAME("/connection/DCMS_CONSULTA/oracle.servername." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_SERVICE("/connection/DCMS_CONSULTA/oracle.servico." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_URL("/connection/DCMS_CONSULTA/oracle.url." + System.getProperty("ambiente.teste").toLowerCase()),
    ORACLE_USER_NAME("/connection/DCMS_CONSULTA/oracle.username." + System.getProperty("ambiente.teste").toLowerCase()),
    URL("/properties/portais/" + System.getProperty("ambiente.teste").toLowerCase() + ".redeunna.ia");
    //URL("/properties/portais/" + System.getProperty("ambiente.teste").toLowerCase() + ".preaprovacao.dentista");

    private String urlPath;

    PropertiesEnum(String urlPath) {
        //System.out.println("urlPath:" + urlPath);
        this.urlPath = urlPath;
    }

    public String getUrlPath() {
        //System.out.println("getUrlPath: " + this.urlPath);
        return this.urlPath;
    }

}
