package campus;

import java.util.List;

import usuarios.Paciente;

public class Habitacion {

	private int numeroHabitacion;
	private int piso;
	private boolean ocupada;
	private Paciente paciente;
	
	public Habitacion(int numeroHabitacion, int piso, boolean ocupada, Paciente paciente) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.piso = piso;
		this.ocupada = ocupada;
		this.paciente = paciente;
	}

	public Habitacion() {
		super();
	}

	public int getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(int numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public int getPiso() {
		return piso;
	}

	public void setPiso(int piso) {
		this.piso = piso;
	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}
