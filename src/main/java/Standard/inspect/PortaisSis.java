package Standard.inspect;

import Standard.utils.exceptions.ValidateException;

public class PortaisSis {

    public static String portal;
    private static String sistema;
    private static String tipoSistema_Portal;

    /**
     * Acesso a mais URL no mesmo teste
     *
     * @Author Thiago de Moraes
     */
    public static void setPortal(String portal, String cenario) {
        PortaisSis.portal = "/properties/portais/" + System.getProperty("ambiente.teste").toLowerCase() + "." + portal.toLowerCase() + "." + cenario.toLowerCase();
    }

    public static String getPortal() {
        return portal.toLowerCase();
    }

    public static void setSistema(String sistema, String cenario) {
        PortaisSis.sistema = "/properties/sistemas/" + System.getProperty("ambiente.teste").toLowerCase() + "." + sistema.toLowerCase() + "." + cenario.toLowerCase();
    }

    public static void setTipo(String tipoPortal_Sistema, String nomePortal_Sistema, String cenario) {
        if (tipoPortal_Sistema.toLowerCase().equals("portal")) {
            PortaisSis.tipoSistema_Portal = "/properties/portais/" + System.getProperty("ambiente.teste").toLowerCase() + "." + nomePortal_Sistema.toLowerCase() + "." + cenario.toLowerCase();

        } else if (tipoPortal_Sistema.toLowerCase().equals("sistema")) {
            PortaisSis.tipoSistema_Portal = "/properties/sistemas/" + System.getProperty("ambiente.teste").toLowerCase() + "." + nomePortal_Sistema.toLowerCase() + "." + cenario.toLowerCase();

        } else {
            throw new ValidateException("Não foi identificado o tipo de Portal/Sistema: " + tipoPortal_Sistema.toLowerCase());
        }
    }

    public static String getTipo() {
        return tipoSistema_Portal.toLowerCase();
    }

    public static String getSistema() {
        return sistema.toLowerCase();
    }
}