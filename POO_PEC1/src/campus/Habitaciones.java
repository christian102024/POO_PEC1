package campus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Habitaciones {

	private static Habitaciones instancia;
	private List<Habitacion> habitaciones;

	public Habitaciones() {
		super();
		this.habitaciones = new ArrayList<Habitacion>();
		inicializarHabitaciones();
	}

	public static Habitaciones getInstancia() {
		if(instancia == null) {
			instancia = new Habitaciones();
		}
		return instancia;
	}

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
	
	@Override
	public String toString() {
		String cadena = "";
		for (Habitacion habitacion : habitaciones) {
			cadena += habitacion + "\n";
		}
		return cadena;
	}
	
	
}
