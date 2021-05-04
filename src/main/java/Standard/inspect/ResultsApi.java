package Standard.inspect;

import org.apache.log4j.Logger;

/**
 * Retorna o resultado da API
 *
 * @author Thiago de Moraes
 **/
public class ResultsApi {

    public static final Logger logger = Logger.getLogger("");


    public static int noReturn204(String nomeTeste, int stat) {
        logger.info("Não houve retorno de " + nomeTeste + ": Status " + stat + " No Content");
        return stat;
    }

    public static int erro400(String nomeTeste, int stat) {
        logger.info("Parametros errados para " + nomeTeste + ": Status " + stat + " Bad Request");
        return stat;
    }

    public static int erro500(String nomeTeste, int stat) {
        logger.info("Retorno de erro do Servidor de " + nomeTeste + ": Status " + stat + " Internal Server Error");
        return stat;
    }

    public static int errorNOK(String nomeTeste, int stat) {
        logger.info("Ocorreu um erro não esperado, " + nomeTeste + " não está em funcionamento: Status " + stat);
        return stat;
    }

    public static int errorNull(String nomeTeste, int stat) {
        logger.info("Não houve retorno de" + nomeTeste + ": Status" + stat + " NULL");
        return stat;
    }

    public static int erroVazio(String nomeTeste, int stat) {
        logger.info("Não houve retorno de" + nomeTeste + ": Status" + stat + " Campos Vazios");
        return stat;
    }

    public static void errorINIT(String nomeTeste) {
        logger.info("Não foi possivel iniciar " + nomeTeste);
    }
}
