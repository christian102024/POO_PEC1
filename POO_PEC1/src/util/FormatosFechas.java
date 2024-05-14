package util;

import java.time.format.DateTimeFormatter;

public enum FormatosFechas {
    FORMATO_DIA("yyyy-MM-dd"),
    FORMATO_HORA("HH:mm"),
	FORMATO_DIA_HORA("yyyy-MM-dd HH:mm");

    private final DateTimeFormatter formatter;

    FormatosFechas(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}