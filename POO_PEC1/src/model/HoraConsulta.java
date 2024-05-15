package model;

import java.time.LocalDateTime;

/**
 * Representa una hora en la que se realiza una consulta.
 */
public class HoraConsulta {

	private LocalDateTime horaInicio;
	private LocalDateTime horaFin;
	
	public HoraConsulta(LocalDateTime horaInicio, LocalDateTime horaFin) {
		super();
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}

	public HoraConsulta() {
		super();
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalDateTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalDateTime horaFin) {
		this.horaFin = horaFin;
	}
}
