package Standard.utils.exceptions;

import org.apache.log4j.Logger;

public class ExceptionUtils {

    private static final Logger logger = Logger.getLogger(ExceptionUtils.class);

    public static void throwException(Exception exception) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(exception.getMessage()).append("\n");
        for (StackTraceElement trace : exception.getStackTrace()) {
            stringBuilder.append(trace.toString()).append("\n");
        }

        logger.error(stringBuilder.toString());
        throw new ValidateException(stringBuilder.toString());
    }

}
