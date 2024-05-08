package model;

import java.util.ArrayList;
import java.util.List;

public enum Servicio {	
	GERENCIA("Gerencia"),
	MEDICINA("Medicina"),
	ENFERMERIA("Enfermería"),
	SOPORTE("Soporte");
	
	private final String valor;
	
	private Servicio(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public static List<String> getValores() {
		List<String> listaServicios = new ArrayList<>();
		for(Servicio servicio: Servicio.values()) {
			listaServicios.add(servicio.getValor());
		}
		return listaServicios;
	}
}
