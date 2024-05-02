package model;

import java.util.Arrays;
import java.util.List;

public interface Unidades {
	List<String> UNIDADES = Arrays.asList(Unidad.ENFERMERIA.getValor(), Unidad.GERENCIA.getValor() ,Unidad.MEDICINA.getValor(), Unidad.SOPORTE.getValor());
	List<String> UNIDADES2 = Arrays.asList(Unidad.ADMINISTRACION.getValor(), Unidad.CONSULTAS_EXTERNAS.getValor(), Unidad.UCI.getValor(), 
											Unidad.HOSPITALIZACION.getValor(), Unidad.PRUEBAS_MEDICAS.getValor(), Unidad.UNIDADES_MEDICAS_ESPECIALIZADAS.getValor(),
											Unidad.GERENCIA.getValor() ,Unidad.MEDICINA.getValor(), Unidad.ENFERMERIA.getValor(), Unidad.SOPORTE.getValor());
	 List<Unidad> UNIDADES3 = Arrays.asList(Unidad.ADMINISTRACION, Unidad.CONSULTAS_EXTERNAS, Unidad.UCI, 
             Unidad.HOSPITALIZACION, Unidad.PRUEBAS_MEDICAS, Unidad.UNIDADES_MEDICAS_ESPECIALIZADAS,
             Unidad.GERENCIA, Unidad.MEDICINA, Unidad.ENFERMERIA, Unidad.SOPORTE);
	
}
