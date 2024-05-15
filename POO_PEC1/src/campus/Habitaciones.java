package campus;

import java.util.ArrayList;
import java.util.List;

import usuarios.Paciente;

/**
 * Clase que gestiona las habitaciones del centro médico.
 */
public class Habitaciones {

	private static Habitaciones instancia;
	private List<Habitacion> habitaciones;

    /**
     * Constructor por defecto que inicializa la lista de habitaciones.
     */
	public Habitaciones() {
		super();
		this.habitaciones = new ArrayList<Habitacion>();
		inicializarHabitaciones();
	}

    /**
     * Obtiene la instancia única de la clase Habitaciones utilizando el patrón Singleton.
     * 
     * @return La instancia única de Habitaciones.
     */
	public static Habitaciones getInstancia() {
		if(instancia == null) {
			instancia = new Habitaciones();
		}
		return instancia;
	}

    /**
     * Constructor que inicializa la lista de habitaciones con la lista proporcionada.
     * 
     * @param habitaciones La lista de habitaciones.
     */
	public Habitaciones(List<Habitacion> habitaciones) {
		super();
		this.habitaciones = habitaciones;
	}
	
	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	/**
	 * Inicia las habitaciones por defecto. Estas serán 90 en total, distribuidas en 5 plantas, habiendo 18 habitaciones por planta.
	 */
	public void inicializarHabitaciones() {
		int numeroHabitacion = 1;
		
		for(int piso = 1; piso <= 5; piso++) {
			for(int habitacion = 1; habitacion <= 18; habitacion++) {
				habitaciones.add(new Habitacion(numeroHabitacion, piso, false, null));
				numeroHabitacion++;
			}			
		}
	}
	
	/**
     * Busca una habitación por su número.
     * 
     * @param numeroHabitacion El número de habitación a buscar.
     * @return El índice de la habitación en la lista de habitaciones, o -1 si no se encuentra.
     */
	public int buscarHabitacionPorNumero(int numeroHabitacion) {
		int indice = 0;
		for (Habitacion habitacion : habitaciones) {
			if(habitacion.getNumeroHabitacion() == numeroHabitacion) {
				return indice;
			}
			indice++;
		}
		return -1;
	}
	
	 /**
     * Busca el índice de la habitación ocupada por un paciente.
     * 
     * @param paciente El paciente cuya habitación se busca.
     * @return El índice de la habitación ocupada por el paciente, o -1 si no se encuentra.
     */
	public int buscarIndiceHabitacionPorPaciente(Paciente paciente) {
		int indice = 0;
		for (Habitacion habitacion : habitaciones) {
			if(habitacion.getPaciente().equals(paciente)) {
				return indice;
			}
			indice++;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		String cadena = "";
		for (Habitacion habitacion : habitaciones) {
			cadena += habitacion + "\n";
		}
		return cadena;
	}
	
	
}
