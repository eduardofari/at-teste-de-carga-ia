package Standard.utils.evidencia;

import Standard.utils.databases.DateTimeUtils;
import Standard.utils.others.InfraUtils;
import Standard.utils.readers.Config;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

public class Cover {

    public static void insertCover() {
        insertLogon(Config.getProperty("path.upload.logon"));
        insertObjective();
        Evidencia.addText("Data atual: " + DateTimeUtils.insertDateNow());
        Evidencia.addText("Hora atual: " + DateTimeUtils.insertTimeNow() + " - hora do Brasil");
        Evidencia.addText("Endereço IP: " + InfraUtils.getIpClient());
        Evidencia.addText("Usuário local ou de rede: " + InfraUtils.getWindowsUser());
        Evidencia.addText("Nome do computador de execução: " + InfraUtils.getPcName());
        Evidencia.addNewPage();
    }

    public static void insertObjective() {
        String text = "Evidência de teste\n\n" + Evidencia.getNomeCt().toUpperCase().replace("_", " ") + "\nAmbiente "
                + System.getProperty("ambiente.teste");
        Evidencia.addFormatedText(text, FontFactory.HELVETICA, 20, Font.BOLDITALIC, BaseColor.BLUE, 40, 200,
                Element.ALIGN_CENTER);
    }

    public static void insertLogon(String logonPath) {
        Evidencia.addExternalImage(logonPath, Element.ALIGN_JUSTIFIED, 300f, 150f);
    }

}
