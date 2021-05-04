package Standard.utils.selenium;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	/**
	 * Este_método_lê_um_arquivo_de properties e_retorna o valor_correspondente_pela_chave_informada
	 */
	public static String getProperty(String value) {
		
		Properties properties = null;
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream("src/main/resources/properties/config.properties");
			properties = new Properties();
			properties.load(inputStream); 
		} catch (Exception exception) {
			exception.printStackTrace();
		} 
				
		return properties.getProperty(value).trim();
	}
}
