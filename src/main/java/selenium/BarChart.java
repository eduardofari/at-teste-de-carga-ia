/*
package util.selenium;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

*/
/**
 * Gráfico_de_linhas.
 **//*


public class BarChart {

    public static void main(String[] args) throws Exception {
        */
/* Leia_os_dados do_gráfico_de_barras do_arquivo excel *//*

        FileInputStream chart_file_input = new FileInputStream(new File("C:\\Users\\Auditeste0278\\Documents\\__WORKSPACE\\BDD_Funcionarios_\\Status_Report\\Step_Report.xlsx"));
        */
/*
         * O objeto_XSSFWorkbook_lê o_documento_completo do Excel._Nós_vamos_manipular_isso
         * Objeto_e_escreve de_volta no disco_com_o_gráfico
         *//*

        XSSFWorkbook my_workbook = new XSSFWorkbook(chart_file_input);
        */
/* Ler_planilha de_dados do_gráfico *//*

        XSSFSheet my_sheet = my_workbook.getSheetAt(0);
        */
/* Criar_um_conjunto_de_dados_que_levará_os dados_do_gráfico *//*

        DefaultCategoryDataset my_bar_chart_dataset = new DefaultCategoryDataset();
        */
/* Temos_que_carregar_os_dados do_gráfico_de_barras_agora *//*

        */
/* Comece_iterando_pela_planilha *//*

        */
/* Criar_um_objeto Iterator *//*

        Iterator<Row> rowIterator = my_sheet.iterator();
        */
/* Fazer_um loop_pelos dados_da_planilha e_preencher o conjunto_de_dados do_gráfico de_barras *//*

        String chart_label = "a";
        Number chart_data = 0;
        while (rowIterator.hasNext()) {
            // Read Rows from Excel document
            Row row = rowIterator.next();
            // Ler_linhas do_documento do Excel
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        chart_data = cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        chart_label = cell.getStringCellValue();
                        break;
                }
            }
            */
/* Adicionar_dados ao_conjunto de_dados *//*

            */
/* Não_temos_agrupamento no_gráfico de_barras, então_os_colocamos_em_grupo_fixo *//*

            my_bar_chart_dataset.addValue(chart_data.doubleValue(), "FAILED", chart_label);
        }
        */
/* Crie_um_objeto_gráfico_lógico _com_os_dados do gráfico_coletados *//*

        JFreeChart BarChartObject = ChartFactory.createBarChart("PASSED Vs FAILED", "PASSED", "FAILED",
                my_bar_chart_dataset, PlotOrientation.VERTICAL, true, true, false);
        */
/* Dimensões_do_gráfico_de_barras *//*

        int width = 640; */
/* Largura_do_gráfico *//*

        int height = 480; */
/* Altura_do_gráfico *//*

        */
/*
         * Não_queremos criar_um_arquivo_intermediário._Então,_criamos_uma_matriz_de bytes
         * fluxo_de_saída e_fluxo de_entrada da_matriz_de bytes E nós_passamos os_dados do gráfico_diretamente
         * para_entrada_de_fluxo_através_deste
         *//*

        */
/* Escrever_gráfico_como PNG no fluxo_de_saída *//*

        ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
        ChartUtilities.writeChartAsPNG(chart_out, BarChartObject, width, height);
        */
/*
         * Agora_podemos_ler_os_dados_de byte do_fluxo_de_saída e carimbar_o_gráfico_para o Excel
         * planilha_
         *//*

        int my_picture_id = my_workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_PNG);
        */
/* fechamos_o_fluxo de_saída, já_que não_precisamos mais_disso *//*

        chart_out.close();
        */
/* Crie_o contêiner_de_desenho *//*

        XSSFDrawing drawing = my_sheet.createDrawingPatriarch();
        */
/* Crie_um_ponto de_ancoragem *//*

        ClientAnchor my_anchor = new XSSFClientAnchor();
        */
/* Definir_canto superior_esquerdo, e_podemos redimensionar_imagem adequada_a partir_daí *//*

        my_anchor.setCol1(4);
        my_anchor.setRow1(5);
        */
/* Invoque_createPicture e passe o ponto_de_ancoragem e o ID*//*

        XSSFPicture my_picture = drawing.createPicture(my_anchor, my_picture_id);
        */
/* Método_de redimensionamento_de_chamadas,_que_redimensiona a_imagem *//*

        my_picture.resize();
        */
/* Feche_o FileInputStream *//*

        chart_file_input.close();
        */
/* Gravar_alterações_na_pasta de_trabalho *//*

        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Auditeste0278\\Documents\\__WORKSPACE\\BDD_Funcionarios_\\Status_Report\\Step_Report.xlsx"));
        my_workbook.write(out);
        out.close();
    }

}
*/
