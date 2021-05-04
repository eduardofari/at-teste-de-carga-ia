package Standard.inspect;

import Standard.utils.evidencia.Evidencia;
import Standard.utils.evidencia.EvidenciaIterator;
import Standard.utils.exceptions.ValidateException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

public class Inspecionador {

    private static final Logger logger = Logger.getLogger("");

    /*
     * FASE DE LOGGER. TODOS OS DISPLAYS QUE DEVEM APARECER NO CONSOLE
     */

    /**
     * @param validar  = "sucesso" ou "erro" - Se for sucesso o teste continuará,
     *                 caso contrario será finalizado como falha;
     * @param mensagem = Informação que deverá ser apresentada na
     *                 {@linkplain Evidencia} do teste;
     * @param passo    = "inicial", "meio" ou "final" - Para o "inicial" e o "meio",
     *                 o teste continurá e a evidencia será feita. Para "final" o
     *                 teste será concluido visando o campo "validar";
     * @param campo    = WebElement a ser printado na {@link Evidencia};
     * @author Thiago de Moraes
     * Classe que inspeciona o teste.
     * Verificar e evidenciar o tipo de teste executado.
     */

    public static void TipoTeste(String validar, String mensagem, String passo, WebElement... campo) {
        // MENSAGEM DE TESTE DAS CLASSES E MÉTODOS
        String nomesClasses = ClassNames();

        // INFORMATIVO A SER APRESENTADO
        String informativo = validar.toUpperCase() + " no passo: " + mensagem + ". ";

        String testErro = "Verificar: " + nomesClasses + "\n Ocorreu " + informativo;

        // FALHA PARA PASSO NÃO ENCONTRADO. REVISAR CÓDIGO
        String verificar = "Verificar teste no passo: " + passo
                + " - Não faz parte da verificação - Finalizando teste como 'Falhado'";

        boolean passoValida = "inicial".equals(passo) || "meio".equals(passo) || "final".equals(passo);
        switch (validar) {
            case "sucesso":

                if (campo != null) {

                    if ("inicial".equals(passo) || "meio".equals(passo)) {
                        Validator(informativo, 0, false);
                        Evidencia.print(mensagem, campo);

                    } else if ("final".equals(passo)) {
                        Validator(informativo, 0, false);
                        Evidencia.addPrintPassed(mensagem, campo);
                        EvidenciaIterator.finishEvidencia(false);

                    } else {
                        Validator(informativo, 0, true);
                        Evidencia.addPrintFailed(verificar + " - " + nomesClasses);
                        getMessage(verificar);
                        EvidenciaIterator.finishEvidencia(true);
                    }
                } else {
                    if ("inicial".equals(passo) || "meio".equals(passo)) {
                        Validator(informativo, 0, false);
                        Evidencia.print(mensagem);

                    } else if ("final".equals(passo)) {
                        Validator(informativo, 0, false);
                        Evidencia.addPrintPassed(mensagem);
                        EvidenciaIterator.finishEvidencia(false);
                    }
                }
                break;

            case "falha":
                if ("inicial".equals(passo) || "meio".equals(passo)) {
                    Evidencia.addPrintFailed(informativo);
                    Validator(informativo, 0, true);
                } else {
                    Evidencia.addPrintFailed(testErro);
                    Validator(informativo, 0, true);
                    throw new ValidateException(testErro);
                }
                break;
            case "erro":
                if (passoValida) {
                    Evidencia.addPrintFailed(testErro);
                    Validator(informativo, 0, true);
                    EvidenciaIterator.finishEvidencia(true);
                    throw new ValidateException(testErro);
                }
                break;

            default:
                Evidencia.addPrintFailed(informativo);
                Validator(informativo, 0, true);
                EvidenciaIterator.finishEvidencia(true);
                throw new ValidateException(mensagem);
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void getMessage(String message) {
        logger.info(message);
    }


    private static String NomeClass() {
        return Thread.currentThread().getStackTrace()[5].getClassName();
    }


    private static String NomeMethod() {
        return Thread.currentThread().getStackTrace()[5].getMethodName();
    }

    private static String ClassNames() {
        return "CLASSE: " + Thread.currentThread().getStackTrace()[5].getClassName() +
                " ==> " + "MÉTODO: " + Thread.currentThread().getStackTrace()[5].getMethodName();
    }

    public static void Validator(String msg, int stat, boolean notFuncional) {
        String namesClasses = ClassNames();
        if (stat == 0) {
            if (notFuncional) {
                logger.error(namesClasses);
                logger.error(msg);
            } else {
                logger.info(namesClasses);
                logger.info(msg);
            }
        } else {
            if (notFuncional) {
                logger.error(namesClasses);
                logger.error(msg + ": Status " + stat);
            } else {
                logger.info(namesClasses);
                logger.info(msg + ": Status " + stat);
            }
        }
    }
}
