package usuarios;

import java.util.ArrayList;
import java.util.List;

import citas.Agenda;
import model.Servicio;
import model.Unidad;
import model.Unidades;
import util.Comparador;

/**
 * Clase que representa a un miembro del personal sanitario.
 */
public class PersonalSanitario extends Empleado {
	private Agenda agenda;

	/**
	 * Constructor de la clase PersonalSanitario.
	 *
	 * @param nombre    El nombre del personal sanitario.
	 * @param apellidos Los apellidos del personal sanitario.
	 * @param dni       El DNI del personal sanitario.
	 * @param telefono  El número de teléfono del personal sanitario.
	 * @param unidad    La unidad a la que pertenece el personal sanitario.
	 * @param agenda    La agenda del personal sanitario.
	 */
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

	/**
	 * Convierte un objeto Empleado en un objeto PersonalSanitario si es posible.
	 *
	 * @param empleado El empleado que se desea convertir.
	 * @return El objeto PersonalSanitario correspondiente al empleado, o null si no
	 *         es posible la conversión.
	 */
	public static PersonalSanitario convertirEmpleadoEnPersonalSanitarioMostrandoErrores(Empleado empleado) {
		if (empleado instanceof PersonalSanitario) {
			PersonalSanitario personalSanitario = (PersonalSanitario) empleado;
			return personalSanitario;
		} else if (comprobarEmpleadoEsPersonalSanitario(empleado)) {
			PersonalSanitario personalSanitario = new PersonalSanitario(empleado.getNombre(), empleado.getApellidos(),
					empleado.getDni(), empleado.getTelefono(), empleado.getUnidad(), new Agenda());
			return personalSanitario;
		} else {
			System.out.println("El empleado no pertenece al Personal Sanitario");
			return null;
		}
	}

	/**
	 * Convierte un objeto Empleado en un objeto PersonalSanitario si es posible.
	 *
	 * @param empleado El empleado que se desea convertir.
	 * @return El objeto PersonalSanitario correspondiente al empleado, o null si no
	 *         es posible la conversión.
	 */
	public static PersonalSanitario convertirEmpleadoEnPersonalSanitario(Empleado empleado) {
		if (empleado instanceof PersonalSanitario) {
			PersonalSanitario personalSanitario = (PersonalSanitario) empleado;
			return personalSanitario;
		} else if (comprobarEmpleadoEsPersonalSanitario(empleado)) {
			PersonalSanitario personalSanitario = new PersonalSanitario(empleado.getNombre(), empleado.getApellidos(),
					empleado.getDni(), empleado.getTelefono(), empleado.getUnidad(), new Agenda());
			return personalSanitario;
		} else {
			return null;
		}
	}

	/**
	 * Comprueba si un empleado es un médico.
	 *
	 * @param empleado El empleado a comprobar.
	 * @return El objeto PersonalSanitario correspondiente al médico, o null si el
	 *         empleado no es médico.
	 */
	public static PersonalSanitario comprobarEmpleadoEsMedico(Empleado empleado) {
		PersonalSanitario personalSanitario = convertirEmpleadoEnPersonalSanitarioMostrandoErrores(empleado);
		if (personalSanitario == null) {
			return null;
		}
		if (Unidades.getServiciosPorUnidad(personalSanitario.getUnidad()).contains(Servicio.MEDICINA)) {
			return personalSanitario;
		} else {
			System.out.println("El empleado no es un Médico.");
			return null;
		}

	}

	/**
	 * Comprueba si un empleado es un enfermero.
	 *
	 * @param empleado El empleado a comprobar.
	 * @return El objeto PersonalSanitario correspondiente al enfermero, o null si
	 *         el empleado no es enfermero.
	 */
	public static PersonalSanitario comprobarEmpleadoEsEnfermero(Empleado empleado) {
		PersonalSanitario personalSanitario = convertirEmpleadoEnPersonalSanitarioMostrandoErrores(empleado);
		if (personalSanitario == null) {
			return null;
		}
		if (Unidades.getServiciosPorUnidad(personalSanitario.getUnidad()).contains(Servicio.ENFERMERIA)) {
			return personalSanitario;
		} else {
			System.out.println("El empleado no es un Enfermero.");
			return null;
		}
	}

	public static PersonalSanitario comprobarEmpleadoEsEnfermero(Empleado empleado, Unidad[] unidadesValidas) {
		PersonalSanitario personalSanitario = comprobarEmpleadoEsEnfermero(empleado);

		if (personalSanitario == null) {
			return null;
		}

		boolean unidadValida = Comparador.buscarValorEnArray(personalSanitario.getUnidad(), unidadesValidas);

		if (unidadValida) {
			return personalSanitario;
		} else {
			System.out.println("La unidad del enfermero no es válida.");
			return null;
		}
	}

	public static boolean comprobarEmpleadoEsPersonalSanitario(Empleado empleado) {
		List<Servicio> serviciosPorUnidad = Unidades.getServiciosPorUnidad(empleado.getUnidad());

		if (serviciosPorUnidad.contains(Servicio.ENFERMERIA) || serviciosPorUnidad.contains(Servicio.MEDICINA)) {
			return true;
		} else {
			return false;
		}
	}

	public static List<PersonalSanitario> obtenerTodosLosEmpleadosDelPersonalSanitario() {
		Empleados empleados = Empleados.getInstancia();
		List<Empleado> listaEmpleados = empleados.getEmpleados();
		List<PersonalSanitario> listaPersonalSanitario = new ArrayList<PersonalSanitario>();

		for (Empleado empleado : listaEmpleados) {
			PersonalSanitario personal = PersonalSanitario.convertirEmpleadoEnPersonalSanitario(empleado);
			if (personal != null)
				listaPersonalSanitario.add(personal);
		}

		if (listaPersonalSanitario.size() == 0)
			return null;

		return listaPersonalSanitario;
	}
}
