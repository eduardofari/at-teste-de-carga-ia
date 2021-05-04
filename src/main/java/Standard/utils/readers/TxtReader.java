package Standard.utils.readers;

import Standard.utils.exceptions.ExceptionUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtReader {

    public static List<String> fileInLines(String pathFile) {

        Path path = Paths.get(pathFile);

        List<String> linhasArquivo = null;

        try {
            linhasArquivo = Files.readAllLines(path);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }

        return linhasArquivo;
    }

    public static String fileInLine(String pathFile) {

        List<String> linhasArquivo = fileInLines(pathFile);

        StringBuilder stringBuilder = new StringBuilder();

        for (String linha : linhasArquivo) {
            stringBuilder.append(linha + " ");
        }

        return stringBuilder.toString();
    }

}
