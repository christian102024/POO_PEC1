package model;

import java.util.Arrays;

public enum Unidad {
	ADMINISTRACION("Administración"),
	CONSULTAS_EXTERNAS("Consultas externas"),
	UCI("Unidad de Cuidados Intensivos (UCI)"),
	HOSPITALIZACION("Hospitalización"),
	PRUEBAS_MEDICAS("Pruebas médicas"),
	UNIDADES_MEDICAS_ESPECIALIZADAS("Unidades médicas especializadas"),
	GERENCIA("Gerencia"),
	MEDICINA("Medicina"),
    ENFERMERIA("Enfermeria"),
    SOPORTE("Soporte"),
	URGENCAS("Urgencias");

	private final String valor;
	
	private Unidad(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	
}
