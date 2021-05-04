package Standard.utils.readers;

import Standard.utils.exceptions.ExceptionUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    public static String getProperty(String value) {

        Properties properties = null;
        InputStream inputStream;

        try {
            inputStream = new FileInputStream("./src/main/resources/properties/config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        if (properties == null) {
            throw new AssertionError();
        }
        return properties.getProperty(value).trim();
    }

    public static String getSysProperty(String value) {

        Properties properties = null;
        InputStream inputStream;

        try {
            inputStream = new FileInputStream("./src/main/resources/properties/config.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        if (properties == null) {
            throw new AssertionError();
        }
        return properties.getProperty(value).trim();
    }

}
