package Standard.utils.others;

import Standard.inspect.Inspecionador;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GerarArquivoCSV {

    public static void main(String[] args) throws IOException {
    }

    String ultimaGto;

    public void gerarExcreverLerArquivoCSV(String gto) {
        File arquivo = new File("gto.csv");
        try {
            if (!arquivo.exists()) {
                //cria um arquivo (vazio)
                arquivo.createNewFile();
            }
            //escreve no arquivo
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gto);
            bw.newLine();
            bw.close();
            fw.close();
            /*//faz a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            //enquanto houver mais linhas
            while (br.ready()) {
                //lê a proxima linha
                String linha = br.readLine();
                //faz algo com a linha
                System.out.println(linha);
            }
            br.close();
            fr.close();*/
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getUltimaGto() {
        return this.ultimaGto;
    }

    public void setUltimaGto(String ultimaGto) {
        this.ultimaGto = ultimaGto;
    }

    public void pegarUmelementoCSV() throws IOException {
        try {
            File arquivo = new File("gto.csv");
            //faz a leitura do arquivo
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            //enquanto houver mais linhas
            while (br.ready()) {
                //lê a proxima linha
                String linha = br.readLine();
                //faz algo com a linha
                ultimaGto = linha;
                setUltimaGto(this.ultimaGto);
                System.out.println(ultimaGto);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            Inspecionador.TipoTeste("erro", "Não foi possível pegar uma GTO: " + e, "final");
        }
    }

    public void arquivoCsvDentistaSenhaProtocolo(String gto, String dentista, String senhaProt) {
        File arquivo = new File("Dados Dentista e Senha Protocolo.csv");
        try {
            if (!arquivo.exists()) {
                //cria um arquivo (vazio)
                arquivo.createNewFile();
            }
            List<String[]> linhas = new ArrayList<>();
            linhas.add(new String[]{gto,dentista, senhaProt});
            //escreve no arquivo
            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            CSVWriter csvWriter = new CSVWriter(bw);
            csvWriter.writeAll(linhas);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void GerarArquivo() throws IOException {

        String[] cabecalho = {"gto"};

        List<String[]> linhas = new ArrayList<>();
        linhas.add(new String[]{""});
        //Colocar um If pra verificar se o arquivo ja existe, inserir uma nova linha

            /*linhas.add(new String[]{"Maria","23","maria@dicasdeprogramacao.com.br"});
            linhas.add(new String[]{"Ana","25","ana@dicasdejava.com.br"});*/

        Writer writer = Files.newBufferedWriter(Paths.get("gto.csv"));
        CSVWriter csvWriter = new CSVWriter(writer);

        csvWriter.writeNext(cabecalho);
        //csvWriter.writeAll(linhas);

        csvWriter.flush();
        writer.close();

    }

    public void EscreveArquivoCSV(String gto) throws IOException {
        try {
            File file = new File("gto.csv");
            FileWriter fw = new FileWriter("gto.csv", true);
            BufferedWriter bw = new BufferedWriter(fw);

            if (file.length() == 0) {
                bw.newLine();
            }
            bw.write(gto);

            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Deu Erro");
        }
    }

}
