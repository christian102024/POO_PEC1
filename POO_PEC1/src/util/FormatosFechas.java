package util;

import java.time.format.DateTimeFormatter;

/**
 * Enumeración que define diferentes formatos de fecha y hora.
 */
public enum FormatosFechas {
	/**
	 * Formato de fecha "yyyy-MM-dd".
	 */
	FORMATO_DIA("yyyy-MM-dd"),

	/**
	 * Formato de hora "HH:mm".
	 */
	FORMATO_HORA("HH:mm"),

	/**
	 * Formato de fecha y hora "yyyy-MM-dd HH:mm".
	 */
	FORMATO_DIA_HORA("yyyy-MM-dd HH:mm");

	private final DateTimeFormatter formatter;

	/**
	 * Constructor privado para el enum FormatosFechas.
	 *
	 * @param pattern El patrón de formato de fecha y hora.
	 */
	FormatosFechas(String pattern) {
		this.formatter = DateTimeFormatter.ofPattern(pattern);
	}

	/**
	 * Obtiene el formateador de fecha y hora asociado a este formato.
	 *
	 * @return El formateador de fecha y hora.
	 */
	public DateTimeFormatter getFormatter() {
		return formatter;
	}
}
