package ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import campus.Habitaciones;
import citas.Agenda;
import citas.Cita;
import citas.GestionarAgenda;
import citas.GestionarAgenda.TipoPersonal;
import citas.MostrarAgenda;
import model.Facturas;
import model.Recursos;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Estudiantes;
import usuarios.Paciente;
import usuarios.Pacientes;
import usuarios.PersonalSanitario;
import util.EntradaValores;

/**
 * Clase dedicada a la navegación entre los menus de la aplicacion y la funcionalidad
 * de sus respectivas clases.
 * @author Christian
 *
 */
public class Menus {

	private static Scanner scanner;
	private Empleados empleados;
	private Estudiantes estudiantes;
	private Pacientes pacientes;
	private Habitaciones habitaciones;
	private Facturas facturas;
	private Recursos recursos;

	public Menus() {
		super();
		scanner = new Scanner(System.in);
		empleados = Empleados.getInstancia();
		estudiantes = Estudiantes.getInstancia();
		pacientes = Pacientes.getInstancia();
		habitaciones = Habitaciones.getInstancia();
		facturas = Facturas.getInstancia();
		recursos = Recursos.getInstancia();
	}

	/**
	 * Muestra el menu principal
	 */
	public void mostrarMenuPrincipal() {
		List<String> opciones = Arrays.asList("GERENCIA Y GESTIÓN DE SERVICIOS GENERALES DEL HOSPITAL", "GESTIÓN DE ESTUDIANTES",
				"GESTIÓN DE MEDICINA", "GESTIÓN DE ENFERMERÍA", "GESTIÓN DE PACIENTES", "GESTIÓN DE SOPORTE", "SALIR");
		String opcion;
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("MENU PRINCIPAL", opciones);

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");

			if (opcion == null)
				return;

			switch (opcion) {
			case "1":
				mostrarMenuGestionHospital();
				break;
			case "2":
				mostrarMenuGestionEstudiantes();
				break;
			case "3":
				mostrarMenuGestionMedicina();
				break;
			case "4":
				mostrarMenuGestionEnfermeria();
				break;
			case "5":
				mostrarMenuGestionPacientes();
				break;
			case "6":
				mostrarMenuGestionSoporte();
				break;
			case "7":
				navegar = true;
				break;
			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");

			}

		} while (!navegar);
	}

	/**
	 * Muestra el menu de gestión del hospital
	 */
	public void mostrarMenuGestionHospital() {
		List<String> opciones = Arrays.asList("Dar de alta a personal", "Dar de baja a personal", "Modificar empleado",
				"Mostrar empleados", "Buscar empleados por DNI", "Asignar turno", "Expedir factura", "Mostrar empleados clasificados por unidad", "Listar las agendas de medicos y enfemeros", "Volver");
		String opcion;
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GERENCIA Y GESTIÓN DE SERVICIOS GENERALES DEL HOSPITAL", opciones);

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");

			if (opcion == null)
				return;

			switch (opcion) {
			case "1":
				empleados.darDeAltaEmpleado(scanner);
				break;
			case "2":
				empleados.darDeBajaEmpleado();
				break;
			case "3":
				empleados.modificarEmpleado();
				break;
			case "4":
				System.out.println("EMPLEADOS DADOS DE ALTA");
				System.out.println(empleados.toString());
				break;
			case "5":
				empleados.mostrarEmpleadoBuscadoPorDNI();
				break;
			case "6":
				empleados.asignarTurno();
				break;
			case "7":
				facturas.generarFactura();
				break;
			case "8":
				empleados.mostrarEmpleadosClasificadosPorUnidad();
				break;
			case "9":
				MostrarAgenda.mostrarTodasLasAgendas();
				break;
			case "10":
				return;
			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}

	/**
	 * Muestra el menu de gestión de los estudiantes
	 */
	public void mostrarMenuGestionEstudiantes() {
		List<String> opciones = Arrays.asList("Dar de alta a estudiante", "Dar de baja a estudiante",
				"Mostrar estudiantes", "Volver");
		String opcion;
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE ESTUDIANTES DEL HOSPITAL", opciones);

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");

			if (opcion == null)
				return;

			switch (opcion) {
			case "1":
				estudiantes.darDeAltaEstudiante();
				break;
			case "2":
				estudiantes.darDeBajaEstudiante();
				break;
			case "3":
				estudiantes.mostrarEstudiantes();
				break;
			case "4":
				navegar = true;
				break;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}

	/**
	 * Muestra el menu de gestión de medicina
	 */
	public void mostrarMenuGestionMedicina() {
		List<String> opciones = Arrays.asList("Ver agenda de medico", "Añadir una cita", "Eliminar cita",
				"Actualizar expediente de paciente", "Volver");
		String opcion;
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE MEDICINA DEL HOSPITAL", opciones);

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			Empleado empleado;
			switch (opcion) {
			case "1":
				empleado = empleados.buscarEmpleadoPorDNI();
				if (empleado != null) {
					PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsMedico(empleado);
					if (personalSanitario != null) {
						MostrarAgenda.mostrarAgendaPorFecha(personalSanitario.getAgenda());
					}
				}
				break;
			case "2":
				GestionarAgenda.añadirCita(TipoPersonal.MEDICO);
				break;
			case "3":
				empleado = empleados.buscarEmpleadoPorDNI();
				if (empleado != null) {
					PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsMedico(empleado);

					if (personalSanitario != null) {
						Agenda agenda = personalSanitario.getAgenda();
						LocalDate fecha = EntradaValores
								.introducirFecha("Introduzca la fecha en la que quiere eliminar la cita: ");
						MostrarAgenda.mostrarAgenda(agenda, fecha);
						LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio: ");
						LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin: ");

						LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
						LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);

						boolean citaNoExiste = agenda.comprobarCitaNoExiste(fecha, horaInicio,
								horaFin);
						
						Cita cita = agenda.buscarCita(fecha, horaInicio, horaFin);
						

						if (cita == null) {
							System.out.println("La cita no existe");
						} else {
							agenda.eliminarCita(fecha, cita);
							System.out.println("Cita eliminada correctamente");
						}

					}
				}
				break;
			case "4":
				pacientes.actualizarExpedientePaciente();
				break;
			case "5":
				navegar = true;
				break;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}

	/**
	 * Muestra el menu de gestión de los enfermería
	 */
	public void mostrarMenuGestionEnfermeria() {
		List<String> opciones = Arrays.asList("Ver agenda del enfermero", "Añadir una cita", "Eliminar una cita",
				"Actualizar expediente de paciente", "Volver");
		String opcion;
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE ENFERMERÍA DEL HOSPITAL", opciones);

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			Empleado empleado;
			switch (opcion) {
			case "1":
				empleado = empleados.buscarEmpleadoPorDNI();
				if (empleado != null) {
					PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsEnfermero(empleado);
					if (personalSanitario != null) {
						MostrarAgenda.mostrarAgendaPorFecha(personalSanitario.getAgenda());
					}
				} else {
					System.out.println("Proceso cancelado.");
				}
				break;
			case "2":
				GestionarAgenda.añadirCita(TipoPersonal.ENFERMERO);
				break;
			case "3":
				empleado = empleados.buscarEmpleadoPorDNI();
				if (empleado != null) {
					PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsEnfermero(empleado);

					if (personalSanitario != null) {
						Agenda agenda = personalSanitario.getAgenda();
						LocalDate fecha = EntradaValores
								.introducirFecha("Introduzca la fecha en la que quiere eliminar la cita: ");
						MostrarAgenda.mostrarAgenda(agenda, fecha);
						LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio: ");
						LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin: ");

						LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
						LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);

						boolean citaNoExiste = agenda.comprobarCitaNoExiste(fecha, horaInicio,
								horaFin);
						
						Cita cita = agenda.buscarCita(fecha, horaInicio, horaFin);
						

						if (cita == null) {
							System.out.println("La cita no existe");
						} else {
							agenda.eliminarCita(fecha, cita);
							System.out.println("Cita eliminada correctamente");
						}

					}
				}
				break;
			case "4":
				pacientes.actualizarExpedientePaciente();
				break;
			case "5":
				navegar = true;
				break;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}

	/**
	 * Muestra el menu de gestión de los pacientes
	 */
	public void mostrarMenuGestionPacientes() {
		List<String> opciones = Arrays.asList("Añadir paciente", "Eliminar paciente", "Mostrar pacientes",
				"Buscar paciente por DNI", "Ingresar paciente", "Dar el alta a paciente ingresado",
				"Mostrar habitaciones", "Mostrar pacientes ingresados", "Mostrar pacientes que tiene cada miembro del personal médico en una fecha "
						+ "concreta.", "Mostrar pacientes con citas en la agenda de consultas externas", "Pacientes que tienen que ver un especialista en un periodo", "Volver");
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE PACIENTES DEL HOSPITAL", opciones);

			String opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			switch (opcion) {
			case "1":
				pacientes.darDeAltaPaciente();
				break;
			case "2":
				pacientes.darDeBajaPaciente();
				break;
			case "3":
				System.out.println(pacientes);
				break;
			case "4":
				System.out.println(pacientes.buscarPacientePorDNI());
				break;
			case "5":
				pacientes.ingresarPaciente();
				break;
			case "6":
				pacientes.darAltaPacienteIngresado();
				break;
			case "7":
				System.out.println(habitaciones.toString());
				break;
			case "8":
				pacientes.mostrarPacientesIngresados();
				break;
			case "9":
				pacientes.mostrarPacientesCadaMiembroPersonalMedico();
				break;
			case "10":
				pacientes.mostrarPacientesAgendaConsultasExternas();
			case "11":
				pacientes.mostrarPacientesEspecialistaEnPeriodo();
				break;
			case "12":
				return;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}
	
	/**
	 * Muestra el menu de gestión del soporte
	 */
	public void mostrarMenuGestionSoporte() {
		List<String> opciones = Arrays.asList("Añadir recurso dañado", "Eliminar recurso dañado", "Mostrar recursos", "Volver");
		boolean navegar = false;

		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE SOPORTE", opciones);

			String opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			switch (opcion) {
			case "1":
				recursos.darDeAltaRecurso();
				break;
			case "2":
				recursos.darDeBajaRecurso();
				break;
			case "3":
				System.out.println(recursos);
				break;
			case "4":
				return;

			default:
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

		} while (!navegar);
	}
}
