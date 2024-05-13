package usuarios;

import java.time.format.DateTimeFormatter;
import java.util.List;

import citas.Agenda;
import citas.Cita;
import model.HoraConsulta;
import model.Unidad;
import util.Cadenas;
import util.FormatosFechas;

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
}
