package Standard.inspect;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.logging.Logger;

public class FileUtils {

    public static final String PATH_PROPERTIES_TESTS = "src/main/resources/properties/config.properties";
    public static final String PATH_PROPERTIES_DATABASE = "/var/.env/config.properties";

    public FileUtils() {
        throw new UnsupportedOperationException("CLASSE UTILITÁRIA, NÃO DEVE SER INSTANCIADA");
    }

    public static String lerArquivo(String path) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                sb.append(reader.readLine());
            }
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
        return sb.toString();
    }

    public static String getProperty(String path, String value) {
        String property = "";
        try (InputStream inputStream = new FileInputStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            property = properties.getProperty(value).trim();
        } catch (Exception ex) {
            Logger.getLogger("Ocorreu um erro ao realizar a leitura da Property" + ex.getMessage());
        }
        return property;
    }

    public static void copyFiles(InputStream in, String path) {
        try {
            Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            Logger.getLogger(ex.getMessage());
        }
    }

    public static void newFolder(String path) {
        File diretorio = new File(path);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

    }

}

/*public void importFile(InputStream arquivoCarregado) throws AWTException{
		String pasta = "C:\\Users\\Auditeste0240\\Downloads";
		String nomeDoArquivo = "classificacao de eventos 056000 - 7441.xlsx";
		try {
			String caminhoArquivo = pasta + "\\" + nomeDoArquivo;
			File novoArquivo = new File(caminhoArquivo);
			FileOutputStream saida = new FileOutputStream(novoArquivo);
			copiarArquivo(arquivoCarregado, saida);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public void copiarArquivo(InputStream origem, OutputStream destino){
		int bite = 0;
		byte[] tamanhoMaximo = new byte[1024*8];
		try{
			while ((bite = origem.read(tamanhoMaximo)) >= 0){
				destino.write(tamanhoMaximo, 0, bite);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}*/