package citas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.HoraConsulta;
import model.Horario;
import model.HorariosPorTurno;

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
	
	public Cita buscarCita(LocalDate fecha, LocalDateTime horaInicio, LocalDateTime horaFin) {
		List<Cita> citas = agenda.get(fecha);
		
		if(citas == null) {
			return null;
		} else {
			for (Cita cita : citas) {
				if(cita.getFechaInicio().equals(horaInicio) && cita.getFechaFin().equals(horaFin)) {
					return cita;
				}
			}
			return null;
		}
	}
}
