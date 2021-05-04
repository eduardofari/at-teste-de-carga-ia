package Standard.utils.services;

import Standard.utils.exceptions.ValidateException;
import Standard.utils.readers.Config;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import repositorios.ResultsAPI;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class APIUploader_Imagem_IA {
    boolean status;
    public static final Logger logger = Logger.getLogger(APIUploader_Imagem_IA.class);

    public static void main(String[] args) {
        APIUploader_Imagem_IA API_Uploader = new APIUploader_Imagem_IA();
        String numGTO = "115930220";
        String dentista = "Z77204";
        String senha = "12345678";
        System.out.println("Status API:" + API_Uploader.validadorSucesso(dentista, senha, numGTO));
    }

    public static String GerarImagemBase64(String path) {
        File fileImg = new File(path);
        //System.out.println("\n Dados: " + encodeFileToBase64Binary(fileImg));
        return encodeFileToBase64Binary(fileImg);

    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            // System.out.println(fileInput);
            encodedfile = new String(Base64.getEncoder().encode(bytes), StandardCharsets.UTF_8);
            fileInputStreamReader.close();
        } catch (Exception e) {
            String erro = "Não foi possivel converter a imagem para Base 64";
            String exceptionError = e.getMessage();
            ResultsAPI.getMessage(exceptionError + e);
            throw new ValidateException(erro);
        }
        return encodedfile;
    }

    public boolean validadorSucesso(String dentista, String senha, String numGTO) {

        ResultsAPI.getMessage("Iniciando a validação de Sucesso");
        try {
            String jsonImagem = GerarImagemBase64(Config.getProperty("path.imagem"));
            JSONObject json = new JSONObject();

            json.put("data", jsonImagem);
            Thread.sleep(1000);
            json.put("ficha", numGTO);
            Thread.sleep(1000);
            json.put("usuario", dentista);
            Thread.sleep(1000);
            json.put("senha", senha);

            String envio = json.toString();

            ResultsAPI.getMessage(envio);
            this.status = validarAPI(envio);

            System.out.println("Status da API: " + status);

        } catch (Exception e) {
            String erro = "Não foi possivel Validar a API, status: " + status;
            String exceptionError = e.getMessage();
            ResultsAPI.getMessage(exceptionError + e);
            throw new ValidateException(erro);
        }
        return status;
    }

    public static boolean validarAPI(String envio) {

        boolean statusAPI;
        String urlAPI = "https://servicosh.odontoprev.com.br/dcmsweb/uploader/services/uploader/enviarImagensIntegracoes";
        Response resultSet = given().baseUri(urlAPI).body(envio).contentType("application/json").when().post();
        String resultado = resultSet.body().prettyPrint();
        int stat = resultSet.getStatusCode();

        if (resultado != null) {
            if (!"".equals(resultado)) {
                switch (stat) {
                    case 200:
                    case 204:
                    case 400:
                        statusAPI = true;
                        ResultsAPI.APIOk(stat);
                        break;
                    case 500:
                    default:
                        ResultsAPI.getMessage("API - Falha: " + stat);
                        ResultsAPI.APINok(stat);
                        statusAPI = false;
                        break;
                }
            } else {
                statusAPI = false;
                ResultsAPI.erroVazio(stat);
            }
        } else {
            statusAPI = false;
            ResultsAPI.errorNull(stat);
        }
        return statusAPI;
    }

    public static Logger getLogger() {
        return logger;
    }
}

