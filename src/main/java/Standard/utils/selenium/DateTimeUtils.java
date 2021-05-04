package Standard.utils.selenium;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import Standard.utils.exceptions.ExceptionUtils;

public class DateTimeUtils {

	public static String getHoraAtual() {
		Instant instante = Instant.now();
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(instante.toEpochMilli());
	}

	public static String getDiaAtual() {
		Instant instante = Instant.now();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(instante.toEpochMilli());
	}

	public static String getDtHr() {
		String dia = getDiaAtual().replaceAll("/", "-");
		String hora = getHoraAtual().replaceAll(":", "_");
		return dia + "__" + hora;
	}

	public static String formataData(String data) {
		return formataData(data, "");
	}

	public static String formataData(String data, String pattern) {

		if (data.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {

			String ano = data.substring(0, 4);
			String mes = data.substring(5, 7);
			String dia = data.substring(8, 10);

			return dia + mes + ano;
		}

		return data;
	}

	/**
	 * Este_metodo_retorna a data_atual
	 */
	public static String insertDateNow() {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			return formatDate.format(new Date());
		} catch (Exception exception) {
			ExceptionUtils.throwException(exception);
		}
		return null;
	}

	/**
	 * Este_metodo_retorna a_hora_atual
	 */
	public static String insertTimeNow() {
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm:ss a");
		return formatTime.format(new Date());
	}

	public static SimpleDateFormat dataFinalMes(SimpleDateFormat date){
		SimpleDateFormat dataFimMes = new SimpleDateFormat("dd/MM/yyyy");
		Date d = Date.from(Instant.now());
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		cal.add(Calendar.DAY_OF_WEEK, - 30);
		return date;
	}

}
