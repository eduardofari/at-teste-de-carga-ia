package Standard.utils.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;

public class RestTest {

    public static final Logger logger = Logger.getLogger(RestTest.class);

    public static boolean isCrm(String id, String nomeFileSql) {
        boolean status;
        if (nomeFileSql.contains("queryCrmUserComExtrato")) {
            status = isCrmWithExtrato(id);
        } else {
            status = isPureCrm(id);
        }
        return status;
    }

    public static boolean isPureCrm(String id) {

        boolean status;

        String uri = "https://login.windows.net/odontoprev.onmicrosoft.com/oauth2/token";

        JsonPath result = given()
                .baseUri(uri)
                .contentType("application/x-www-form-urlencoded")
                .body("grant_type=password&client_id=4df5dbc4-6267-468c-afa8-0021de80ad9e&resource=https%3A%2F%2Fodontoprev.crm2.dynamics.com%2F&username=dcmsweb%40odontoprev.onmicrosoft.com&password=Web%40Odonto2018")
                .when()
                .post()
                .getBody()
                .jsonPath();

        String token = result.getString("access_token");

        String resultSet = given()
                .header("Authorization", "Bearer " + token)
                .baseUri("https://odontoprevqa.api.crm2.dynamics.com/api/data/v9.0/odpv_associados?$filter=odpv_name%C2%A0eq%20%27000489540%27%20")
                .contentType("application/json")
                .expect()
                .statusCode(200)
                .when()
                .get("https://odontoprevqa.api.crm2.dynamics.com/api/data/v9.0/odpv_associados?$filter=odpv_name eq '" + id + "'")
                .body()
                .jsonPath()
                .prettify();

        JsonObject object = (JsonObject) new JsonParser().parse(resultSet);
        status = object.get("value").getAsJsonArray().size() > 0 ? true : false;
        logger.info("O usuário " + id + " tem status de cadastramento no CRM como " + status);

        return status;
    }

    public static boolean isCrmWithExtrato(String id) {

        boolean status = false;

        String uri = "https://login.windows.net/odontoprev.onmicrosoft.com/oauth2/token";

        JsonPath result = given()
                .baseUri(uri)
                .contentType("application/x-www-form-urlencoded")
                .body("grant_type=password&client_id=4df5dbc4-6267-468c-afa8-0021de80ad9e&resource=https%3A%2F%2Fodontoprev.crm2.dynamics.com%2F&username=dcmsweb%40odontoprev.onmicrosoft.com&password=Web%40Odonto2018")
                .when()
                .post()
                .getBody()
                .jsonPath();

        String token = result.getString("access_token");

        String resultSet = given()
                .header("Authorization", "Bearer " + token)
                .baseUri("https://odontoprevqa.api.crm2.dynamics.com/api/data/v9.0/odpv_associados?$filter=odpv_name%C2%A0eq%20%27000489540%27%20")
                .contentType("application/json")
                .expect()
                .statusCode(200)
                .when()
                .get("https://odontoprevqa.api.crm2.dynamics.com/api/data/v9.0/odpv_associados?$filter=odpv_name eq '" + id + "'")
                .body()
                .jsonPath()
                .prettify();

        JsonObject object = (JsonObject) new JsonParser().parse(resultSet);
        JsonArray array = object.get("value").getAsJsonArray();

        String odpvAssociadoid = "";
        if (array.size() > 0) {
            odpvAssociadoid = array.get(0).getAsJsonObject().get("odpv_associadoid").getAsString();

            String resultt = given()
                    .header("Authorization", "Bearer " + token)
                    .baseUri("https://odontoprevqa.api.crm2.dynamics.com/api/data/v8.0/incidents?$filter=_odpv_carteirinhaid_value%20eq%20cdedfdbc-25aa-e811-a96b-000d3ac1b1e6")
                    .contentType("application/json")
                    .expect()
                    .statusCode(200)
                    .when()
                    .get("https://odontoprevqa.api.crm2.dynamics.com/api/data/v8.0/incidents?$filter=_odpv_carteirinhaid_value eq " + odpvAssociadoid)
                    .body()
                    .jsonPath()
                    .prettify();

            JsonObject objectt = (JsonObject) new JsonParser().parse(resultt);
            status = objectt.get("value").getAsJsonArray().size() > 0 ? true : false;
            logger.info("O usuário " + id + " tem status de cadastramento no CRM como " + status);
            return status;
        }

        logger.info("O usuário " + id + " tem status de cadastramento no CRM como " + status);
        return status;
    }

}
