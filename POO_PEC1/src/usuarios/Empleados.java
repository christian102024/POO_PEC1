package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Unidad;
import ui.MostrarMenu;
import util.EntradaValores;

public class Empleados {
	private List<Empleado> empleados;

	public Empleados() {
		super();
		this.empleados = new ArrayList<Empleado>();
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public void darDeAltaEmpleado() {
		System.out.println("Iniciando el proceso de alta de un empleado...");
		Scanner scanner = new Scanner(System.in);
		Empleado empleado = new Empleado();

		System.out.println("Nombre: ");
		empleado.setNombre(scanner.nextLine());
		
		System.out.println("Apellidos: ");
		empleado.setApellidos(scanner.nextLine());
		
		System.out.println("DNI: ");
		empleado.setDni(scanner.nextLine());
		
		System.out.println("Telefono: ");
		empleado.setTelefono(scanner.nextLine());
		
		seleccionarUnidad();
		// empleado.setUnidad();

		
	}
	
	private static void seleccionarUnidad() {
		MostrarMenu.mostrarMenu("SELECCIONE UNA UNIDAD", Arrays.asList(Unidad.ENFERMERIA.getValor(), Unidad.GERENCIA.getValor() ,Unidad.MEDICINA.getValor(), Unidad.SOPORTE.getValor()));
		EntradaValores.introducirNumeroEntero("Seleccione una opción", new int[]{1, 2, 3, 4});
	}
	
	
}
