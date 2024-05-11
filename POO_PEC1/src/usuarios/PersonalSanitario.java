package usuarios;

import citas.Agenda;
import model.Unidad;

public class PersonalSanitario extends Empleado {
	private Agenda agenda;

	public PersonalSanitario(String nombre, String apellidos, String dni, String telefono, Unidad unidad,
			Agenda agenda) {
		super(nombre, apellidos, dni, telefono, unidad);
		this.agenda = agenda;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public static PersonalSanitario convertirEmpleadoEnPersonalSanitario(Empleado empleado) {
		if (empleado instanceof PersonalSanitario) {
			PersonalSanitario personalSanitario = (PersonalSanitario) empleado;
			return personalSanitario;
		} else {
			System.out.println("El empleado no pertenece al Personal Sanitario");
			return null;
		}
	}

	public static PersonalSanitario comprobarEmpleadoEsMedico(Empleado empleado) {
		PersonalSanitario personalSanitario = convertirEmpleadoEnPersonalSanitario(empleado);
		if (personalSanitario == null) {
			return null;
		}
		if (personalSanitario.getUnidad() == Unidad.MEDICINA) {
			return personalSanitario;
		} else {
			System.out.println("El empleado no es un Médico");
			return null;
		}

	}

	@Override
	public String toString() {
		return super.toString() + ", agenda: " + agenda;
	}

}
