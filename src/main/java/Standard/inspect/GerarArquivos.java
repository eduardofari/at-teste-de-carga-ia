package Standard.inspect;

import Standard.utils.databases.DateTimeUtils;
import Standard.utils.evidencia.Evidencia;
import Standard.utils.exceptions.ValidateException;
import Standard.utils.readers.Config;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//import repositories.ResultsAPI;

public class GerarArquivos {
    private static final Logger logger = Logger.getLogger(GerarArquivos.class);

    public static Logger getLogger() {
        return logger;
    }

    /**
     * @param nomeAPI
     * @param tipo
     * @param message
     * @param resultado
     * @param stat
     * @param recurso
     */
    public static void gerarDoc(String nomeAPI, String tipo, String message, String resultado, int stat,
                                String recurso) {

        String time = DateTimeUtils.DataHora("hora");
        String date = DateTimeUtils.DataHora("data");

        try {
            String enviado = "Dados enviados para API: " + "\n" + message;
            String recebido = "Dados recebidos da API: " + "\n" + resultado;

            String montado = "Evidencia do teste de " + recurso + " para " + tipo.toUpperCase() + "\n" + "Status: "
                    + stat + "\n" + enviado + "\n\n" + recebido + "\n";

            new Evidencia(nomeAPI.toUpperCase(), " _ " + stat);
            Evidencia.addNewPage();
            Evidencia.addText(montado);
/*
			File jsonFile = new File(Config.getProperty("path.save.evidencias.json") + "\\" + tipo.toUpperCase() + " - "
					+ date + time + ".json");
			
			FileWriter file = new FileWriter(jsonFile);
			file.write(montado);
			file.flush();
			file.close();*/

        } catch (Exception e) {
            String erro = "Não foi possivel gerar os arquivos";
            String exceptionError = e.getMessage();
            //ResultsAPI.getMessage(exceptionError + e);
            //Evidencia.addFailedText(erro);
            //EvidenciaIterator.finishEvidencia(true);
            Inspecionador.TipoTeste("erro", exceptionError, "final");
            //throw new ValidateException(erro);
        }
    }

    /**
     * @param tipo      do arquivo
     * @param envio     dados para gerar arquivo
     * @param resultado que deve ser gerado
     * @param stat      status da API
     */
    public static void gerarDocPDF(String tipo, String envio, byte[] resultado, int stat) {

        String time = DateTimeUtils.DataHora("hora");
        String date = DateTimeUtils.DataHora("data");

        File pdfFile = new File(Config.getProperty("path.save.evidencias.pdf") + "\\" + tipo.toUpperCase() + " - "
                + date + time + ".pdf");

        try (OutputStream outStream = new FileOutputStream(pdfFile)) {
            outStream.write(resultado);
            outStream.close();

        } catch (IOException e) {
            String erro = "Não foi possivel gerar os arquivos";
            String exceptionError = e.getMessage();
            //ResultsAPI.getMessage(exceptionError + e);
            //Evidencia.addFailedText(erro);
            //EvidenciaIterator.finishEvidencia(true);
            Inspecionador.TipoTeste("erro", exceptionError, "final");
            throw new ValidateException(erro);
        }
    }

}