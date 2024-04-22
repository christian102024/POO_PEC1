package campus;

import java.util.ArrayList;
import java.util.List;

public class Hospitalizacion extends Departamento {
	private List<Habitacion> habitaciones;

	public Hospitalizacion(List<Habitacion> habitaciones) {
		super();
		this.habitaciones = habitaciones;
	}
	
	public Hospitalizacion() {
		super();
		this.habitaciones = new ArrayList<Habitacion>();
	}
	
	
	

}
