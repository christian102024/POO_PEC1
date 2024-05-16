package usuarios;

import java.text.Normalizer.Form;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import campus.Habitacion;
import campus.Habitaciones;
import citas.Cita;
import citas.MostrarAgenda;
import model.Expediente;
import model.Seguro;
import model.Unidad;
import ui.MostrarMenu;
import util.EntradaValores;
import util.FormatosFechas;
import util.Mensajes;

/**
 * Clase que gestiona la lista de pacientes. Hace uso del patrón Singleton.
 */
public class Pacientes {
	private static Pacientes instancia;
	private List<Paciente> pacientes;

	/**
	 * Constructor privado de la clase Pacientes.
	 */
	public Pacientes() {
		super();
		this.pacientes = new ArrayList<Paciente>();
	}

	/**
	 * Obtiene la instancia única de la clase Pacientes (patrón Singleton).
	 *
	 * @return La instancia de la clase Pacientes.
	 */
	public static Pacientes getInstancia() {
		if (instancia == null) {
			instancia = new Pacientes();
		}
		return instancia;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public void addPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
	}

	/**
	 * Permite dar de alta a un paciente.
	 */
	public void darDeAltaPaciente() {
		Scanner scanner = new Scanner(System.in);
		Paciente paciente = new Paciente();

		paciente = pedirDatosPaciente(scanner, paciente);

		if (paciente != null) {
			addPaciente(paciente);
		} else {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
		}
	}

	/**
	 * Solicita los datos de un paciente al usuario.
	 *
	 * @param scanner  El objeto Scanner para la entrada de datos.
	 * @param paciente El paciente para el cual se solicitan los datos.
	 * @return El objeto Paciente con los datos introducidos, o null si se cancela
	 *         el proceso.
	 */
	private Paciente pedirDatosPaciente(Scanner scanner, Paciente paciente) {
		String nombre = EntradaValores.introducirCadena("Nombre: ", paciente.getNombre());
		if (nombre == null) {
			return null;
		}
		paciente.setNombre(nombre);

		String apellidos = EntradaValores.introducirCadena("Apellidos: ", paciente.getApellidos());
		if (apellidos == null) {
			return null;
		}
		paciente.setApellidos(apellidos);

		String dni = EntradaValores.introducirCadena("DNI: ", paciente.getDni());
		if (dni == null) {
			return null;
		}
		paciente.setDni(dni);

		String telefono = EntradaValores.introducirCadena("Teléfono: ", paciente.getTelefono());
		if (telefono == null) {
			return null;
		}
		paciente.setTelefono(telefono);

		MostrarMenu.mostrarMenu("Seleccione el tipo de seguro", Arrays.asList(Seguro.SEGURIDAD_SOCIAL.toString(),
				Seguro.SEGURO_MEDICO.toString(), Seguro.NO_ASEGURADO.toString()));
		Integer opcion = EntradaValores.introducirNumeroEntero("Seleccione el tipo de seguro: ", new int[] { 1, 2, 3 });
		if (opcion != null) {
			paciente.setSeguro(Seguro.values()[opcion - 1]);
		} else {
			return null;
		}

		String seguridadSocial = EntradaValores.introducirCadena("Número de seguridad social: ");
		if (seguridadSocial == null) {
			return null;
		}
		paciente.setNumeroSeguridadSocial(seguridadSocial);

		return paciente;
	}

	/**
	 * Permite dar de baja a un paciente solicitando su DNI al usuario.
	 */
	public void darDeBajaPaciente() {

		String dni = EntradaValores.introducirCadena("Introduzca el DNI del paciente a eliminar: ");
		if (dni == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return;
		} else {
			darDeBajaPaciente(dni);
		}

	}

	/**
	 * Elimina un paciente de la lista según su DNI.
	 *
	 * @param dni El DNI del paciente a dar de baja.
	 */
	public void darDeBajaPaciente(String dni) {
		Integer indice = buscarIndiceDePacientePorDNI(dni);
		if (indice == -1) {
			System.out.println("Paciente no encontrado.");
		} else if (indice == null) {
			System.out.println("Proceso cancelado.");
		} else {
			pacientes.remove(indice.intValue());
			System.out.println("Paciente eliminado");
		}
	}

	/**
	 * Busca un paciente en la lista por su DNI.
	 *
	 * @return El objeto Paciente encontrado, o null si no se encuentra.
	 */
	public Paciente buscarPacientePorDNI() {
		boolean continuar = true;

		while (continuar) {
			Integer indice = buscarIndiceDePacientePorDNI();
			if (indice == null)
				continuar = false;
			else if (indice == -1)
				System.out.println("Paciente no encontrado.");
			else
				return pacientes.get(indice);

		}
		return null;
	}

