package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumeración que representa los diferentes servicios disponibles en el sistema.
 */
public enum Servicio {	
	GERENCIA("Gerencia"),
	MEDICINA("Medicina"),
	ENFERMERIA("Enfermería"),
	SOPORTE("Soporte");
	
	private final String valor;
	
	private Servicio(String valor) {
		this.valor = valor;
	}
	
    /**
     * Obtiene el valor asociado al servicio.
     *
     * @return El valor asociado al servicio.
     */
	public String getValor() {
		return this.valor;
	}
	
    /**
     * Obtiene una lista de cadenas que representan los valores de todos los servicios disponibles.
     *
     * @return Lista de cadenas que representan los valores de los servicios disponibles.
     */
	public static List<String> getValores() {
		List<String> listaServicios = new ArrayList<>();
		for(Servicio servicio: Servicio.values()) {
			listaServicios.add(servicio.getValor());
		}
		return listaServicios;
	}
}
