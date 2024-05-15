package citas;

import java.time.LocalDateTime;

import usuarios.Paciente;

/**
 * Representa una cita médica.
 */
public class Cita {
    private Paciente paciente;          // El paciente asociado a la cita
    private LocalDateTime fechaInicio;  // La fecha y hora de inicio de la cita
    private LocalDateTime fechaFin;     // La fecha y hora de fin de la cita
    private boolean reservado;          // Indica si la cita está reservada o no
	
	/**
     * Constructor de la clase Cita.
     * 
     * @param paciente     El paciente asociado a la cita.
     * @param fechaInicio  La fecha y hora de inicio de la cita.
     * @param fechaFin     La fecha y hora de fin de la cita.
     * @param reservado    Indica si la cita está reservada.
     */
	public Cita(Paciente paciente, LocalDateTime fechaInicio, LocalDateTime fechaFin, boolean reservado) {
		super();
		this.paciente = paciente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.reservado = reservado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	@Override
	public String toString() {
		return "Cita [Paciente: " + paciente + ", Fecha de inicio: " + fechaInicio + ", Fecha de fin: " + fechaFin + ", Reservado: "
				+ (reservado == true ? "Sí" : "No") + "]";
	}
	
	
}
