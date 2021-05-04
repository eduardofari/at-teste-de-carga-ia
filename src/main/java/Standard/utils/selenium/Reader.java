package Standard.utils.selenium;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	public static String lerArquivo(String path) {
		StringBuilder sb = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			while(reader.ready()) {
				sb.append(reader.readLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
}
