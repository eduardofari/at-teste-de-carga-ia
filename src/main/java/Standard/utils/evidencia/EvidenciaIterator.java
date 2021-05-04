package Standard.utils.evidencia;

import Standard.utils.databases.DateTimeUtils;
import Standard.utils.exceptions.ExceptionUtils;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalTime;

public class EvidenciaIterator {

    private static String teste;


    public static void setPathToSaveEvidence(String path) {
        try {
            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString();

            date = DateTimeUtils.formataData(date, "").replace("/", "");
            String hora = time.substring(0, 2);
            String minutos = time.substring(3, 5);
            String segundos = time.substring(6, 8);

            time = hora + minutos + segundos;

            File diretorio = new File(path);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            Evidencia.setNomeEvidencia(
                    Evidencia.getNomeCt() + "_" + System.getProperty("ambiente.teste") + "_" + date + time + ".pdf");

            Evidencia.setCaminho(path);

            PdfWriter.getInstance(Evidencia.getDocument(),
                    new FileOutputStream(Evidencia.getCaminho() + Evidencia.getNome()));
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
    }

    public static void finishEvidencia(boolean falha) {

        Evidencia.getDocument().close();
        String novoNome = Evidencia.getNome().replace(".pdf", "");

        if (!falha) {
            setTeste("PASSOU");
            novoNome = novoNome + "__PASSOU.pdf";

        } else {
            setTeste("FALHOU");
            novoNome = novoNome + "__FALHOU.pdf";
        }

        new File(Evidencia.getCaminho() + Evidencia.getNome()).renameTo(new File(Evidencia.getCaminho() + novoNome));
        Evidencia.setNomeEvidencia(novoNome);

    }

    public static String getTeste() {
        return teste;
    }

    public static void setTeste(String teste) {
        EvidenciaIterator.teste = teste;
    }

}
