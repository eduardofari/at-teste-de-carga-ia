package Standard.factory;

import Standard.inspect.Inspecionador;
import Standard.inspect.PortaisSis;
import Standard.utils.databases.ConsumePropertiesAPI;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlAccess {

    public static void validarUrl(String url) throws IOException {
        int statPost = statUrlPost(url);
        int statGet = statUrlGet(url);
        if (statGet == 200) {
            Inspecionador.TipoTeste("sucesso", "Usando GET: Portal/Sistema Online - Status Code: " + statGet, "meio");
        } else if (statPost == 200) {
            Inspecionador.TipoTeste("sucesso", "Usando POST: Portal/Sistema Online - Status Code: " + statPost, "meio");
        } else {
            //Stat OK - Xpath não encontrado
            Inspecionador.TipoTeste("falha", "Portal/Sistema Offline: Status Code - " + "\nGET: " + statGet + "\nPOST: " + statPost, "final");
        }
    }

    public static int statUrlGet(String currentUrl) throws IOException {
        URL url = new URL(currentUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }

    public static int statUrlPost(String currentUrl) throws IOException {
        URL url = new URL(currentUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();
        return connection.getResponseCode();
    }

    /**
     * @param tipoPortal_Sistema Se Sistema ou se Portal a ser acessado;
     * @param nomePortal_Sistema portal ou sistema para o acesso, verificar a nomenclatura;
     * @param cenario            Teste a ser feito Dependendo do Portal/Sistema:
     *                           Associado, Empresa, Geral, Vendas, Brasil, Mexico,
     *                           Vendas, Ans, Auditoria, Dentista, Disque, Faturamento, Ebs, Intranet;
     * @author Thiago de Moraes
     **/
    public void setURL(String tipoPortal_Sistema, String nomePortal_Sistema, String cenario) {
        try {
            PortaisSis.setTipo(tipoPortal_Sistema, nomePortal_Sistema, cenario);
            ConsumePropertiesAPI consumePropertiesAPI = new ConsumePropertiesAPI();
            //String url = consumePropertiesAPI.getPropertieValue(PropertiesEnum.valueOf("URL"));

            //*---------------URL Exclusiva deste teste ---------*//*
            //String url = consumePropertiesAPI.getPropertiesURL();
            //*--------------------------------------------------*//*

            String urlRedeUnna = "https://testecarga.redeunna.com.br/dentista";
            String urlPreAprov = "https://testecarga.odontoprev.com.br/preaprovacaoauditoria";

            //String urlRedeUnna = "https://hml.redeunna.com.br/dentista/";
            //String urlPreAprov = "http://172.16.21.130/preaprovacaoauditoria/loginInterno.jsp";


            if (nomePortal_Sistema.equalsIgnoreCase("RedeUnna")) {
                System.setProperty("url.acessada", urlRedeUnna);
                Inspecionador.getMessage("Iniciando acesso ao " + tipoPortal_Sistema + " " + nomePortal_Sistema.toUpperCase() + " " + cenario.toUpperCase() + "\n" + urlRedeUnna);
                WebDriverFactory.getCurrentRunningDriver().get(urlRedeUnna);
            } else if (nomePortal_Sistema.equalsIgnoreCase("PreAprovacao")) {
                System.setProperty("url.acessada", urlPreAprov);
                Inspecionador.getMessage("Iniciando acesso ao " + tipoPortal_Sistema + " " + nomePortal_Sistema.toUpperCase() + " " + cenario.toUpperCase() + "\n" + urlPreAprov);
                WebDriverFactory.getCurrentRunningDriver().get(urlPreAprov);
            }
            Inspecionador.TipoTeste("sucesso", "Portal Online", "inicial");
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível acessar o " + tipoPortal_Sistema +
                    ": " + nomePortal_Sistema + " " + cenario + "\n " + e, "final");
        }
    }
}
