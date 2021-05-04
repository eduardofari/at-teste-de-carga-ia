package Standard.utils.others;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LendoArquivoCSV {

    private String cd_cir_dentista;
    private String senha;
    private String cd_associado;
    private String cd_evento;
    private String id_protocolo;

    public static List<String[]> LerArquivo() throws IOException, CsvException {
        BufferedReader reader = Files.newBufferedReader(Paths.get("dadosDentista.csv"));
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

        return csvReader.readAll();
    }

    public static void main(String[] args) throws IOException, CsvException {
        LendoArquivoCSV lendoArquivoCSV = new LendoArquivoCSV();
        lendoArquivoCSV.LerLinhas();

    }

    public void LerLinhas() throws IOException, CsvException {

        List<String[]> linhas = LendoArquivoCSV.LerArquivo();

            //    for (String coluna : linha)
            for (int i = 0; i < 1; i++) {
                for (String[] linha : linhas) {

                //    System.out.print(coluna + " # ");
                //System.out.println();
           /*
            System.out.println("CD_DIR_DENTISTA : " + linha[0] +
                    " - SENHA : " + linha[1] +
                    " - CD_ASSOCIADO : " + linha[2] +
                    " - CD_EVENTO : " + linha[3] +
                    " - ID_PROTOCOLO : " + linha[4]);
*/
                this.cd_cir_dentista = linha[0];
                setCd_cir_dentista(this.cd_cir_dentista);

                this.senha = linha[1];
                setSenha(this.senha);

                this.id_protocolo = linha[2];
                setId_protocolo(this.id_protocolo);

                this.cd_associado = linha[3];
                setCd_associado(this.cd_associado);

                this.cd_evento = linha[4];
                setCd_evento(this.cd_evento);

                getters();

                //lendoArquivoCSV.getters();
            }
        }
    }

    public void getters() {
        String dentista = getCd_cir_dentista();
        String senha = getSenha();
        String associado = getCd_associado();
        String evento = getCd_evento();
        String protocolo = getId_protocolo();
        System.out.println(
                "Dentista: " + dentista + "\n" +
                        "Senha:" + senha + "\n" +
                        "Associado: " + associado + "\n" +
                        "Eventos: " + evento + "\n" +
                        "Protocolo: " + protocolo + "\n"
        );
    }

    public String getCd_cir_dentista() {
        return this.cd_cir_dentista;
    }

    public void setCd_cir_dentista(String cd_cir_dentista) {
        this.cd_cir_dentista = cd_cir_dentista;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCd_associado() {
        return this.cd_associado;
    }

    public void setCd_associado(String cd_associado) {
        this.cd_associado = cd_associado;
    }

    public String getCd_evento() {
        return this.cd_evento;
    }

    public void setCd_evento(String cd_evento) {
        this.cd_evento = cd_evento;
    }

    public String getId_protocolo() {
        return this.id_protocolo;
    }

    public void setId_protocolo(String id_protocolo) {
        this.id_protocolo = id_protocolo;
    }
}
