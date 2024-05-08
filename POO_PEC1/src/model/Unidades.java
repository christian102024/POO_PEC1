package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Unidades {
	private List<Unidad> unidades;

	public Unidades(List<Unidad> unidades) {
		super();
		this.unidades = unidades;
	}

	public Unidades() {
		super();
	}

	public List<Unidad> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidad> unidades) {
		this.unidades = unidades;
	}
	
	public static List<Unidad> getUnidadesDisponiblesPorServicio(Servicio servicio) {
		List<Unidad> unidades = new ArrayList<Unidad>();
		
		switch(servicio) {
		case GERENCIA:
			unidades.add(Unidad.ADMINISTRACION);
			break;
		case MEDICINA:
			unidades.add(Unidad.CONSULTAS_EXTERNAS);
			unidades.add(Unidad.CARDIOVASCULAR);
			unidades.add(Unidad.DIABETES);
			unidades.add(Unidad.HOSPITALIZACION);
			unidades.add(Unidad.FORMACION);
			break;
		case ENFERMERIA:
			unidades.add(Unidad.CONSULTAS_EXTERNAS);
			unidades.add(Unidad.URGENCAS);
			unidades.add(Unidad.HOSPITALIZACION);
			unidades.add(Unidad.UCI);
			unidades.add(Unidad.PRUEBAS_MEDICAS);
			unidades.add(Unidad.CARDIOVASCULAR);
			unidades.add(Unidad.DIABETES);
			break;
		case SOPORTE:
			unidades.add(Unidad.CAFETERIA);
			unidades.add(Unidad.APARCAMIENTO);
			unidades.add(Unidad.ADMINISTRACION);
			break;
		}
		return unidades;
	}
	
	public static List<String> getValoresUnidadesDisponiblesPorServicio(Servicio servicio) {
		List<String> unidades = new ArrayList<String>();
		
		switch(servicio) {
		case GERENCIA:
			unidades.add(Unidad.ADMINISTRACION.getValor());
			break;
		case MEDICINA:
			unidades.add(Unidad.CONSULTAS_EXTERNAS.getValor());
			unidades.add(Unidad.CARDIOVASCULAR.getValor());
			unidades.add(Unidad.DIABETES.getValor());
			unidades.add(Unidad.HOSPITALIZACION.getValor());
			unidades.add(Unidad.FORMACION.getValor());
			break;
		case ENFERMERIA:
			unidades.add(Unidad.CONSULTAS_EXTERNAS.getValor());
			unidades.add(Unidad.URGENCAS.getValor());
			unidades.add(Unidad.HOSPITALIZACION.getValor());
			unidades.add(Unidad.UCI.getValor());
			unidades.add(Unidad.PRUEBAS_MEDICAS.getValor());
			unidades.add(Unidad.CARDIOVASCULAR.getValor());
			unidades.add(Unidad.DIABETES.getValor());
			break;
		case SOPORTE:
			unidades.add(Unidad.CAFETERIA.getValor());
			unidades.add(Unidad.APARCAMIENTO.getValor());
			unidades.add(Unidad.ADMINISTRACION.getValor());
			break;
		}
		return unidades;
	}
	
}
