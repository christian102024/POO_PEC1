package model;

import java.time.LocalDateTime;

import util.FormatosFechas;

public class Expediente {

	private LocalDateTime fecha;
	private String informe;
	private String procedimiento;
	private String diagnostico;
	
	public Expediente(String informe, String procedimiento, String diagnostico) {
		super();
		this.fecha = LocalDateTime.now();
		this.informe = informe;
		this.procedimiento = procedimiento;
		this.diagnostico = diagnostico;
	}
	
	public Expediente() {
		super();
		this.fecha = LocalDateTime.now();
		this.informe = null;
		this.procedimiento = null;
		this.diagnostico = null;
	}


	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public String getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Expediente [fecha: " + fecha.format(FormatosFechas.FORMATO_DIA_HORA.getFormatter()) +", informe: " + (informe != null ? informe : "-") + ", procedimiento: " + (procedimiento != null ? procedimiento : "-") + ", diagnostico: " + (diagnostico != null ? diagnostico : "-") + "] ";
	}
	
	
	
}
