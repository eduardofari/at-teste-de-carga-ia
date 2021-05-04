/*package Standard.utils.others;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ListaDeDadosBancoCSV {

        public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

            List<DadosBancoNoCSV> dadosDentista = new ArrayList<>();
            dadosDentista.add(new DadosBancoNoCSV("298422","81865758","421297340","82.000.816","4"));
            *//*pessoas.add(new DadosBancoNoCSV("298422","","","",""));
            pessoas.add(new DadosBancoNoCSV("298422","","","",""));
            pessoas.add(new DadosBancoNoCSV("298422","","","",""));
            pessoas.add(new DadosBancoNoCSV("298422","","","",""));*//*

            Writer writer = Files.newBufferedWriter(Paths.get("dadosDentista.csv"));
            StatefulBeanToCsv<DadosBancoNoCSV> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            beanToCsv.write(dadosDentista);

            writer.flush();
            writer.close();

        }

}*/
