package Standard.utils.databases;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class Dao {

    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;
    protected JSONArray tabelaJson;

    public Dao() {
        preparedStatement = null;
        resultSet = null;
        tabelaJson = null;
    }

    protected static String mountInClausule(JSONArray array) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        Iterator<Object> iterator = array.iterator();
        while (iterator.hasNext()) {
            sb.append("? ");
            iterator.next();
            if (iterator.hasNext()) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    protected JSONArray mountJsonArrayByResultSet(ResultSet resultSet) throws SQLException {
        JSONArray tabela = new JSONArray();
        while (resultSet.next()) {
            int qtdColunas = resultSet.getMetaData().getColumnCount();
            JSONObject linha = new JSONObject();
            for (int i = 1; i <= qtdColunas; i++) {
                String chave = resultSet.getMetaData().getColumnName(i);
                String valor = resultSet.getString(i);
                if (valor == null) {
                    valor = "";
                }
                linha.put(chave, valor);
            }
            tabela.put(linha);
        }
        return tabela;
    }
}

