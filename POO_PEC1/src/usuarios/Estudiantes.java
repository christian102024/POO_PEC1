package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Unidad;
import ui.MostrarMenu;
import util.EntradaValores;

public class Estudiantes {

	private static Estudiantes instancia;
	private List<Estudiante> estudiantes;

	public Estudiantes() {
		super();
		this.estudiantes = new ArrayList<Estudiante>();
	}
	
	public static Estudiantes getInstancia() {
		if(instancia == null) {
			instancia = new Estudiantes();
		}
		return instancia;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	public void addEstudiante(Estudiante estudiante) {
		this.estudiantes.add(estudiante);
	}
	
	public void darDeAltaEstudiante() {
		Estudiante estudiante = new Estudiante();
		Scanner scanner = new Scanner(System.in);
		estudiante = pedirDatosEstudiante(scanner, estudiante);
		
		if(estudiante != null) {
			estudiantes.add(estudiante);
		}
	}
	
	private Estudiante pedirDatosEstudiante(Scanner scanner, Estudiante estudiante) {
		
		String nombre = EntradaValores.introducirCadena("Nombre: ", estudiante.getNombre());
		if(nombre.equals("cancelar")) {
			return null;
		}
		estudiante.setNombre(nombre);
		
		
		String apellidos = EntradaValores.introducirCadena("Apellidos: ", estudiante.getApellidos());
		if(apellidos.equals("cancelar")) {
			return null;
		}
		estudiante.setApellidos(apellidos);
		
		String dni = EntradaValores.introducirCadena("DNI: ", estudiante.getDni());
		if(dni.equals("cancelar")) {
			return null;
		}
		estudiante.setDni(dni);
		
		String telefono = EntradaValores.introducirCadena("Teléfono: ", estudiante.getTelefono());
		if(telefono.equals("cancelar")) {
			return null;
		}
		estudiante.setTelefono(telefono);
		
		boolean asignarPersonal = EntradaValores.introducirValorBooleano("¿Asignar personal al estudiante? (S/N): ");
		
		if(asignarPersonal) {
			
		} 
		return estudiante;
	}
	
	private void asignarPersonalAlEmpleado(Estudiante estudiante) {
		MostrarMenu.mostrarMenu("Seleccionar tipo de personal", Arrays.asList("Asignar personal médico", "Asignar personal de enfermería", "Asignar cita", "Cancelar"));
		int opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción: ", new int[] {1, 2, 3, 4});
		Empleados empleados = Empleados.getInstancia();
		
		
		switch(opcion) {
		case 1:
			Empleado empleado = empleados.buscarEmpleadoPorDNI();
			if(empleado != null && empleado.getUnidad().equals(Unidad.MEDICINA))  {
				estudiante.setPersonalAsignado(empleado);
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		}
	}
	
	public void mostrarEstudiantes() {
		System.out.println("Estudiantes dados de alta");
		for(Estudiante estudiante: estudiantes) {
			System.out.println(estudiante);
		}
	}
	
	
}