	/**
	 * Busca el índice de un paciente en la lista por su DNI.
	 *
	 * @return El índice del paciente encontrado, o null si se cancela el proceso.
	 */
	public Integer buscarIndiceDePacientePorDNI() {
		String dni = EntradaValores.introducirCadena("Introduzca el DNI del paciente: ");

		if (dni == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return null;
		}

		return buscarIndiceDePacientePorDNI(dni);
	}

	/**
	 * Busca el índice de un paciente en la lista por su DNI.
	 *
	 * @param dni El DNI del paciente a buscar.
	 * @return El índice del paciente encontrado, -1 si no se encuentra, o null si
	 *         se cancela el proceso.
	 */
	public Integer buscarIndiceDePacientePorDNI(String dni) {
		if (dni == "cancelar")
			return null;

		for (int i = 0; i < pacientes.size(); i++) {
			Paciente paciente = pacientes.get(i);
			if (paciente.getDni().equals(dni)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Permite ingresar un paciente y asignarle una habitación.
	 */
	public void ingresarPaciente() {
		Paciente paciente = buscarPacientePorDNI();
		Habitacion habitacion = buscarHabitacionPorNumero();
		paciente = addTratamiento(paciente);

		ingresarPaciente(paciente, habitacion);
		System.out.println("Paciente ingresado correctamente");
	}

	/**
	 * Ingresa un paciente en una habitación específica.
	 *
	 * @param paciente   El paciente a ingresar.
	 * @param habitacion La habitación en la que se va a ingresar al paciente.
	 */
	public void ingresarPaciente(Paciente paciente, Habitacion habitacion) {
		if (paciente != null && habitacion != null) {
			habitacion.setOcupada(true);
			habitacion.setPaciente(paciente);

			Habitaciones habitaciones = Habitaciones.getInstancia();
			habitaciones.setHabitaciones(actualizarHabitacion(paciente, habitacion, habitaciones));
		}
	}

	/**
	 * Da de alta a un paciente ingresado, liberando la habitación que ocupaba.
	 */
	public void darAltaPacienteIngresado() {
		Paciente paciente = buscarPacientePorDNI();
		if (paciente != null) {
			Habitaciones habitaciones = Habitaciones.getInstancia();
			int indice = habitaciones.buscarIndiceHabitacionPorPaciente(paciente);

			if (indice != -1) {
				Habitacion habitacion = habitaciones.getHabitaciones().get(indice);
				habitacion.setOcupada(false);
				habitacion.setPaciente(null);
				System.out.println("Paciente dado de alta; Habitación desocupada.");
			}
		} else {
			return;
		}

	}

	/**
	 * Busca una habitación disponible por su número.
	 *
	 * @return La habitación encontrada, o null si se cancela el proceso.
	 */
	public Habitacion buscarHabitacionPorNumero() {
		boolean encontrada = false;
		Habitacion habitacionEncontrada = null;

		while (!encontrada) {
			Integer numeroHabitacion = EntradaValores.introducirNumeroEntero("Introduzca el número de habitación: ");

			Habitaciones habitaciones = Habitaciones.getInstancia();
			for (int i = 0; i < habitaciones.getHabitaciones().size(); i++) {

				Habitacion habitacion = habitaciones.getHabitaciones().get(i);

				if (habitacion.getNumeroHabitacion() == (numeroHabitacion) && habitacion.isOcupada() == false) {
					habitacionEncontrada = habitacion;
					encontrada = true;
				} else if (habitacion.getNumeroHabitacion() == (numeroHabitacion)) {
					System.out.println("La habitación esta ocupada");
					i = habitaciones.getHabitaciones().size();
				}
			}
			if (!encontrada)
				System.out.println("Habitación no encontrada");
		}

		return habitacionEncontrada;
	}

	/**
	 * Actualiza los datos de una habitación en la lista de habitaciones.
	 *
	 * @param paciente     El paciente ingresado en la habitación.
	 * @param habitacion   La habitación que se va a actualizar.
	 * @param habitaciones La lista de habitaciones donde se va a realizar la
	 *                     actualización.
	 * @return La lista actualizada de habitaciones.
	 */
	public List<Habitacion> actualizarHabitacion(Paciente paciente, Habitacion habitacion, Habitaciones habitaciones) {
		List<Habitacion> listaHabitaciones = habitaciones.getHabitaciones();

		int indice = habitaciones.buscarHabitacionPorNumero(habitacion.getNumeroHabitacion());

		if (indice != -1) {
			listaHabitaciones.set(indice, habitacion);
		}

		return listaHabitaciones;
	}

	/**
	 * Agrega un tratamiento al expediente de un paciente.
	 *
	 * @param paciente El paciente al que se le agregará el tratamiento.
	 * @return El paciente con el tratamiento agregado, o null si no se agrega
	 *         tratamiento.
	 */
	public Paciente addTratamiento(Paciente paciente) {
		boolean tratamiento = EntradaValores.introducirValorBooleano("¿Introducir procedimiento médico? (S/N): ");

		if (tratamiento) {
			paciente.getExpedientes().add(pedirDatosProcedimiento());
			System.out.println("Tratamiento");
		} else {
			return null;
		}

		return paciente;
	}

	/**
	 * Solicita al usuario los datos de un procedimiento médico.
	 *
	 * @return El objeto Expediente con los datos introducidos, o null si se cancela
	 *         el proceso.
	 */
	private Expediente pedirDatosProcedimiento() {
		Expediente expediente = new Expediente(null, null, null);
		String procedimiento = EntradaValores.introducirCadena("Escriba el procedimiento médico: ");
		expediente.setProcedimiento(procedimiento);
		return expediente;

	}

	/**
	 * Permite al usuario actualizar el expediente de un paciente.
	 */
	public void actualizarExpedientePaciente() {
		Integer indice = buscarIndiceDePacientePorDNI();

		if (indice != null && indice != -1) {
			Paciente paciente = pacientes.get(indice);
			Expediente expediente = pedirDatosExpediente();
			paciente.anyadirExpediente(expediente);
		} else if (indice == null) {
			return;
		} else if (indice == -1) {

			System.out.println("Paciente no encontrado.");
		}
	}

	/**
	 * Solicita al usuario los datos de un expediente médico.
	 *
	 * @return El objeto Expediente con los datos introducidos, o null si se cancela
	 *         el proceso.
	 */
	public Expediente pedirDatosExpediente() {
		Expediente expediente = new Expediente();
		String informe = EntradaValores.introducirCadena("Informe: ");
		if (informe == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setInforme(informe);

		String procedimiento = EntradaValores.introducirCadena("Procedimiento: ");
		if (procedimiento == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setProcedimiento(procedimiento);

		String diagnostico = EntradaValores.introducirCadena("Diagnostico: ");
		if (diagnostico == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setDiagnostico(diagnostico);

		return expediente;
	}

	/**
	 * Muestra los pacientes ingresados en el hospital.
	 */
	public void mostrarPacientesIngresados() {
		System.out.println("PACIENTES INGRESADOS EN EL HOSPITAL:");
		for (Habitacion habitacion : Habitaciones.getInstancia().getHabitaciones()) {
			Paciente paciente = habitacion.getPaciente();

			if (paciente != null) {
				System.out.println("\t" + paciente.toString());
			}
		}
	}

	/**
	 * Pide una fecha y muestra los pacientes en el método
	 * mostrarPacientesCadaMiembroPersonalMedico()
	 */
	public void mostrarPacientesCadaMiembroPersonalMedico() {
		LocalDate fecha = EntradaValores.introducirFecha("Introduzca la fecha: ");

		if (fecha != null) {
			mostrarPacientesCadaMiembroPersonalMedico(fecha);
		}
	}

	/**
	 * Muestra los pacientes de cada miembro del personal medico con el tengan
	 * el registro de haber pedido una cita en una fecha específica.
	 * @param fecha
	 */
	public void mostrarPacientesCadaMiembroPersonalMedico(LocalDate fecha) {
		List<PersonalSanitario> listaPersonalSanitarios = PersonalSanitario
				.obtenerTodosLosEmpleadosDelPersonalSanitario();

		if (listaPersonalSanitarios != null) {
			for (PersonalSanitario personalSanitario : listaPersonalSanitarios) {
				System.out.println("PACIENTES DEL PERSONAL SANITARIO " + personalSanitario.getNombre() + " "
						+ personalSanitario.getApellidos());
				List<Cita> listaCitas = personalSanitario.getAgenda().getListaCitas(fecha);
				if (listaCitas == null || listaCitas.size() == 0) {
					System.out.println("\tSin pacientes asignados el día "
							+ fecha.format(FormatosFechas.FORMATO_DIA.getFormatter()));
					System.out.println();
				} else {
					MostrarAgenda.mostrarAgenda(personalSanitario.getAgenda(), fecha);
				}
			}
		}
	}

	/**
	 * Pide una fecha y muestra los pacientes que tengan una cita en el día indicado en la fecha parametrizada
	 * con un especialista de la unidad de CONSULTAS EXTERNAS.
	 * @param fecha
	 */
	public void mostrarPacientesAgendaConsultasExternas() {
		LocalDate fecha = EntradaValores.introducirFecha("Introduzca la fecha: ");

		if (fecha != null) {
			mostrarPacientesAgendaConsultasExternas(fecha);

		}
	}

	/**
	 * Muestra los pacientes que tengan una cita en el día indicado en la fecha parametrizada
	 * con un especialista de la unidad de CONSULTAS EXTERNAS.
	 * @param fecha
	 */
	public void mostrarPacientesAgendaConsultasExternas(LocalDate fecha) {
		List<PersonalSanitario> listaPersonalSanitarios = PersonalSanitario
				.obtenerTodosLosEmpleadosDelPersonalSanitario();
		String pacientes = "";

		System.out.println(
				"PACIENTES EN CONSULTAS EXTERNAS - " + fecha.format(FormatosFechas.FORMATO_DIA.getFormatter()));
		for (PersonalSanitario personalSanitario : listaPersonalSanitarios) {
			if (personalSanitario.getUnidad().equals(Unidad.CONSULTAS_EXTERNAS)) {
				List<Cita> listaCitas = personalSanitario.getAgenda().getListaCitas(fecha);

				if (listaCitas != null)
					for (Cita cita : listaCitas) {
						pacientes += "\t" + cita.getPaciente().toString() + "\n";
					}
			}
		}

		if (pacientes.equals("")) {
			System.out.println("Sin empleados en consultas externas");
		} else {
			System.out.println(pacientes);
		}
	}

	/**
	 * Muestra los pacientes que tienen citas con un especialista en un periodo
	 * de una semana o de un día.
	 * 
	 */
	public void mostrarPacientesEspecialistaEnPeriodo() {
		boolean navegar = false;
		do {
			MostrarMenu.mostrarMenu("Seleccione el tipo de periodo",
					Arrays.asList("Pacientes en una semana", "Pacientes en un día"));

			String opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			switch (opcion) {
			case "1":
				mostrarPacientesEspecialistaEnSemana();
				navegar = true;
				break;
			case "2":
				mostrarPacientesEspecialistaEnDia();
				navegar = true;
				break;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}

	/**
	 * Muestra los pacientes que tienen citas con un especialista en una semana específica.
	 * 
	 */
	public void mostrarPacientesEspecialistaEnSemana() {
		LocalDate fecha = EntradaValores.introducirFecha("Introduzca el día para buscar los pacientes");
		LocalDate[] fechaInicioFin = EntradaValores.obtenerFechaInicioYFinSemana(fecha);
		LocalDate fechaInicio = fechaInicioFin[0];
		LocalDate fechaFin = fechaInicioFin[1];

		List<PersonalSanitario> listaPersonalSanitarios = PersonalSanitario
				.obtenerTodosLosEmpleadosDelPersonalSanitario();
		String pacientes = "";

		System.out.println("PACIENTES QUE TIENEN QUE VER A UN ESPECIALISTA LA SEMANA "
				+ fechaInicio.format(FormatosFechas.FORMATO_DIA.getFormatter()) + " - "
				+ fechaFin.format(FormatosFechas.FORMATO_DIA.getFormatter()));
		for (PersonalSanitario personalSanitario : listaPersonalSanitarios) {

			for (int i = 0; i < 7; i++) {
				List<Cita> listaCitas = personalSanitario.getAgenda().getListaCitas(fechaInicio);

				if (listaCitas != null)
					for (Cita cita : listaCitas) {
						pacientes += "\t" + cita.getPaciente().toString() + "\n";
					}

				fechaInicio = fechaInicio.plusDays(1);
			}
			fechaInicio = fechaInicioFin[0];

		}

		if (pacientes.equals("")) {
			System.out.println("Sin empleados en esa semana.");
		} else {
			System.out.println(pacientes);
		}
	}

	/**
	 * Muestra los pacientes que tienen citas con un especialista en un día específico.
	 * 
	 */
	public void mostrarPacientesEspecialistaEnDia() {
		LocalDate fecha = EntradaValores.introducirFecha("Introduzca el día para buscar los pacientes");
		List<PersonalSanitario> listaPersonalSanitarios = PersonalSanitario
				.obtenerTodosLosEmpleadosDelPersonalSanitario();
		String pacientes = "";

		System.out.println("PACIENTES QUE TIENEN QUE VER A UN ESPECIALISTA EL DÍA "
				+ fecha.format(FormatosFechas.FORMATO_DIA.getFormatter()));
		for (PersonalSanitario personalSanitario : listaPersonalSanitarios) {

			List<Cita> listaCitas = personalSanitario.getAgenda().getListaCitas(fecha);

			if (listaCitas != null)
				for (Cita cita : listaCitas) {
					pacientes += "\t" + cita.getPaciente().toString() + "\n";
				}

		}

		if (pacientes.equals("")) {
			System.out.println("Sin empleados en ese día.");
		} else {
			System.out.println(pacientes);
		}
	}

	@Override
	public String toString() {
		String resultadoConcatenado = "";
		for (Paciente paciente : pacientes) {
			resultadoConcatenado += paciente + "\n";
		}
		if (pacientes.size() == 0) {
			resultadoConcatenado += "No existen pacientes dados de alta.";
		}
		return resultadoConcatenado;
	}

}
