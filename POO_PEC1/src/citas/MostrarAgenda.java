package citas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.HoraConsulta;
import model.Horario;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.PersonalSanitario;
import util.Cadenas;
import util.EntradaValores;
import util.FormatosFechas;

/**
 * Clase que proporciona métodos para mostrar la agenda de citas.
 */
public class MostrarAgenda {
	
    /**
     * Muestra la agenda de todos los empleados y personal sanitario.
     */
	public static void mostrarTodasLasAgendas() {
		Empleados empleados = Empleados.getInstancia();
		List<Empleado> listaEmpleados = empleados.getEmpleados();		
		List<PersonalSanitario> listaPersonalSanitario = new ArrayList<PersonalSanitario>();
		
		for (Empleado empleado : listaEmpleados) {
			PersonalSanitario personal = PersonalSanitario.convertirEmpleadoEnPersonalSanitario(empleado);
			if(personal != null) listaPersonalSanitario.add(personal);
		}
		
		for (PersonalSanitario personalSanitario : listaPersonalSanitario) {
			imprimirInformacionAgenda(personalSanitario);
		}

	}
	
	private static void imprimirInformacionAgenda(PersonalSanitario personalSanitario) {
		System.out.println("Nombre: " + personalSanitario.getNombre() + ", Apellidos: " + personalSanitario.getApellidos() + ", DNI: "  + personalSanitario.getDni() + ", Unidad: " + personalSanitario.getUnidad().getValor());
		System.out.println("\tListado de días apuntados en la agenda: ");
		System.out.println("\t\t" + personalSanitario.getAgenda());
	}

    /**
     * Muestra la agenda del día de hoy o de una fecha especificada y devuelve la fecha mostrada..
     * 
     * @param agenda La agenda de citas.
     * @return La fecha de la agenda mostrada.
     */
	public static LocalDate mostrarAgendaDevolverFecha(Agenda agenda) {
		boolean agendaHoy = EntradaValores.introducirValorBooleano("¿Quiere mostrar la agenda del día de hoy? (S/N)");
		
		if(agendaHoy) {
			mostrarAgenda(agenda, LocalDate.now());
			return LocalDate.now();
		} else {
			LocalDate fecha = EntradaValores.introducirFecha(null);
			mostrarAgenda(agenda, fecha);
			return fecha;
		}
	}
	
	/**
     * Muestra la agenda del día de hoy o de una fecha especificada.
     * 
     * @param agenda La agenda de citas.
     * @return La agenda mostrada (con su respectivo formato)
     */
	public static String mostrarAgendaPorFecha(Agenda agenda) {
		boolean agendaHoy = EntradaValores.introducirValorBooleano("¿Quiere mostrar la agenda del día de hoy? (S/N)");
		
		if(agendaHoy) {
			return mostrarAgenda(agenda, LocalDate.now());
		} else {
			LocalDate fecha = EntradaValores.introducirFecha(null);
			return mostrarAgenda(agenda, fecha);
		}
	}
	
    /**
     * Muestra la agenda del día de hoy o de una fecha especificada.
     * 
     * @param agenda La agenda de citas.
     * @param fecha La fecha para la cual se mostrará la agenda.
     * @return La agenda mostrada.
     */
	public static String mostrarAgenda(Agenda agenda, LocalDate fecha) {
		
		int longitudDefectoPaciente = longitudMayorNombrePaciente(agenda.getListaCitas(fecha));
		Horario horario = agenda.getHorario();

		String columnaPaciente = Cadenas.repetirCaracter("-", longitudDefectoPaciente);
		
		String formatoColumnaPaciente = "%-" + (longitudDefectoPaciente-2) + "s";
		String citas = "";
		
		citas +=("+-------------+----------------+-------------+-----------+"+ columnaPaciente  +"+" + "\n");
		citas += String.format("|    Fecha    | Hora de Inicio | Hora de Fin | Reservado | " + formatoColumnaPaciente + " |" + "\n", "Paciente");
		citas +=("+-------------+----------------+-------------+-----------+"+ columnaPaciente + "+" + "\n");
		
		for (HoraConsulta horaConsulta : horario.getListaHorasConsultas()) {
	        citas += imprimirFilaHoraConsulta(horaConsulta, longitudDefectoPaciente, agenda, fecha);
	    }
	    
	    

	    // Línea de cierre de la tabla
	    citas += imprimirLineaCierre(longitudDefectoPaciente);
	    
	    System.out.println(citas);
	    return citas;
	}
	
	private static int longitudMayorNombrePaciente(List<Cita> citas) {
		int longitudMayorDeNombre = 10;
		if(citas != null) {
			for (Cita cita : citas) {
				String nombre = cita.getPaciente().getNombre();
				String apellidos = cita.getPaciente().getApellidos();
				
				String nombreCompleto = nombre + " " + apellidos;
				if(nombreCompleto.length() > longitudMayorDeNombre) {
					longitudMayorDeNombre = nombreCompleto.length();
				}
			}
			
		}
		return longitudMayorDeNombre;
	}
	
	private static String imprimirFilaHoraConsulta(HoraConsulta horaConsulta, int anchoNombreMaximo, Agenda agenda, LocalDate fecha) {
		String dia = fecha != null ? fecha.format(FormatosFechas.FORMATO_DIA.getFormatter()) : "-";
	    String fechaInicio = horaConsulta.getHoraInicio() != null ? horaConsulta.getHoraInicio().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
	    String fechaFin = horaConsulta.getHoraFin() != null ? horaConsulta.getHoraFin().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
	    Cita cita = buscarCita(agenda, horaConsulta.getHoraInicio(), horaConsulta.getHoraFin(), fecha);
	    String reservado = "No";
	    String nombrePaciente = "-";
	    
	    if(cita != null) {
	    	reservado = cita.isReservado() ? "Sí" : "No";
	    	nombrePaciente = cita.getPaciente() != null ? cita.getPaciente().getNombre() : "-";	    	
	    }
	    
	    int anchoColumnaPaciente = Math.max(anchoNombreMaximo, 10); // Mínimo ancho de columna de 10 caracteres
	    String formatoColumnaPaciente = "%-" + (anchoColumnaPaciente -2) + "s";
	    
	    return String.format("| %-11s | %-14s | %-11s | %-9s | " + formatoColumnaPaciente + " |\n",
	    		dia, fechaInicio, fechaFin, reservado, nombrePaciente);
	}
	
	private static Cita buscarCita(Agenda agenda, LocalTime horaInicio, LocalTime horaFin, LocalDate fecha) {
		List<Cita> listaCitas = agenda.getListaCitas(fecha);
		if(listaCitas == null) {
			return null;
		} else {
			for (Cita cita : listaCitas) {
				if(cita.getFechaInicio().toLocalTime().equals(horaInicio) && cita.getFechaFin().toLocalTime().equals(horaFin)) {
					return cita;
				}
			}
			
		}
		return null;
	}
	
	private static String imprimirLineaCierre(int anchoNombreMaximo) {
	    String lineaCierre = "------------------------------------------------------------------";
	    int longitudAdicional = Math.max(0, anchoNombreMaximo - 8); // Determinar longitud adicional basada en el nombre del paciente
	    for (int i = 0; i < longitudAdicional; i++) {
	        lineaCierre += "-"; // Agregar guiones adicionales según la longitud del nombre del paciente
	    }
	    lineaCierre += "+";
	    return lineaCierre;
	}
}
