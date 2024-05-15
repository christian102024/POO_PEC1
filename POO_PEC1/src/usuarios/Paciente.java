package usuarios;

import java.util.List;

import model.Expediente;
import model.Seguro;

/**
 * Clase que representa a un paciente en el sistema.
 */
public class Paciente extends Usuario {

	private Seguro seguro;
	private String numeroSeguridadSocial;
	private List<Expediente> expedientes;
	
	/**
     * Constructor de la clase Paciente.
     *
     * @param nombre                 El nombre del paciente.
     * @param apellidos              Los apellidos del paciente.
     * @param dni                    El DNI del paciente.
     * @param telefono               El número de teléfono del paciente.
     * @param seguro                 El tipo de seguro del paciente.
     * @param numeroSeguridadSocial  El número de seguridad social del paciente.
     * @param expedientes            La lista de expedientes médicos del paciente.
     */
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
	
	public void anyadirExpediente(Expediente expediente) {
		this.expedientes.add(expediente);
	}

	@Override
	public String toString() {
		String expedientesConcatenados = "";
		if(expedientes != null && expedientes.size() > 0) {
			for (Expediente expediente : this.expedientes) {
				expedientesConcatenados += expediente.toString();
			}			
		} else {
			expedientesConcatenados += "Sin expedientes";
		}
		return super.toString() + ", seguro: " + seguro + ", número de seguridad social: " + numeroSeguridadSocial + ", expedientes: " + expedientesConcatenados;
	}
	
}
