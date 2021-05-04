package Standard.utils.databases;

import Standard.utils.exceptions.ExceptionUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static String formataData(String data) {
        return formataData(data, "");
    }

    public static String formataData(String data, String pattern) {

        if (data.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {

            String ano = data.substring(0, 4);
            String mes = data.substring(5, 7);
            String dia = data.substring(8, 10);

            return dia + "/" + mes + "/" + ano;
        }
        return data;
    }

    public static String insertDateNow() {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            return formatDate.format(new Date());
        } catch (Exception exception) {
            ExceptionUtils.throwException(exception);
        }
        return null;
    }

    public static String insertTimeNow() {
        SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
        return formatTime.format(new Date());
    }

    public static String DataHora(String tipoData) {

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();
        if ("hora".equals(tipoData)) {


            date = formataData(date, "").replace("/", "");
            String hora = time.substring(0, 2);
            String minutos = time.substring(3, 5);
            String segundos = time.substring(6, 8);
            time = hora + minutos + segundos;
            tipoData = time;

        } else if ("data".equals(tipoData)) {
            date = LocalDate.now().toString();
            date = formataData(date, "").replace("/", "");
            tipoData = date;
        }
        return tipoData;
    }

}
