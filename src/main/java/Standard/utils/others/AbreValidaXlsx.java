package Standard.utils.others;

import Standard.inspect.Inspecionador;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AbreValidaXlsx {

    public static final String FILE_NAME = "target\\evidencias\\arquivos\\MassaFrontOrçamento.xlsx";

    public void AbreExcel() throws IOException {
        try {
            FileInputStream arquivoXlsx = new FileInputStream(new File(AbreValidaXlsx.FILE_NAME));
            XSSFWorkbook workbook = new XSSFWorkbook(arquivoXlsx);

            Sheet sheet = workbook.getSheetAt(0);

            XSSFRow row = (XSSFRow) sheet.getRow(1);

            row.getCell(1).getNumericCellValue();

            arquivoXlsx.close();
        } catch (FileNotFoundException e) {
            Inspecionador.TipoTeste("erro", "Arquivo não encontrado: " + e, "meio");
        }

    }


}
