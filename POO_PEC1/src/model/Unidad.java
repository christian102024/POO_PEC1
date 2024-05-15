package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Unidad {
	ADMINISTRACION("Administración"),
	CONSULTAS_EXTERNAS("Consultas externas"),
	UCI("Unidad de Cuidados Intensivos (UCI)"),
	HOSPITALIZACION("Hospitalización"),
	PRUEBAS_MEDICAS("Pruebas médicas"),
	CARDIOVASCULAR("Cardiovascular"), // Unidades médicas especializadas
	DIABETES("Diabetes"), // Unidades médicas especializadas
    CAFETERIA("Cafetería"),
    APARCAMIENTO("Aparcamiento"),
	URGENCIAS("Urgencias"),
	FORMACION("Formación");

	private final String valor;
	
	private Unidad(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public static List<String> getValores() {
		List<String> listaUnidades = new ArrayList<String>();
		for (Unidad unidad : Unidad.values()) {
			listaUnidades.add(unidad.getValor());
		}
		return listaUnidades;
	}
	
	
}
