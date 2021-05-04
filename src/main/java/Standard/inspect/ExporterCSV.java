package Standard.inspect;

import Standard.utils.databases.DateTimeUtils;
import Standard.utils.readers.Config;
import Standard.utils.readers.TxtReader;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ExporterCSV {

    public static String oracleCSV() throws Exception {
        System.setProperty("line.separator", ";");
        System.setProperty("ambiente.teste", "prod");
        String ambiente = System.getProperty("ambiente.teste");
        String NomeArquivo = "";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String server = Config.getProperty("oracle.servername." + ambiente);
            String port = Config.getProperty("oracle.portnumber." + ambiente);
            String service = Config.getProperty("oracle.servico." + ambiente);
            String user = Config.getProperty("oracle.username." + ambiente);
            String pwd = Config.getProperty("oracle.password." + ambiente);
            String relativePath = ("src/test/resources/sql/crm/queryNPS.sql");
            String query = TxtReader.fileInLine(relativePath);
            String date = LocalDate.now().toString();
            String time = LocalTime.now().toString();
            date = DateTimeUtils.formataData(date, "").replace("/", ".");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + server + ":" + port + "/" + service,
                    user, pwd);
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();


            date = DateTimeUtils.formataData(date, "").replace("/", ".");
            time = DateTimeUtils.formataData(date, "").replace("/", ".");
            String hora = time.substring(0, 2);
            String minutos = time.substring(3, 5);
            String segundos = time.substring(6, 8);
            time = hora + "h" + minutos + "m" + segundos + "s";


            NomeArquivo = date + "_" + time + " - " + "EnvioNPS.csv";

            //NomeArquivo = "03.12.2019 - EnvioNPS.csv";


            BufferedWriter bwOutFile = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("./target/" + NomeArquivo), StandardCharsets.UTF_8));
            bwOutFile.write('\ufeff');

            StringBuffer sbOutput = new StringBuffer();

            System.out.println(System.getProperty("file.encoding"));
            /* COLUNAS FIXAS DO CSV */
            sbOutput.append("ID;NOME COMPLETO;CODIGO DE AREA;TELEFONE;DATA;NOME;TIPO TELEFONE;");
            bwOutFile.append(sbOutput);
            bwOutFile.append(String.format("%n"));
            System.out.println("No of columns in the table:" + rsmd.getColumnCount());
            /*
             * COLUNAS DO SQL - NÃO UTILIZADO for (int i = 1; i <= rsmd.getColumnCount();
             * i++) { String fname = rsmd.getColumnName(i); }
             *
             * System.out.println();
             */
            while (rs.next()) {
                for (int i = 1; i < 8; i++) {
                    System.out.print(rs.getString(i));
                    bwOutFile.append(rs.getString(i));
                    bwOutFile.append(System.getProperty("line.separator"));

                }
                sbOutput.append("CELULAR");
                bwOutFile.append(String.format("%n"));
                bwOutFile.flush();
                System.out.println();
            }
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unable to connect to database" + e);
        }

        return NomeArquivo;

    }
}

