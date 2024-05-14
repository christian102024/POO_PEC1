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
import citas.MostrarAgenda;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Estudiantes;
import usuarios.Paciente;
import usuarios.Pacientes;
import usuarios.PersonalSanitario;
import util.EntradaValores;

public class Menus {
	
	private static Scanner scanner;
	private Empleados empleados;
	private Estudiantes estudiantes;
	private Pacientes pacientes;
	private Habitaciones habitaciones;
	
	public Menus() {
		super();
		scanner = new Scanner(System.in);
		empleados = Empleados.getInstancia();
		estudiantes = Estudiantes.getInstancia();
		pacientes = Pacientes.getInstancia();
		habitaciones = Habitaciones.getInstancia();
	}

	public Menus(Empleados empleados, Estudiantes estudiantes, Pacientes pacientes, Habitaciones habitaciones) {
		super();
		scanner = new Scanner(System.in);
		this.empleados = empleados;
		this.estudiantes = estudiantes;
		this.pacientes = pacientes;
		this.habitaciones = habitaciones;
	}


	public void mostrarMenuPrincipal() {
		List<String> opciones = Arrays.asList("GESTIÓN DE PERSONAL DEL HOSPITAL", "GESTIÓN DE ESTUDIANTES", "GESTIÓN DE MEDICINA", "GESTIÓN DE ENFERMERÍA", "GESTIÓN DE PACIENTES", "GESTIÓN DE SOPORTE", "SALIR");
		String opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("MENU PRINCIPAL", opciones);
			
			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			
			if(opcion == null) return;
			
			switch(opcion) {
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
				System.out.println("NO IMPLEMENTADO");
				break;
			case "7":
				navegar = true;
				break;
			default:
            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				
			}
			
				
	
		} while (!navegar);
	}

	public void mostrarMenuGestionHospital() {
		List<String> opciones = Arrays.asList("Dar de alta a personal", "Dar de baja a personal", "Modificar expediente", "Mostrar empleados", "Buscar empleados por DNI", "Asignar turno", "Volver");
		String opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE PERSONAL DEL HOSPITAL", opciones );
			System.out.print("Seleccione una opción: ");

				opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
				
				if(opcion == null) return;
				
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
		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}

			
		} while(!navegar);
	}
	
	public void mostrarMenuGestionEstudiantes() {
		List<String> opciones = Arrays.asList("Dar de alta a estudiante", "Dar de baja a estudiante", "Mostrar estudiantes", "Volver");
		String opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE ESTUDIANTES DEL HOSPITAL", opciones );

			opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
			
			if(opcion == null) return;
				
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
//		            case 5:
//		            	empleados.buscarEmpleadoPorDNI();
//		            	break;
//		            case 6:
//		            	empleados.asignarTurno();
//		            	break;
//		            case 7:
//		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}

			
		} while(!navegar);
	}
	
	public void mostrarMenuGestionMedicina() {
		List<String> opciones = Arrays.asList("Ver agenda de medico", "Añadir una cita", "Actualizar expediente de paciente", "Volver");
		String opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE MEDICINA DEL HOSPITAL", opciones );

				opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
				Empleado empleado;
				switch (opcion) {
		            case "1":
		            	empleado = empleados.buscarEmpleadoPorDNI();
		            	if(empleado != null ) {
		            		PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsMedico(empleado);
		            		if(personalSanitario != null) {
		            			MostrarAgenda.mostrarAgendaPorFecha(personalSanitario.getAgenda());
		            		}		            		
		            	}
		                break;
		            case "2":
		            	empleado = empleados.buscarEmpleadoPorDNI();
		            	if(empleado != null) {
		            		PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsMedico(empleado);
		            		
		            		if(personalSanitario != null) {
		            			Agenda agenda = personalSanitario.getAgenda();
		            			LocalDate fecha = EntradaValores.introducirFecha("Introduzca la fecha en la que quiere dar de alta la cita: ");
		            			MostrarAgenda.mostrarAgenda(agenda, fecha);
//		            			while (citaNoDisponbile) {
		            				LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio: ");
		            				LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin: ");
		            				Paciente paciente = pacientes.buscarPacientePorDNI();
		            				
		            				if(paciente == null) {
		            					System.out.println("Paciente no encontrado.");
		            				} else {
		            					
		            					LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
		            					LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);
		            					
		            					boolean citaDisponible = agenda.comprobarCitaEstaDisponible(fecha, fechaHoraInicio, fechaHoraFin);
		            					
		            					if(citaDisponible) {
		            						agenda.anyadirCita(fecha, new Cita(paciente, fechaHoraInicio, fechaHoraFin, true));
		            						System.out.println("Cita registrada correctamente!");
		            					}
		            				}
		            				
//		            			}
		            			
		            			
//		            			personalSanitario.getAgenda().anyadirCita(fecha, new Cita(null, horaInicio, horaFin, true));
//		            			MostrarAgenda.mostrarAgendaPorFecha(personalSanitario.getAgenda());
		            			
		            		}	
		            	} else {
		            		System.out.println("Proceso cancelado.");
		            	}
		            	break;
		            case "3":
		            	pacientes.actualizarExpedientePaciente();
		                break;
		            case "4":
		            	navegar = true;
		            	break;
//		            case 5:
//		            	empleados.buscarEmpleadoPorDNI();
//		            	break;
//		            case 6:
//		            	empleados.asignarTurno();
//		            	break;
//		            case 7:
//		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}
				
		} while(!navegar);
	}
	
	public void mostrarMenuGestionEnfermeria() {
		List<String> opciones = Arrays.asList("Ver agenda del enfermero", "Añadir una cita", "Actualizar expediente de paciente", "Volver");
		String opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE ENFERMERÍA DEL HOSPITAL", opciones );

				opcion = EntradaValores.introducirCadena("Seleccione una opción: ");
				Empleado empleado;
				switch (opcion) {
		            case "1":
		            	empleado = empleados.buscarEmpleadoPorDNI();
		            	if(empleado != null ) {
		            		PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsEnfermero(empleado);
		            		if(personalSanitario != null) {
		            			MostrarAgenda.mostrarAgendaPorFecha(personalSanitario.getAgenda());
		            		}		            		
		            	} else {
		            		System.out.println("Proceso cancelado.");
		            	}
		                break;
		            case "2":
		            	empleado = empleados.buscarEmpleadoPorDNI();
		            	if(empleado != null) {
		            		PersonalSanitario personalSanitario = PersonalSanitario.comprobarEmpleadoEsEnfermero(empleado);
		            		
		            		if(personalSanitario != null) {
		            			Agenda agenda = personalSanitario.getAgenda();
		            			LocalDate fecha = EntradaValores.introducirFecha("Introduzca la fecha en la que quiere dar de alta la cita: ");
		            			MostrarAgenda.mostrarAgenda(agenda, fecha);
//		            			while (citaNoDisponbile) {
		            				LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio: ");
		            				LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin: ");
		            				Paciente paciente = pacientes.buscarPacientePorDNI();
		            				
		            				if(paciente == null) {
		            					System.out.println("Paciente no encontrado.");
		            				} else {
		            					
		            					LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
		            					LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);
		            					
		            					boolean citaDisponible = agenda.comprobarCitaEstaDisponible(fecha, fechaHoraInicio, fechaHoraFin);
		            					
		            					if(citaDisponible) {
		            						agenda.anyadirCita(fecha, new Cita(paciente, fechaHoraInicio, fechaHoraFin, true));
		            						System.out.println("Cita registrada correctamente!");
		            					}
		            				}
		            			
		            		}	
		            	} else {
		            		System.out.println("Proceso cancelado.");
		            	}
		            	break;
		            case "3":
		            	pacientes.actualizarExpedientePaciente();
		                break;
		            case "4":
		            	navegar = true;
		            	break;
//		            case 5:
//		            	empleados.buscarEmpleadoPorDNI();
//		            	break;
//		            case 6:
//		            	empleados.asignarTurno();
//		            	break;
//		            case 7:
//		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}
				
		} while(!navegar);
	}
	
	public void mostrarMenuGestionPacientes() {
		List<String> opciones = Arrays.asList("Añadir paciente", "Eliminar paciente", "Mostrar pacientes", "Buscar paciente por DNI", "Ingresar paciente", "Mostrar habitaciones", "Volver");
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE PACIENTES DEL HOSPITAL", opciones );

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
		            	System.out.println(habitaciones.toString());
		            	break;
		            case "7":
		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}


			
		} while(!navegar);
	}
}
