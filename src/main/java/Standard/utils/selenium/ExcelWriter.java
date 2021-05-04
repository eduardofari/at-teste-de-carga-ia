/*
package Standard.utils.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

*/
/**
 * Grava_dados_Excel  * Biblioteca_do Apache POI.
 *//*

public class ExcelWriter {

	public static void Excel(String nameDataFile, String sheetName, List<String> passo_1, List<String> passo_2,
			List<String> passo_3, List<String> passo_4, List<String> passo_5, List<String> PASSED1,
			List<String> PASSED2, List<String> PASSED3, List<String> PASSED4, List<String> PASSED5, 
			List<String> FAILED1,List<String> FAILED2,List<String> FAILED3,List<String> FAILED4,
			List<String> FAILED5)
			throws Exception {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(sheetName);

		
		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(font);

		Row rowHeader = sheet.createRow(0);

		Cell cellHeaderCasos = rowHeader.createCell(1);
		cellHeaderCasos.setCellType(Cell.CELL_TYPE_STRING);
		cellHeaderCasos.setCellValue("CASOS DE TESTE");
		cellHeaderCasos.setCellStyle(style);

		Cell cellHeaderPF = rowHeader.createCell(2);
		cellHeaderPF.setCellType(Cell.CELL_TYPE_STRING);
		cellHeaderPF.setCellValue("PASSED");
		cellHeaderPF.setCellStyle(style);

		Cell cellHeaderOK = rowHeader.createCell(3);
		cellHeaderOK.setCellType(Cell.CELL_TYPE_STRING);
		cellHeaderOK.setCellValue("FAILED");
		cellHeaderOK.setCellStyle(style);

		Object[][] dataPassos = { { passo_1.toString(), PASSED1.toString(), FAILED1.toString() },
								  { passo_2.toString(), PASSED2.toString(), FAILED2.toString() },
								  { passo_3.toString(), PASSED3.toString(), FAILED3.toString() },
								  { passo_4.toString(), PASSED4.toString(), FAILED4.toString() },
								  { passo_5.toString(), PASSED5.toString(), FAILED5.toString() }, };	

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

		for (Object[] aPassos : dataPassos) {
			Row row = sheet.createRow(++rowCount);

			int columnCount = 0;

			for (Object field : aPassos) {
				Cell cell = row.createCell(++columnCount);
				if (field instanceof String) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cell.setCellValue((String) field);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}

		}

		try (FileOutputStream outputStream = new FileOutputStream(nameDataFile)) {
			workbook.write(outputStream);
		}

		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File afile = new File(
					"C:\\Users\\Jonathan\\Documents\\Projetos Jonathan\\BDD_Funcionarios\\Step_Report.xlsx");
			File bfile = new File(
					"C:\\Users\\Jonathan\\Documents\\Projetos Jonathan\\BDD_Funcionarios\\Status_Report\\Step_Report.xlsx");

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copia_o_conteúdo do_arquivo_em bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();

			// delete o_arquivo original
			afile.delete();
			BarChart.main(null);
			System.out.println("Status_Report criado e copiado com sucesso!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}*/
