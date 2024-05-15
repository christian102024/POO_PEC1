package usuarios;

import citas.Cita;
import citas.PersonalAsignado;

/**
 * Clase que representa a un estudiante.
 */
public class Estudiante extends Usuario {

	private PersonalAsignado personalAsignado;
	
	/**
	 * Crea un nuevo estudiante con el nombre, apellidos, DNI, teléfono y personal asignado (empleado).
	 *
	 * @param nombre            El nombre del estudiante.
	 * @param apellidos         Los apellidos del estudiante.
	 * @param dni               El DNI del estudiante.
	 * @param telefono          El teléfono del estudiante.
	 * @param personalAsignado El personal asignado (empleado) al estudiante.
	 */
	public Estudiante(String nombre, String apellidos, String dni, String telefono, Empleado personalAsignado) {
		super(nombre, apellidos, dni, telefono);
		this.personalAsignado = new PersonalAsignado(personalAsignado);
	}
	
	/**
	 * Crea un nuevo estudiante con el nombre, apellidos, DNI, teléfono y personal asignado (cita).
	 *
	 * @param nombre            El nombre del estudiante.
	 * @param apellidos         Los apellidos del estudiante.
	 * @param dni               El DNI del estudiante.
	 * @param telefono          El teléfono del estudiante.
	 * @param personalAsignado La cita asignada al estudiante.
	 */
	public Estudiante(String nombre, String apellidos, String dni, String telefono, Cita personalAsignado) {
		super(nombre, apellidos, dni, telefono);
		this.personalAsignado = new PersonalAsignado(personalAsignado);
	}
	
	
	public Estudiante() {
		super();
	}

	public PersonalAsignado getPersonalAsignado() {
		return personalAsignado;
	}

	public void setPersonalAsignado(Empleado personalAsignado) {
		this.personalAsignado = new PersonalAsignado(personalAsignado);
	}
	
	public void setPersonalAsignado(Cita personalAsignado) {
		this.personalAsignado = new PersonalAsignado(personalAsignado);	
	}

	@Override
	public String toString() {
		return super.toString() + ", personalAsignado: " + (personalAsignado != null ? personalAsignado.getPersonalAsignado() : "-");
	}
	
	
	
	
}
