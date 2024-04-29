package usuarios;
import model.Unidad;
import usuarios.Usuario;

public class Empleado extends Usuario {
	private Unidad unidad;

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	
}
