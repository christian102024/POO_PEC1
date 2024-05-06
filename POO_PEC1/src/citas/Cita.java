package citas;

import java.time.LocalDateTime;

import usuarios.Empleado;
import usuarios.Paciente;

public class Cita {
	private Paciente paciente;
	private Empleado empleado;
	private LocalDateTime fechaAlta;
	private LocalDateTime fechaBaja;
	
	public Cita(Paciente paciente, Empleado empleado, LocalDateTime fechaAlta, LocalDateTime fechaBaja) {
		super();
		this.paciente = paciente;
		this.empleado = empleado;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDateTime getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDateTime fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	
	
	
}
