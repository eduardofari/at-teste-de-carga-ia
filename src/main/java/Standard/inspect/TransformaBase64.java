package Standard.inspect;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

public class TransformaBase64 {

    public static void main(String[] args) throws IOException {

        String nome = "Teste de Criptografia";
        String encodedBase64 = GerarBase64(nome, false);
        //  String encodedBase64 = GerarBase64String();
        System.out.println(encodedBase64);
        String view = DesCriptografia(encodedBase64);
        System.out.println(view);
        GerarOriginal(encodedBase64, nome, "jpg");
        System.out.println("Arquivo gerado");
    }

    private static String TipoArquivo(String extens) {

        String tipo = null;
        if ("jpg".equals(extens) || "png".equals(extens) || "gif".equals(extens)) {
            tipo = "Imagem";
        } else if ("pdf".equals(extens) || "txt".equals(extens)) {
            tipo = "Doc";
        } else if ("string".equals(extens)) {
            tipo = extens;
        } else {
            Inspecionador.getMessage("Não foi possível identificar o tipo do arquivo");
        }
        return tipo;
    }

    public static String DesCriptografia(String encoded) throws IOException {
        byte[] bytes = Base64.decodeBase64(encoded);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void GerarOriginal(String encoded, String NomeArquivo, String extens) {

        try {
            File filePath = new File("./src/main/resources/images/" + NomeArquivo + "_2" + "." + extens);
            File file = new File("./src/main/resources/images/" + NomeArquivo + ".");
            BufferedImage image;
            byte[] arqByte;
            arqByte = decodeBase64(encoded);
            ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(arqByte);

            String tipoArq = TipoArquivo(extens);

            switch (tipoArq.toLowerCase()) {
                case "imagem":
                    image = ImageIO.read(byteArrayIS);
                    byteArrayIS.close();
                    ImageIO.write(image, extens, file);
                    break;
                case "doc":
                    OutputStream outStream = new FileOutputStream(filePath);
                    outStream.write(arqByte);
                    outStream.close();
                    break;
                default:
                    throw new IllegalStateException("Valor inesperado: " + tipoArq.toLowerCase());
            }
        } catch (Exception e) {

            String erro = "Não foi possível converter da Base 64 pra a versão original";
            String exceptionError = e.getMessage();
            //  Inspecionador.TipoTeste("erro", exceptionError, "final");
        }
    }

    private static String GerarBase64(String stringPath, boolean arquivo) {


        String encodedfile = null;
        try {
            if (arquivo) {
                File file = new File(stringPath);
                FileInputStream fileInputStreamReader = new FileInputStream(file);
                byte[] bytes = new byte[(int) file.length()];

                fileInputStreamReader.read(bytes);
                encodedfile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
                fileInputStreamReader.close();
            } else {
                byte[] bytes = stringPath.getBytes();
                encodedfile = new String(Base64.encodeBase64(bytes), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {

            String erro = "Não foi possível converter a imagem para Base 64";
            String exceptionError = e.getMessage();
            Inspecionador.TipoTeste("erro", exceptionError, "final");
        }
        return encodedfile;
    }

    private static String GerarBase64String(String str) {

        String encodedfile = null;
        try {

            byte[] bytes = str.getBytes();
            encodedfile = new String(Base64.encodeBase64(bytes));

        } catch (Exception e) {

            String erro = "Não foi possível converter a imagem para Base 64";
            String exceptionError = e.getMessage();
            Inspecionador.TipoTeste("erro", exceptionError, "final");
        }
        return encodedfile;
    }
}
