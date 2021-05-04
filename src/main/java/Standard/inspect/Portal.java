package Standard.inspect;

import Standard.utils.evidencia.Evidencia;
import Standard.utils.exceptions.ValidateException;

public class Portal {

    private static final String ODONTOPREV = "1";
    private static final String DENTAL_CORP = "2";
    private static final String REDE_DENTAL = "3";
    private static final String SEPAO = "26";
    private static final String PRONTO_DENTE = "27";
    private static final String TOTAL_DENT = "28";
    private static final String PRIVIAN = "29";
    private static final String BRADESCO = "30";
    private static final String BRADESCO_PF = "31";
    private static final String ODONTO_SERV = "35";
    private static final String BB_DENTAL = "36";
    private static final String ODONTOSYSTEM = "39";
    private static String portalTeste;

    public static String numPortal(String portal) {
        try {
            switch (portal) {
                case "ODONTOSYSTEM":
                    portalTeste = ODONTOSYSTEM;
                    break;
                case "PRONTO_DENTE":
                    portalTeste = PRONTO_DENTE;
                    break;
                case "ODONTO_SERV":
                    portalTeste = ODONTO_SERV;
                    break;
                case "DENTAL_CORP":
                    portalTeste = DENTAL_CORP;
                    break;
                case "ODONTOPREV":
                    portalTeste = ODONTOPREV;
                    break;
                case "BRADESCO_PF":
                    portalTeste = BRADESCO_PF;
                    break;
                case "REDE_DENTAL":
                    portalTeste = REDE_DENTAL;
                    break;
                case "TOTAL_DENT":
                    portalTeste = TOTAL_DENT;
                    break;
                case "BB_DENTAL":
                    portalTeste = BB_DENTAL;
                    break;
                case "BRADESCO":
                    portalTeste = BRADESCO;
                    break;
                case "PRIVIAN":
                    portalTeste = PRIVIAN;
                    break;
                case "SEPAO":
                    portalTeste = SEPAO;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            String erro = "Portal de teste não encontrado";
            Evidencia.addFailedText(erro);
            throw new ValidateException(erro);
        }
        return portalTeste;
    }
}