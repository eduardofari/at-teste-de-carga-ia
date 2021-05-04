package Standard.utils.databases;

import Standard.factory.ConnectionFactory;
import Standard.utils.exceptions.ExceptionUtils;
import Standard.utils.readers.SearchFile;
import Standard.utils.readers.TxtReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GenericDao {

    public static JsonArray select(String sqlName, String... values) {

        JsonArray tabela = new JsonArray();

        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String relativePath = SearchFile.getAbsolutePath(sqlName);
            String query = TxtReader.fileInLine(relativePath);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int qtdColunas = resultSet.getMetaData().getColumnCount();

                JsonObject linha = new JsonObject();
                for (int i = 1; i <= qtdColunas; i++) {
                    String chave = resultSet.getMetaData().getColumnName(i);
                    String valor = resultSet.getString(i);
                    linha.addProperty(chave, valor);
                }

                tabela.add(linha);
            }
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }

        return tabela;
    }

    public static void create(String database, String ambiente, String sqlName) {

        if (database.toLowerCase().trim().equals("oracle")) {
            throw new RuntimeException("Este método não pode ser utilizado para a base de dados do cliente");
        } else {
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                String relativePathCreate = SearchFile.getAbsolutePath(sqlName);
                String queryCreate = TxtReader.fileInLine(relativePathCreate);
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(queryCreate);
            } catch (Exception exception) {
                ExceptionUtils.throwException(exception);
            }
        }
    }

    public static void insert(String database, String ambiente, String sqlName, JsonArray massa) {

        if (database.toLowerCase().trim().equals("oracle")) {
            throw new RuntimeException("Este método não pode ser utilizado para a base de dados do cliente");
        } else {
            try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
                String relativePathInsert = SearchFile.getAbsolutePath(sqlName);
                String queryInsert = TxtReader.fileInLine(relativePathInsert);
                Statement stmt = conn.createStatement();

                for (JsonElement aux : massa) {
                    JsonObject object = aux.getAsJsonObject();

                    Set<String> keys = object.keySet();
                    List<String> colunas = new LinkedList<String>();

                    for (String key : keys) {
                        colunas.add(object.get(key).getAsString());
                    }

                    stmt.executeUpdate(String.format(queryInsert, colunas.toArray()));

                }
            } catch (Exception exception) {
                ExceptionUtils.throwException(exception);
            }
        }
    }

    public static void drop(String sqlName, String... values) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String relativePathInsert = SearchFile.getAbsolutePath(sqlName);
            String queryDrop = TxtReader.fileInLine(relativePathInsert);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate(queryDrop);

        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void update(String database, String ambiente, String sqlName, String... values) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String relativePathInsert = SearchFile.getAbsolutePath(sqlName);
            String queryUpdate = TxtReader.fileInLine(relativePathInsert);
            PreparedStatement preparedStatement = conn.prepareStatement(queryUpdate);

            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            preparedStatement.executeUpdate(queryUpdate);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void delete(String database, String ambiente, String sqlName, String... values) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String relativePathInsert = SearchFile.getAbsolutePath(sqlName);
            String queryDelete = TxtReader.fileInLine(relativePathInsert);
            PreparedStatement preparedStatement = conn.prepareStatement(queryDelete);

            for (int i = 1; i <= values.length; i++) {
                preparedStatement.setString(i, values[i - 1]);
            }

            preparedStatement.executeUpdate(queryDelete);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

}
