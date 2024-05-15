package citas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import model.HoraConsulta;
import model.Horario;
import usuarios.Paciente;
import util.Cadenas;
import util.EntradaValores;
import util.FormatosFechas;

public class MostrarAgenda {

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
	
	public static String mostrarAgendaPorFecha(Agenda agenda) {
		boolean agendaHoy = EntradaValores.introducirValorBooleano("¿Quiere mostrar la agenda del día de hoy? (S/N)");
		
		if(agendaHoy) {
			return mostrarAgenda(agenda, LocalDate.now());
		} else {
			LocalDate fecha = EntradaValores.introducirFecha(null);
			return mostrarAgenda(agenda, fecha);
		}
	}
	
	public static String mostrarAgenda(Agenda agenda, LocalDate fecha) {
		
		int longitudDefectoPaciente = longitudMayorNombrePaciente(agenda.getListaCitas(fecha));
		Horario horario = agenda.getHorario();

		String columnaPaciente = Cadenas.repeatString("-", longitudDefectoPaciente);
		
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String dia = horaConsulta.getHoraInicio() != null ? horaConsulta.getHoraInicio().format(FormatosFechas.FORMATO_DIA.getFormatter()) : "-";
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
	
	private static Cita buscarCita(Agenda agenda, LocalDateTime horaInicio, LocalDateTime horaFin, LocalDate fecha) {
		List<Cita> listaCitas = agenda.getListaCitas(fecha);
		if(listaCitas == null) {
			return null;
		} else {
			for (Cita cita : listaCitas) {
				if(cita.getFechaInicio().equals(horaInicio) && cita.getFechaFin().equals(horaFin)) {
					return cita;
				}
			}
			
		}
		return null;
	}
	
	private static Cita seleccionarCita(Agenda agenda, LocalTime horaInicio, LocalDate fecha) {
		List<Cita> listaCitas = agenda.getListaCitas(fecha);
		if(listaCitas == null) {
			return null;
		} else {
			for (Cita cita : listaCitas) {
				if(cita.getFechaInicio().equals(LocalDateTime.of(fecha, horaInicio))) {
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
