package usuarios;

public class Estudiante extends Usuario {

	private Empleado personalAsignado;
	
	public Estudiante(String nombre, String apellidos, String dni, String telefono, Empleado personalAsignado) {
		super(nombre, apellidos, dni, telefono);
		this.personalAsignado = personalAsignado;
	}
	
	public Estudiante() {
		super();
	}

	public Empleado getPersonalAsignado() {
		return personalAsignado;
	}

	public void setPersonalAsignado(Empleado personalAsignado) {
		this.personalAsignado = personalAsignado;
	}
	
}
