package usuarios;

/**
 * Clase que representa a un usuario.
 */
public class Usuario {

	private String nombre;
	private String apellidos;
	private String dni;
	private String telefono;
	
    /**
     * Constructor de la clase Usuario.
     *
     * @param nombre    El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param dni       El DNI del usuario.
     * @param telefono  El número de teléfono del usuario.
     */
	public Usuario(String nombre, String apellidos, String dni, String telefono) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
	}
	
	
	public Usuario() {
		super();
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	


	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Apellidos: " + apellidos + ", DNI: " + dni + ", teléfono: " + telefono;
	}
	
	
	
	
}
