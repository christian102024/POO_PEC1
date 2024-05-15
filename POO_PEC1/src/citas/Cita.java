package citas;

import java.time.LocalDateTime;

import usuarios.Empleado;
import usuarios.Paciente;

public class Cita {
	private Paciente paciente;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private boolean reservado;
	
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
