package usuarios;
import model.Unidad;
import usuarios.Usuario;

public class Empleado extends Usuario {
	private Unidad unidad;

	public Empleado(String nombre, String apellidos, String dni, String telefono, Unidad unidad) {
		super(nombre, apellidos, dni, telefono);
		this.unidad = unidad;
	}
	
	public Empleado() {
		super();
	}



	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		return super.toString() + ", unidad: " + unidad.getValor();
	}


	
	
	
	
}
