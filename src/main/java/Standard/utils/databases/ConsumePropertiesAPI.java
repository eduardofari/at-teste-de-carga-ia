package Standard.utils.databases;

import Standard.enums.PropertiesEnum;
import Standard.inspect.PortaisSis;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static Standard.enums.PropertiesEnum.URL;

public class ConsumePropertiesAPI {

    private org.springframework.web.client.RestTemplate restTemplate;
    private Base64 decoder;

    public ConsumePropertiesAPI() {
        this.restTemplate = new RestTemplateBuilder()
                .rootUri("http://172.16.2.71:8500/v1/kv")
                .build();
        this.decoder = new Base64();
    }

    /**
     * Metodo para quando serão diversas Urls a serem acessadas;
     *
     * @author Thiago de Moraes
     */
    public String getPropertiesURL() {
        String tipoS_P = PortaisSis.getTipo();
        String response = this.restTemplate.getForObject(tipoS_P, String.class);

        JSONArray array = new JSONArray(response);
        JSONObject object = array.getJSONObject(0);

        String value = object.getString("Value");
        return decodeBase64Return(value);
    }

    public static void main(String[] args) {
        ConsumePropertiesAPI api = new ConsumePropertiesAPI();
        System.out.println(api.getPropertieValue(URL));
    }

    public String getPropertieValue(PropertiesEnum propertiesEnum) {
        String urlPath = propertiesEnum.getUrlPath();
        String response = this.restTemplate.getForObject(urlPath, String.class);

        JSONArray array = new JSONArray(response);
        JSONObject object = array.getJSONObject(0);

        String value = object.getString("Value");
        return decodeBase64Return(value);
    }

    private String decodeBase64Return(String value) {
        byte[] decodedBytes = decoder.decode(value);
        return new String(decodedBytes);
    }
}
