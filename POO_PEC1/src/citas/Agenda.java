package citas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.HoraConsulta;
import model.Horario;
import model.HorariosPorTurno;
import usuarios.PersonalSanitario;
import util.Cadenas;
import util.FormatosFechas;

public class Agenda {

	private Map<LocalDate, List<Cita>> agenda;
	private Horario horario;

	public Agenda(Map<LocalDate, List<Cita>> agenda) {
		super();
		this.agenda = agenda;
		this.horario = HorariosPorTurno.HORARIO_DIA.getHorario();
	}

	public Agenda() {
		super();
		this.agenda = new TreeMap<LocalDate, List<Cita>>();
	}
	
//	public Agenda(Horario horario) {
//		super();
//		horario.get
//		this.listaCitas = listaCitas;
//	}

	public List<Cita> getListaCitas(LocalDate fecha) {
		return agenda.get(fecha);
	}
	
	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	public void anyadirCita(LocalDate fecha, Cita cita) {
		List<Cita> citas = this.agenda.get(fecha);
		citas.add(cita);
		this.agenda.replace(fecha, citas);
	}
	
	public void eliminarCita(LocalDate fecha, Cita cita) {
		List<Cita> citas = this.agenda.get(fecha);
		citas.remove(cita);
		this.agenda.replace(fecha, citas);
	}
	
	public boolean comprobarCitaEstaDisponible(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
//		boolean horaDeInicioDisponbile = false;
		
		if(comprobarHoraExisteEnHorario(horaInicio, horaFin) && comprobarCitaNoExiste(fecha, horaInicio, horaFin)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean comprobarHoraExisteEnHorario(LocalDateTime horaInicio, LocalDateTime horaFin) {
		List<HoraConsulta> listaHorasConsultas = horario.getListaHorasConsultas();
		
		if(listaHorasConsultas == null) {
			return false;
		}
		
		for (HoraConsulta horaConsulta : horario.getListaHorasConsultas()) {
			if(horaConsulta.getHoraInicio().equals(horaInicio) && horaConsulta.getHoraFin().equals(horaFin)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean comprobarCitaNoExiste(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
		List<Cita> citas = agenda.get(fecha);
		
		if(citas == null) {
			return true;
		} else {
			for (Cita cita : citas) {
				if(cita.getFechaInicio().equals(horaInicio) && cita.getFechaFin().equals(horaFin) && cita.getPaciente() != null) {
					return false;
				}
			}
		}
		return true;
	}

//	@Override
//	public String toString() {
//		return mostrarCitas();
//	}
	
//	public String mostrarCitas() {
//		
//		int longitudDefectoPaciente = longitudMayorNombrePaciente(listaCitas);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//		String columnaPaciente = Cadenas.repeatString("-", longitudDefectoPaciente);
//		
//		String formatoColumnaPaciente = "%-" + (longitudDefectoPaciente-2) + "s";
//		String citas = "";
//		
//		citas += ("---------------------------------------------------------+"+ columnaPaciente  +"+" + "\n");
//		citas += String.format("|    Fecha    | Hora de Inicio | Hora de Fin | Reservado | " + formatoColumnaPaciente + " |" + "\n", "Paciente");
//		citas +=("---------------------------------------------------------+"+ columnaPaciente + "+" + "\n");
//	    
//	    // Imprimir cada cita en la lista
//	    for (HoraConsulta horaConsulta : horario.getListaHorasConsultas()) {
//	        citas += imprimirFilaCita(horaConsulta, formatter, longitudDefectoPaciente);
//	    }
//
//	    // Línea de cierre de la tabla
//	    citas += imprimirLineaCierre(longitudDefectoPaciente);
//	    
//	    return citas;
//	}
//	
//	private int longitudMayorNombrePaciente(List<Cita> citas) {
//		int longitudMayorDeNombre = 10;
//		for (Cita cita : citas) {
//			String nombre = cita.getEmpleado().getNombre();
//			String apellidos = cita.getEmpleado().getApellidos();
//			
//			String nombreCompleto = nombre + " " + apellidos;
//			if(nombreCompleto.length() > longitudMayorDeNombre) {
//				longitudMayorDeNombre = nombreCompleto.length();
//			}
//		}
//		return longitudMayorDeNombre;
//	}
//	
//	private String imprimirFilaCita(Cita cita, DateTimeFormatter formatter, int anchoNombreMaximo) {
//		String dia = cita.getFechaInicio() != null ? cita.getFechaInicio().format(FormatosFechas.FORMATO_DIA.getFormatter()) : "-";
//	    String fechaInicio = cita.getFechaInicio() != null ? cita.getFechaInicio().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
//	    String fechaFin = cita.getFechaFin() != null ? cita.getFechaFin().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
//	    String reservado = cita.isReservado() ? "Sí" : "No";
//	    String nombrePaciente = cita.getPaciente() != null ? cita.getPaciente().getNombre() : "";
//
//	    // Determinar el ancho de la columna para el nombre del paciente
//	    int anchoColumnaPaciente = Math.max(anchoNombreMaximo, 12); // Mínimo ancho de columna de 12 caracteres
//	    String formatoColumnaPaciente = "%-" + (anchoColumnaPaciente -2) + "s";
//
//	    return String.format("| %-11s | %-14s | %-11s | %-9s | " + formatoColumnaPaciente + " |\n",
//	    		dia, fechaInicio, fechaFin, reservado, nombrePaciente);
//	}
//
//	private String imprimirLineaCierre(int anchoNombreMaximo) {
//	    String lineaCierre = "------------------------------------------------------------------";
//	    int longitudAdicional = Math.max(0, anchoNombreMaximo - 8); // Determinar longitud adicional basada en el nombre del paciente
//	    for (int i = 0; i < longitudAdicional; i++) {
//	        lineaCierre += "-"; // Agregar guiones adicionales según la longitud del nombre del paciente
//	    }
//	    return lineaCierre;
//	}
}
