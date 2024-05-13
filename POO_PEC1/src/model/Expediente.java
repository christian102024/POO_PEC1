package model;

public class Expediente {

	private String informe;
	private String tratamiento;
	private String diagnostico;
	
	public Expediente(String informe, String tratamiento, String diagnostico) {
		super();
		this.informe = informe;
		this.tratamiento = tratamiento;
		this.diagnostico = diagnostico;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	@Override
	public String toString() {
		return "Expediente [informe: " + informe + ", tratamiento: " + tratamiento + ", diagnostico: " + diagnostico + "]";
	}
	
	
	
}
