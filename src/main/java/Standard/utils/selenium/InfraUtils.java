package Standard.utils.selenium;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.io.FileUtils;

public class InfraUtils {

	/**
	 * Este_metodo_retorna o_ip da_maquina de_execucao do_teste
	 */
	public static String getIpClient() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	/**
	 * _Este _etodo_retorna o_nome do_computador
	 */
	public static String getPcName() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este_metodo o_usuario_logado na_mequina_de_execucao do_teste
	 */
	public static String getWindowsUser() {
		return System.getProperty("user.name");
	}

	public static void newFolder(String path) {
		File diretorio = new File(path);
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

	}

	public static InputStream getInputStream(String link) {
		URL url = null;
		InputStream in = null;
		try {
			url = new URL(link);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			in = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return in;
	}

	/**
	 * Este_método_aciona_um_arquivo .bat
	 */
	public static void acionarBat(String path, String nomeBat) {
		ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start", nomeBat);
		File dir = new File(path);
		pb.directory(dir);
		Process p = null;
		try {
			p = pb.start();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.destroy();
	}

	/**
	 * Este_método_copia_um_arquivo_de_origem_para_destino
	 * @param origem
	 * @param destino
	 */
	public static void copyFile(String pathWithExtensionFileOrigem, String pathWithExtensionFileDetino) {
		try {
			FileUtils.copyFile(new File(pathWithExtensionFileOrigem), new File(pathWithExtensionFileDetino));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Este_método_verifica se_existe um_arquivo_passando o_caminho com_nome do arquivo_e_extensão
	 */
	public static boolean isFile(String path) {
		return new File(path).exists();
	}
	
	public static void copyFiles(InputStream in, String path) {
		try {
			Files.copy(in, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
