package Standard.utils.others;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelEmpresa {

    private static final String pathFileExcel = "C:\\Users\\Auditeste0231\\Desktop\\Automações\\Consulta_GTO_PreAprov_UNNA_Disque\\target\\evidencias\\arquivos\\Massa Front Orçamento.xlsx";
    private Iterator<Row> rowIterator;
    private File fileExcel = new File(pathFileExcel);

    @SuppressWarnings("unused")
    public List<String> getCodEvento() {

        try {
            int numLinha = 0;
            FileInputStream fis = new FileInputStream(pathFileExcel);
            @SuppressWarnings("resource")
            LineNumberReader lineNumber = new LineNumberReader(new FileReader(fileExcel));
            List<String> listColuna = new ArrayList<String>();
            lineNumber.skip(fileExcel.length());
            numLinha = lineNumber.getLineNumber();
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet abaPlanilha = wb.getSheetAt(0);
            Row lineCell = rowIterator.next();
            for (int x = 1; x < numLinha; x++) {

                /*
                 * Cell celStatus =
                 * abaPlanilha.getRow(x).getCell(3);//.getStringCellValue();
                 */ /* if(celStatus == null){ */
                /*Cód. do Evento*/
                listColuna.add(abaPlanilha.getRow(2).getCell(0).getStringCellValue());
                /*Nome*/
                listColuna.add(abaPlanilha.getRow(2).getCell(1).getStringCellValue());
                /*Modulo de Cobertura*/
                listColuna.add(abaPlanilha.getRow(2).getCell(2).getStringCellValue());
                /*Codigo do Modulo*/
                listColuna.add(abaPlanilha.getRow(2).getCell(3).getStringCellValue());
                /*Especialidade*/
                listColuna.add(abaPlanilha.getRow(2).getCell(4).getStringCellValue());
                /*Pós Pagamento*/
                listColuna.add(abaPlanilha.getRow(2).getCell(5).getStringCellValue());
                /*Valor Pós*/
                listColuna.add(abaPlanilha.getRow(2).getCell(6).getStringCellValue());

                return listColuna;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}