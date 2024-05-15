package usuarios;
import model.Turno;
import model.Unidad;

/**
 * Clase que representa a un empleado del sistema, que extiende la clase Usuario.
 */
public class Empleado extends Usuario {
	private Unidad unidad; // La unidad a la que pertenece el empleado
    private Turno turno; // El turno asignado al empleado

	/**
     * Constructor para crear un nuevo empleado con su información básica y unidad asignada.
     * El turno predeterminado es el turno de día.
     * 
     * @param nombre     El nombre del empleado.
     * @param apellidos  Los apellidos del empleado.
     * @param dni        El DNI del empleado.
     * @param telefono   El número de teléfono del empleado.
     * @param unidad     La unidad a la que pertenece el empleado.
     */
	public Empleado(String nombre, String apellidos, String dni, String telefono, Unidad unidad) {
		super(nombre, apellidos, dni, telefono);
		this.unidad = unidad;
		this.turno = Turno.DIA;
	}
	
	/**
	 * Constructor para crear un empleado con valores predeterminados.
	 * El turno predeterminado es el turno de día.
	 */
	public Empleado() {
		super();
		this.turno = Turno.DIA;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return super.toString() + ", unidad: " + unidad.getValor() + ", turno: " + turno;
	}


	
	
	
	
}
