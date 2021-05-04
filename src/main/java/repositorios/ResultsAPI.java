package repositorios;

import org.apache.log4j.Logger;

public class ResultsAPI {

    public static final Logger logger = Logger.getLogger(ResultsAPI.class);

    public static String getMessage(String message) {
        logger.info(message);
        return message;
    }

    public static int APIOk(int stat) {
        if (stat == 240) {
            logger.info("Não houve retorno da API Upload de Imagem: Status " + stat + " No Content");
        } else if (stat == 400) {
            logger.info("Não houve retorno da API Upload de Imagem: Status " + stat + " Bad Request");
        } else if (stat == 200) {
            logger.info("A API Upload de Imagem está em funcionamento: Status " + stat + "Success");
        } else {
            logger.info("A API Upload de Imagem está em funcionamento: Status " + stat + "Sem identificação");
        }

        return stat;
    }

    public static int APINok(int stat) {
        if (stat == 500) {
            logger.info("Não houve retorno da API Upload de Imagem: Status " + stat + " Internal Server Error");
        } else {
            logger.info("A API Upload de Imagem está em funcionamento: Status " + stat + "Sem identificação");
        }
        return stat;
    }

    public static int errorNOK(int stat) {
        logger.info("Ocorreu um erro não esperado,a API Upload de Imagem não está em funcionamento: Status " + stat + " Falha");
        return stat;
    }

    public static int errorNull(int stat) {
        logger.info("Não houve retorno da API Upload de Imagem: Status" + stat + " NULL");
        return stat;
    }

    public static int erroVazio(int stat) {
        logger.info("Não houve retorno da API Upload de Imagem: Status" + stat + " Campos Vazios");
        return stat;
    }

    public static void errorINIT(String api) {
        logger.info("Não foi possível iniciar a API Upload de Imagem: " + api);
    }

    public static Logger getLogger() {
        return logger;
    }
}
