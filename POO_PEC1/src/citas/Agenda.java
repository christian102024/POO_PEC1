package citas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.HoraConsulta;
import model.Horario;
import model.HorariosPorTurno;
import model.Turno;

/**
 * Representa la agenda de citas.
 */
public class Agenda {

	private Map<LocalDate, List<Cita>> agenda;
	private Horario horario;

    /**
     * Constructor de la clase Agenda.
     * 
     * @param agenda La agenda de citas.
     */
	public Agenda(Map<LocalDate, List<Cita>> agenda, Turno turno) {
		super();
		this.agenda = agenda;
		if(turno.equals(Turno.DIA)) {
			this.horario = HorariosPorTurno.HORARIO_DIA.getHorario();			
		} else {
			this.horario = HorariosPorTurno.HORARIO_NOCHE.getHorario();
		}
	}

    /**
     * Constructor por defecto de la clase Agenda.
     */
	public Agenda(Turno turno) {
		super();
		this.agenda = new TreeMap<LocalDate, List<Cita>>();
		if(turno.equals(Turno.DIA)) {
			this.horario = HorariosPorTurno.HORARIO_DIA.getHorario();			
		} else {
			this.horario = HorariosPorTurno.HORARIO_NOCHE.getHorario();
		}
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
		if(citas == null) {
			addAgendaDate(fecha);
			citas = this.agenda.get(fecha);
		}
		citas.add(cita);
		this.agenda.replace(fecha, citas);
	}
	
	public void eliminarCita(LocalDate fecha, Cita cita) {
		List<Cita> citas = this.agenda.get(fecha);
		citas.remove(cita);
		this.agenda.replace(fecha, citas);
	}
	
	public void addAgendaDate(LocalDate fecha) {
		agenda.put(fecha, new ArrayList<Cita>());
	}
	
	public boolean comprobarCitaEstaDisponible(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
		System.out.println("comprobarHoraExiste: " + comprobarHoraExisteEnHorario(horaInicio, horaFin));
		System.out.println("citaNoEXISTE: " + comprobarCitaNoExiste(fecha, horaInicio, horaFin));
		if(comprobarHoraExisteEnHorario(horaInicio, horaFin) && comprobarCitaNoExiste(fecha, horaInicio, horaFin)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean comprobarHoraExisteEnHorario(LocalTime horaInicio, LocalTime horaFin) {
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
	
	public boolean comprobarCitaNoExiste(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
		List<Cita> citas = agenda.get(fecha);
		
		if(citas == null) {
			return true;
		} else {
			for (Cita cita : citas) {
				if(cita.getFechaInicio().toLocalTime().equals(horaInicio) && cita.getFechaFin().toLocalTime().equals(horaFin) && cita.getPaciente() != null) {
					return false;
				}
			}
		}
		return true;
	}
	
	public Cita buscarCita(LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
		List<Cita> citas = agenda.get(fecha);
		
		if(citas == null) {
			return null;
		} else {
			for (Cita cita : citas) {
				if(cita.getFechaInicio().toLocalTime().equals(horaInicio) && cita.getFechaFin().toLocalTime().equals(horaFin)) {
					return cita;
				}
			}
			return null;
		}
	}
	
    @Override
    public String toString() {
        String cadena = "";
        for (LocalDate fecha : agenda.keySet()) {
            cadena += fecha + ":\n";
            List<Cita> citas = agenda.get(fecha);
            for (Cita cita : citas) {
                cadena += "\t\t\t" + cita + "\n";
            }
        }
        return cadena;
    }
}
