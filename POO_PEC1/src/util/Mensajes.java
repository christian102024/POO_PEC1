package util;

/**
 * Enumeración que define mensajes predefinidos para diferentes situaciones.
 */
public enum Mensajes {
    /**
     * Mensaje que indica que el proceso ha sido cancelado.
     */
	PROCESO_CANCELADO("Proceso cancelado; Redirigiendo al menu anterior.");

	private final String mensaje;

	Mensajes(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
