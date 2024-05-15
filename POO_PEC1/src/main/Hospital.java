package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import campus.Habitacion;
import campus.Habitaciones;
import citas.Agenda;
import citas.Cita;
import model.Expediente;
import model.Recurso;
import model.Recursos;
import model.Seguro;
import model.Unidad;
import ui.Menus;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Estudiante;
import usuarios.Estudiantes;
import usuarios.Paciente;
import usuarios.Pacientes;
import usuarios.PersonalSanitario;

public class Hospital {

	public static void main(String[] args) {

		iniciar();
		
		// Iniciar menú principal
		Menus menus = new Menus();
		menus.mostrarMenuPrincipal();

	}
	
	/**
	 * Crea una lista de datos para poder realizar pruebas y trabajar sobre ellos.
	 */
	public static void iniciar() {
		Empleados empleados = Empleados.getInstancia();
		
		Empleado empleadoTecnico = new Empleado("Elena", "Gómez Fernández", "23456789L", "712345678", Unidad.ADMINISTRACION);
		
		empleados.addEmpleado(new Empleado("Ana", "García López", "23456789A", "612345678", Unidad.CONSULTAS_EXTERNAS));
        empleados.addEmpleado(new Empleado("Pedro", "Martínez Rodríguez", "34567890B", "623456789", Unidad.UCI));
        empleados.addEmpleado(new Empleado("María", "Fernández Pérez", "45678901C", "634567890", Unidad.HOSPITALIZACION));
        empleados.addEmpleado(new Empleado("Juan", "Sánchez Gómez", "56789012D", "645678901", Unidad.PRUEBAS_MEDICAS));
        empleados.addEmpleado(new Empleado("Laura", "Díaz Martín", "67890123E", "656789012", Unidad.CARDIOVASCULAR));
        empleados.addEmpleado(new Empleado("Carlos", "López Sánchez", "78901234F", "667890123", Unidad.DIABETES));
        empleados.addEmpleado(new Empleado("Sofía", "Pérez Gómez", "89012345G", "678901234", Unidad.URGENCIAS));
        empleados.addEmpleado(new Empleado("Daniel", "González Díaz", "90123456H", "689012345", Unidad.FORMACION));
        empleados.addEmpleado(new Empleado("Lucía", "Sánchez Pérez", "01234567J", "690123456", Unidad.CAFETERIA));
        empleados.addEmpleado(new Empleado("Mario", "Ramírez Martínez", "12345678K", "701234567", Unidad.APARCAMIENTO));
        empleados.addEmpleado(new Empleado("Elena", "Gómez Fernández", "23456789L", "712345678", Unidad.ADMINISTRACION));
        empleados.addEmpleado(new Empleado("David", "Martín López", "34567890M", "723456789", Unidad.CONSULTAS_EXTERNAS));
        empleados.addEmpleado(new Empleado("Carmen", "Pérez García", "45678901N", "734567890", Unidad.UCI));
        empleados.addEmpleado(new Empleado("Javier", "Sánchez Rodríguez", "56789012O", "745678901", Unidad.HOSPITALIZACION));
        empleados.addEmpleado(new Empleado("Marina", "Fernández Díaz", "67890123P", "756789012", Unidad.PRUEBAS_MEDICAS));
		Empleado empleado = new Empleado("Christian", "Jurado Maique", "18098277W", "629932957", Unidad.ADMINISTRACION);
		Paciente paciente = new Paciente("Pedro", "Plana", "18475122X", "645142541", Seguro.SEGURIDAD_SOCIAL,
				"AR1243451314", null);
		LocalDateTime ahora = LocalDateTime.now();

		List<Cita> listaCitasMedico = new ArrayList<Cita>();
		listaCitasMedico.add(new Cita(paciente,
				LocalDateTime.of(ahora.getYear(), ahora.getMonth(), ahora.getDayOfMonth(), 7, 0),
				LocalDateTime.of(ahora.getYear(), ahora.getMonth(), ahora.getDayOfMonth(), 8, 0), true));
		
		List<Cita> listaCitasEnfermero = new ArrayList<Cita>();
		listaCitasEnfermero.add(new Cita(paciente,
				LocalDateTime.of(ahora.getYear(), ahora.getMonth(), ahora.getDayOfMonth(), 11, 0),
				LocalDateTime.of(ahora.getYear(), ahora.getMonth(), ahora.getDayOfMonth(), 12, 0), true));
		
		// Agenda del medico
		Map<LocalDate, List<Cita>> agendaMedico = new HashMap<LocalDate, List<Cita>>();
		agendaMedico.put(LocalDate.now(), listaCitasMedico);
		
		//Agenda del enfermero
		Map<LocalDate, List<Cita>> agendaEnfermero = new HashMap<LocalDate, List<Cita>>();
		agendaEnfermero.put(LocalDate.now(), listaCitasEnfermero);

		// Personal Sanitario de la unidad de MEDICINA
		PersonalSanitario medico = new PersonalSanitario("María", "Latorre", "18098278W", "624142536",
				Unidad.CARDIOVASCULAR, new Agenda(agendaMedico));
		
		// Personal Sanitario de la unidad de ENFERMERÍA
		PersonalSanitario enfermero = new PersonalSanitario("Laura", "Pinies", "14542214R", "645124512", Unidad.CONSULTAS_EXTERNAS, new Agenda(agendaEnfermero));
		
		empleados.addEmpleado(empleado);
		empleados.addEmpleado(medico);
		empleados.addEmpleado(enfermero);

		Estudiantes estudiantes = Estudiantes.getInstancia();
		estudiantes.addEstudiante(new Estudiante("Pedro", "Fraga", "141654214X", "645124521", empleado));
		
		Pacientes pacientes = Pacientes.getInstancia();
		pacientes.addPaciente(paciente);
		pacientes.addPaciente(new Paciente("Orosco", "Salinas Zambrano", "74891365F", "645474124", Seguro.SEGURIDAD_SOCIAL, "053700291753", new ArrayList<Expediente>()));
		pacientes.addPaciente(new Paciente("Juan", "García López", "12345678A", "912345678", Seguro.NO_ASEGURADO, "123456789012", new ArrayList<>()));
		pacientes.addPaciente(new Paciente("María", "Martínez Rodríguez", "23456789B", "645678901", Seguro.SEGURO_MEDICO, "234567890123", new ArrayList<>()));
		pacientes.addPaciente(new Paciente("David", "Fernández Pérez", "34567890C", "656789012", Seguro.SEGURIDAD_SOCIAL, "345678901234", new ArrayList<>()));
		pacientes.addPaciente(new Paciente("Ana", "Sánchez Gómez", "45678901D", "678901234", Seguro.SEGURO_MEDICO, "456789012345", new ArrayList<>()));
		pacientes.addPaciente(new Paciente("Laura", "Díaz Ruiz", "56789012E", "689012345", Seguro.SEGURIDAD_SOCIAL, "567890123456", new ArrayList<>()));
		pacientes.addPaciente(new Paciente("Carlos", "Romero Sánchez", "67890123F", "701234567", Seguro.SEGURIDAD_SOCIAL, "678901234567", new ArrayList<>()));
		
		Habitaciones habitaciones = Habitaciones.getInstancia();
		Habitacion habitacion = habitaciones.getHabitaciones().get(0);
		pacientes.ingresarPaciente(paciente, habitacion);
		
		Recursos recursos = Recursos.getInstancia();
		recursos.addRecurso(new Recurso("Impresora", "No imprime correctamente", empleadoTecnico));
		recursos.addRecurso(new Recurso("Portátil", "Pantalla rota", empleadoTecnico));
		recursos.addRecurso(new Recurso("Teléfono móvil", "Batería descargada rápidamente", empleadoTecnico));
		recursos.addRecurso(new Recurso("Monitor", "No muestra imagen", empleadoTecnico));
		recursos.addRecurso(new Recurso("Teclado", "Teclas atascadas", empleadoTecnico));
		recursos.addRecurso(new Recurso("Ratón", "No responde", empleadoTecnico));
		recursos.addRecurso(new Recurso("Cámara web", "Imagen borrosa", empleadoTecnico));
		recursos.addRecurso(new Recurso("Altavoces", "Sonido distorsionado", empleadoTecnico));
		recursos.addRecurso(new Recurso("Router", "Conexión intermitente", empleadoTecnico));
		recursos.addRecurso(new Recurso("Escáner", "No reconoce documentos", empleadoTecnico));


	}
}
