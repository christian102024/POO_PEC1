package usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import util.EntradaValores;

public class Estudiantes {

	private List<Estudiante> estudiantes;

	public Estudiantes() {
		super();
		this.estudiantes = new ArrayList<Estudiante>();
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
		
		boolean asignarPersonal = EntradaValores.introducirValorBooleano("¿Asignar personal medico al estudiante? (S/N): ");
		
		if(asignarPersonal) {
			Empleados empleados = new Empleados();
			System.out.println("empleados: " + empleados.getEmpleados());
		} else {
			return estudiante;
		}
		
		return estudiante;
	}
	
	public void asignarPersonalMedico() {
		Empleados empleados = new Empleados();
		System.out.println("empleados: " + empleados.getEmpleados());
	}
	
	
}
