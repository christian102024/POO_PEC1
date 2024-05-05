package util;

public enum Mensajes {
	PROCESO_CANCELADO("Proceso cancelado; Redirigiendo al menu anterior.");

	private final String mensaje;

	Mensajes(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}
}
