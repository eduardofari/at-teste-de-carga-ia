package Standard.utils.databases;

import Standard.inspect.FileUtils;
import Standard.utils.exceptions.ExceptionUtils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabaseFactory {

    public static Connection getConnection(String database, String ambiente) {

        System.setProperty("teste", "local");

        ambiente = ambiente.trim();
        String teste = System.getProperty("teste");
        if (teste.equals("local")) {

            // Teste em maquina local

            String serverName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS,
                    database + ".servername." + ambiente);
            String portNumber = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS,
                    database + ".portnumber." + ambiente);
            String servico = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS, database + ".servico." + ambiente);
            String userName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS,
                    database + ".username." + ambiente);
            String password = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS,
                    database + ".password." + ambiente);
            String classForName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS,
                    database + ".classforname." + ambiente);
            String url = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_TESTS, database + ".url." + ambiente);

            return getConnection(serverName, portNumber, servico, userName, password, classForName, url);
        } else {

            String serverName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".servername." + ambiente);
            String portNumber = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".portnumber." + ambiente);
            String servico = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".servico." + ambiente);
            String userName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".username." + ambiente);
            String password = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".password." + ambiente);
            String classForName = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE,
                    database + ".classforname." + ambiente);
            String url = FileUtils.getProperty(FileUtils.PATH_PROPERTIES_DATABASE, database + ".url." + ambiente);

            return getConnection(serverName, portNumber, servico, userName, password, classForName, url);
        }
    }

    private static Connection getConnection(String serverName, String portNumber, String servico, String userName, String password, String classForName, String url) {

        url = String.format(url, serverName, portNumber, servico);
        Connection connection = null;
        try {
            Class.forName(classForName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }

        return connection;
    }
}
