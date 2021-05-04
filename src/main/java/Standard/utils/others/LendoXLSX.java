package Standard.utils.others;

import Standard.inspect.Inspecionador;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LendoXLSX extends Thread {

    private final String PATH_FILE_EXCEL = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\LAYOUT_EMPRESA_INFORMACOES.xlsx";
    public String rzSocial;
    public String nmFantasia;
    public String codCNPJ;
    public XSSFRow numLinha;
    /*public String codEmps;*/
    private List<String> codEmp;

    @SuppressWarnings("resource")
    public List<String> getCodEmp() {

        try {

            XSSFWorkbook wb = new XSSFWorkbook(PATH_FILE_EXCEL);
            Sheet sheet = wb.getSheetAt(4);
            Iterator<?> linhas = sheet.rowIterator();
            while (linhas.hasNext()) {
                XSSFRow linha = (XSSFRow) linhas.next();
                Iterator<?> celulas = linha.cellIterator();
                while (celulas.hasNext()) {
                    XSSFCell celula = (XSSFCell) celulas.next();
                    int coluna = celula.getColumnIndex();
                    Thread.sleep(90);
                    List<String> listColuna = new ArrayList<String>();

                    switch (coluna) {

                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            System.out.println(celula.getStringCellValue());
                            listColuna.add((celula.getStringCellValue()));
                            codEmp = listColuna;
                            break;
                        case 4:
                            System.out.println(celula.getStringCellValue());
                            listColuna.add(celula.getStringCellValue());
                            codEmp = listColuna;

                            System.out.println("\n");
                            break;

                    }
                    return listColuna;

                }
            }
        } catch (Exception e) {
            System.out.println("Saindo");
            e.printStackTrace();
        }
        System.out.println("Voltando");
        return codEmp;
    }

/*	public static void main(String[] asdf){
		LendoXLSX l = new LendoXLSX();
		l.start();
	}*/
}