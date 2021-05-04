package Standard.utils.others;

import Standard.utils.exceptions.ExceptionUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class InfraUtils {

    public static String getIpClient() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        return null;
    }

    public static String getPcName() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        return null;
    }

    public static String getWindowsUser() {
        return System.getProperty("user.name");
    }

    public static void copyFile(String pathWithExtensionFileOrigem, String pathWithExtensionFileDetino) {
        try {
            FileUtils.copyFile(new File(pathWithExtensionFileOrigem), new File(pathWithExtensionFileDetino));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static boolean isFile(String path) {
        return new File(path).exists();
    }

}
