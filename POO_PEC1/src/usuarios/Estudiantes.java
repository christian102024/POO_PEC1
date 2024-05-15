package usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import citas.Agenda;
import citas.Cita;
import citas.MostrarAgenda;
import model.Servicio;
import model.Unidad;
import model.Unidades;
import ui.MostrarMenu;
import util.EntradaValores;

public class Estudiantes {

	private static Estudiantes instancia;
	private List<Estudiante> estudiantes;

	public Estudiantes() {
		super();
		this.estudiantes = new ArrayList<Estudiante>();
	}
	
	public static Estudiantes getInstancia() {
		if(instancia == null) {
			instancia = new Estudiantes();
		}
		return instancia;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public void addEstudiante(Estudiante estudiante) {
		this.estudiantes.add(estudiante);
	}
	
	public void darDeAltaEstudiante() {
		Estudiante estudiante = new Estudiante();
		Scanner scanner = new Scanner(System.in);
		estudiante = pedirDatosEstudiante(scanner, estudiante);
		
		if(estudiante != null) {
			estudiantes.add(estudiante);
		}
	}
	
	private Estudiante pedirDatosEstudiante(Scanner scanner, Estudiante estudiante) {
		
		String nombre = EntradaValores.introducirCadena("Nombre: ", estudiante.getNombre());
		if(nombre.equals("cancelar")) {
			return null;
		}
		estudiante.setNombre(nombre);
		
		
		String apellidos = EntradaValores.introducirCadena("Apellidos: ", estudiante.getApellidos());
		if(apellidos.equals("cancelar")) {
			return null;
		}
		estudiante.setApellidos(apellidos);
		
		String dni = EntradaValores.introducirCadena("DNI: ", estudiante.getDni());
		if(dni.equals("cancelar")) {
			return null;
		}
		estudiante.setDni(dni);
		
		String telefono = EntradaValores.introducirCadena("Teléfono: ", estudiante.getTelefono());
		if(telefono.equals("cancelar")) {
			return null;
		}
		estudiante.setTelefono(telefono);
		
		boolean asignarPersonal = EntradaValores.introducirValorBooleano("¿Asignar personal al estudiante? (S/N): ");
		
		if(asignarPersonal) {
			asignarPersonalAlEmpleado(estudiante);
		} 
		return estudiante;
	}
	
	private void asignarPersonalAlEmpleado(Estudiante estudiante) {
		MostrarMenu.mostrarMenu("Seleccionar tipo de personal", Arrays.asList("Asignar personal médico", "Asignar personal de enfermería", "Asignar cita", "Cancelar"));
		int opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción: ", new int[] {1, 2, 3, 4});
		Empleados empleados = Empleados.getInstancia();
		
		
		switch(opcion) {
		case 1:
			Empleado medico = empleados.buscarEmpleadoPorDNI();
			if(medico != null && Unidades.getServiciosPorUnidad(medico.getUnidad()).contains(Servicio.MEDICINA)) {
				estudiante.setPersonalAsignado(medico);
			} else if(medico == null) {
				System.out.println("El empleado no ha sido encontrado.");
			} else {
				System.out.println("El empleado no pertenece al servicio de medicina.");
			}
			break;
		case 2:
			Empleado enfermero = empleados.buscarEmpleadoPorDNI();
			if(enfermero != null && Unidades.getServiciosPorUnidad(enfermero.getUnidad()).contains(Servicio.ENFERMERIA)) {
				estudiante.setPersonalAsignado(enfermero);
			} else if(enfermero == null) {
				System.out.println("El empleado no ha sido encontrado.");
			} else {
				System.out.println("El empleado no pertenece al servicio de enfermería.");
			}
			break;
		case 3:
			Empleado empleado = empleados.buscarEmpleadoPorDNI();
			if(empleado != null && Unidades.getServiciosPorUnidad(empleado.getUnidad()).contains(Servicio.MEDICINA) || Unidades.getServiciosPorUnidad(empleado.getUnidad()).contains(Servicio.ENFERMERIA)) {
				PersonalSanitario personalSanitario = PersonalSanitario.convertirEmpleadoEnPersonalSanitario(empleado);
				LocalDate fecha = MostrarAgenda.mostrarAgendaDevolverFecha(personalSanitario.getAgenda());
				
				Cita cita = seleccionarCita(personalSanitario.getAgenda(), fecha);
				
				if(cita != null) estudiante.setPersonalAsignado(cita);
				else System.out.println("La cita no ha sido encontrada.");
			} else if(empleado == null) {
				System.out.println("El empleado no ha sido encontrado.");
			} else {
				System.out.println("El empleado no es personal sanitario. No dispone de citas.");
			}
			break;
		case 4:
			break;
		}
		
		System.out.println("Estudiante creado correctamente");
	}
	
	private Cita seleccionarCita(Agenda agenda, LocalDate fecha) {
		LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio de la cita: ");
		LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin de la cita: ");
		
		LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
		LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);
		
		boolean horarioExiste = agenda.comprobarHoraExisteEnHorario(fechaHoraInicio, fechaHoraFin);
		
		boolean citaNoExiste = agenda.comprobarCitaEstaDisponible(fecha, fechaHoraInicio, fechaHoraFin);
		
		if(horarioExiste && !citaNoExiste) {
			List<Cita> listaCitas = agenda.getListaCitas(fecha);
			for (Cita cita : listaCitas) {
				if(cita.getFechaInicio().equals(fechaHoraInicio) && cita.getFechaFin().equals(fechaHoraFin)) {
					return cita;
				}
			}
		}
		
		return null;
	}
	
	public void darDeBajaEstudiante() {
		String dni = EntradaValores.introducirCadena("Introduzca el DNI del estudiante a eliminar: ");
		darDeBajaEstudiante(dni);
	}
	
	public void darDeBajaEstudiante(String dni) {
		int indice = buscarIndiceDeEstudiantePorDNI(dni);
		if(indice == -1) {
			System.out.println("Estudiante no encontrado.");
		} else {
			estudiantes.remove(indice);
		}
	}
	
	public void mostrarEstudiantes() {
		System.out.println("Estudiantes dados de alta: ");
		for(Estudiante estudiante: estudiantes) {
			System.out.println(estudiante);
		}
		if (estudiantes == null || estudiantes.size() == 0) {
			System.out.println("No hay estudiates dados de alta.");
		}
	}
	
	/**
	 * 
	 * @param dni
	 * @return El índice del estudiante en la lista de estudiantes
	 */
	public int buscarIndiceDeEstudiantePorDNI(String dni) {
		for (int i = 0; i < estudiantes.size(); i++) {
			Estudiante estudiante = estudiantes.get(i);
			
			if(estudiante.getDni().equals(dni)) {
				return i;
			}
		}
		return -1;
	}
	
	
}
