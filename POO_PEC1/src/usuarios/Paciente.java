package usuarios;

import java.util.List;

import model.Expediente;
import model.Seguro;

public class Paciente extends Usuario {

	private Seguro seguro;
	private String numeroSeguridadSocial;
	private List<Expediente> expedientes;
	
	public Paciente(String nombre, String apellidos, String dni, String telefono, Seguro seguro,
			String numeroSeguridadSocial, List<Expediente> expedientes) {
		super(nombre, apellidos, dni, telefono);
		this.seguro = seguro;
		this.numeroSeguridadSocial = numeroSeguridadSocial;
		this.expedientes = expedientes;
	}

	public Paciente() {
		super();
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public String getNumeroSeguridadSocial() {
		return numeroSeguridadSocial;
	}

	public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
		this.numeroSeguridadSocial = numeroSeguridadSocial;
	}

	public List<Expediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
	
	
	
	
}
