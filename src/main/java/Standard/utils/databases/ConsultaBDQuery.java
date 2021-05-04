package Standard.utils.databases;

import Standard.factory.ConnectionFactory;
import Standard.utils.exceptions.ValidateException;
import Standard.utils.readers.SearchFile;
import Standard.utils.readers.TxtReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ConsultaBDQuery {

    private static void printUserEscolhido(JsonObject user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);
    }

    public static void main(String[] args) {
        Map<String, String> massa = GenericMassa("teste");
        //new String[]{"Clínico Geral"});

        String ficha = massa.get("NR_FICHA");
        String plano = massa.get("CD_PLANO_ATUAL");
        System.out.println(ficha + " ");
    }

    public static Map<String, String> GenericMassa(String sql, String... value) {
        System.out.println("Value: " + Arrays.toString(value));
        return getUser(sql, value);
    }

    private static Map<String, String> getUser(String sql, String... value) {
        System.out.println("Value: " + Arrays.toString(value));
        JsonObject massaObj = getMassa(sql, value);
        Set<String> colunas = massaObj.keySet();
        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, massaObj.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }
        printUserEscolhido(massaObj);
        return resultado;
    }

    public static JsonArray select(String sqlName, String... values) throws SQLException {
        JsonArray tabela = new JsonArray();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String query = TxtReader.fileInLine(SearchFile.getAbsolutePath(sqlName));
            System.out.println(Arrays.toString(values));
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 1; i <= values.length; i++) {
                System.out.println(i + " " + values[i - 1]);
                preparedStatement.setString(i, values[i - 1]);
                i++;
                System.out.println(i + " " + values[i - 1]);
                preparedStatement.setString(i, values[i - 1]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
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
        } catch (SQLException exception) {
            throw new ValidateException(exception.getMessage());
        }
        return tabela;
    }

    public static JsonObject getMassa(String nomeFileSql, String... value) {
        try {
            JsonArray array;
            JsonObject object;
            System.out.println("Value: " + Arrays.toString(value));
            System.out.println("Value: " + (value.length));
            array = select(nomeFileSql, value);
            if (array.size() > 0) {
                object = array.get(0).getAsJsonObject();
            } else {
                throw new ValidateException("Nenhuma massa ORACLE encontrada");
            }
            return object;
        } catch (SQLException e) {
            throw new ValidateException(e.getMessage());
        }
    }
}