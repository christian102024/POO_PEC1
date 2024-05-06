package usuarios;

import citas.Cita;
import citas.PersonalAsignado;

public class Estudiante extends Usuario {

	private PersonalAsignado personalAsignado;
	
	public Estudiante(String nombre, String apellidos, String dni, String telefono, Empleado personalAsignado) {
		super(nombre, apellidos, dni, telefono);
		this.personalAsignado = new PersonalAsignado(personalAsignado);
	}
	
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
		return super.toString() + ", personalAsignado: " + personalAsignado.getPersonalAsignado();
	}
	
	
	
	
}
