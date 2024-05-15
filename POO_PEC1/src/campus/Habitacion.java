package campus;

import java.util.List;

import usuarios.Paciente;

/**
 * Clase que representa una habitación en el centro médico.
 */
public class Habitacion {

	private int numeroHabitacion;
	private int piso;
	private boolean ocupada;
	private Paciente paciente;
	
	/**
     * Constructor que inicializa una habitación con los valores proporcionados.
     * 
     * @param numeroHabitacion El número de la habitación.
     * @param piso El piso en el que se encuentra la habitación.
     * @param ocupada Indica si la habitación está ocupada.
     * @param paciente El paciente asignado a la habitación.
     */
	public Habitacion(int numeroHabitacion, int piso, boolean ocupada, Paciente paciente) {
		super();
		this.numeroHabitacion = numeroHabitacion;
		this.piso = piso;
		this.ocupada = ocupada;
		this.paciente = paciente;
	}

    /**
     * Constructor por defecto.
     */
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

	@Override
	public String toString() {
		return "Número de habitación: " + numeroHabitacion + ", Piso: " + piso + ", Ocupada: " + ocupada
				+ ", Paciente: " + paciente;
	}
	
	
}
