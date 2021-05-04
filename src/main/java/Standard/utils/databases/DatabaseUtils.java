package Standard.utils.databases;

import Standard.utils.exceptions.ValidateException;
import Standard.utils.services.RestTest;
import com.google.gson.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DatabaseUtils {

    private static final Logger logger = Logger.getLogger(DatabaseUtils.class);
    private GenericDao genericDao = new GenericDao();

    public static JsonObject getUserRandon(JsonArray array) {
        int posicaoAleatoria = new Random().nextInt(array.size());
        return array.get(posicaoAleatoria).getAsJsonObject();
    }

    public static JsonArray getMassaFiltrada(JsonArray array, String numeroPortal, String nomeFileSql) {
        JsonArray massaDoPortal = new JsonArray();
        try {
            for (JsonElement element : array) {
                JsonObject object = element.getAsJsonObject();
                String cdMarca = object.get("CD_MARCA").getAsString();
                if (cdMarca.equals(numeroPortal)) {
                    if (nomeFileSql.equals("PRIMEIRO ACESSO")
                            && object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
                        massaDoPortal.add(object);
                    }
                    if (!nomeFileSql.equals("PRIMEIRO ACESSO")
                            && !object.get("PASSWORD").getAsString().equals("PRIMEIRO ACESSO")) {
                        massaDoPortal.add(object);
                    }
                }
            }
        } catch (Exception e) {
            return array;
        }
        return massaDoPortal;
    }

    public static JsonObject getMassa(String nomeFileSql, String numeroPortal) {

        JsonArray array = new JsonArray();
        JsonObject object = new JsonObject();

        array = GenericDao.select(nomeFileSql, numeroPortal);

        if (array.size() > 0) {
            if (System.getProperty("ambiente.teste").equals("HML")) {
                object = array.get(0).getAsJsonObject();
            } else {
                for (int i = 0; i <= array.size(); i++) {
                    logger.info("Tentativa " + i + " de " + array.size());

                    String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
                    if (RestTest.isCrm(cdAssociado, nomeFileSql)) {
                        object = array.get(i).getAsJsonObject();
                        break;
                    } else if (i == array.size() - 2) {
                        throw new ValidateException("Nenhuma massa CRM encontrada");
                    }
                }
            }
        } else {
            throw new ValidateException("Nenhuma massa ORACLE encontrada");
        }

        return object;
    }

    private static void printUserEscolhido(JsonObject user) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);
    }

    private static Map<String, String> getUser(String nomeFileSql, String numeroPortal) {

        JsonObject firstObject = getMassa(nomeFileSql, numeroPortal);

        Set<String> colunas = firstObject.keySet();

        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }
        printUserEscolhido(firstObject);
        return resultado;
    }

    public static Map<String, String> getUserCadastrado(String numeroPortal) {
        return getUser("queryCrmConsultaStatusFupE", numeroPortal);
    }

    public static Map<String, String> getUserDentista(String numeroPortal) {
        return getUser("queryCrmDentistaCadastro", numeroPortal);
    }

    public String useH2Embedded(String database, String ambiente, String nomeFileSql) {
        if (database.equals("h2")) {
            if (nomeFileSql.contains("Cadastrado")) {
                nomeFileSql = "queryH2GetUserCadastrado";
            }
            if (nomeFileSql.contains("SemCadastro")) {
                nomeFileSql = "queryH2GetUserSemCadastro";
            }
        }
        return nomeFileSql;
    }

    private JsonObject getMassaPlano(String nomeFileSql, String empresa, String plano, String rede) {

        JsonArray array = new JsonArray();
        JsonObject object = new JsonObject();

        array = genericDao.select(nomeFileSql, empresa, plano, rede);
        System.out.println(array);
        if (array.size() > 0) {
            System.out.println(array.size());
            if (System.getProperty("ambiente.teste").equals("it2")) {
                object = array.get(0).getAsJsonObject();
            } else {
                for (int i = 0; i <= array.size(); i++) {
                    logger.info("Tentativa " + i + " de " + array.size());

                    String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
                    if (RestTest.isCrm(cdAssociado, nomeFileSql)) {
                        object = array.get(i).getAsJsonObject();
                        break;
                    } else if (i == array.size() - 2) {
                        throw new ValidateException("Nenhuma massa CRM encontrada");
                    }
                }
            }
        } else {
            throw new ValidateException("Nenhuma massa ORACLE encontrada");
        }

        return object;
    }

    public Map<String, String> getPlano(String nomeFileSql, String empresa, String plano, String rede) {

        JsonObject firstObject = getMassaPlano(nomeFileSql, empresa, plano, rede);

        Set<String> colunas = firstObject.keySet();

        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }

        printUserEscolhido(firstObject);

        return resultado;
    }
    private JsonObject getMassaDAP(String nomeFileSql, String dentista, String senha, String associado, String evento, String protocolo) {

        JsonArray array = new JsonArray();
        JsonObject object = new JsonObject();

        array = genericDao.select(nomeFileSql, dentista, senha, associado, evento, protocolo);
        System.out.println(array);
        if (array.size() > 0) {
            System.out.println(array.size());
            if (System.getProperty("ambiente.teste").equals("hml")) {
                object = array.get(0).getAsJsonObject();
            } else {
                for (int i = 0; i <= array.size(); i++) {
                    logger.info("Tentativa " + i + " de " + array.size());

                    String cdAssociado = array.get(i).getAsJsonObject().get("CD_ASSOCIADO").getAsString();
                    if (RestTest.isCrm(cdAssociado, nomeFileSql)) {
                        object = array.get(i).getAsJsonObject();
                        break;
                    } else if (i == array.size() - 2) {
                        throw new ValidateException("Nenhuma massa CRM encontrada");
                    }
                }
            }
        } else {
            throw new ValidateException("Nenhuma massa ORACLE encontrada");
        }

        return object;
    }
    public Map<String, String> getDAP(String nomeFileSql, String dentista, String senha, String associado, String evento, String protocolo) {

        JsonObject firstObject = getMassaDAP(nomeFileSql, dentista, senha, associado, evento, protocolo);

        Set<String> colunas = firstObject.keySet();

        Map<String, String> resultado = new HashMap<String, String>();
        for (String coluna : colunas) {
            try {
                resultado.put(coluna, firstObject.get(coluna).getAsString().trim());
            } catch (Exception e) {
                resultado.put(coluna, "");
            }
        }

        printUserEscolhido(firstObject);

        return resultado;
    }

    public Map<String, String> getDap_IA(String dentista, String senha, String associado, String evento, String protocolo){
        return getDAP("queryDAP", dentista, senha, associado, evento, protocolo);
    }

    public Map<String, String> getRedeCredenciada(String empresa, String plano, String rede) {
        return getPlano("queryCrmRedeCredenciada", empresa, plano, rede);
    }

    public Map<String, String> getEmpresaPlano(String numeroPortal) {
        return getUser("queryCrmEmpresaPlano", numeroPortal);
    }

    public Map<String, String> getUserSemCadastro(String numeroPortal) {
        return getUser("queryGetMassaUserSemCadastro", numeroPortal);
    }

    public Map<String, String> getUserCadastradoProd(String numeroPortal) {
        return getUser("queryCrmUserCadastradoProd", numeroPortal);
    }

    public Map<String, String> getUserPrimeiroAcesso(String numeroPortal) {
        return getUser("queryCrmUserSemCadastro", numeroPortal);
    }

    public Map<String, String> getUserComDependentes(String numeroPortal) {
        return getUser("queryCrmUserComDependentes", numeroPortal);
    }

    public Map<String, String> getUserTitular(String numeroPortal) {
        return getUser("queryCrmUserTitular", numeroPortal);
    }

    public Map<String, String> getUserComExtrato(String numeroPortal) {
        return getUser("queryCrmUserComExtrato", numeroPortal);
    }

}