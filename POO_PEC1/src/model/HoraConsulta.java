package model;

import java.time.LocalTime;

/**
 * Representa una hora en la que se realiza una consulta.
 */
public class HoraConsulta {

	private LocalTime horaInicio;
	private LocalTime horaFin;
	
	public HoraConsulta(LocalTime horaInicio, LocalTime horaFin) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public HoraConsulta() {
		super();
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
}
