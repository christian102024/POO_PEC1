package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Unidad;
import model.Unidades;
import ui.Menus;
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
	
	public void anyadirEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
	}
	
	public void darDeAltaEmpleado(Scanner scanner) {
		System.out.println("Iniciando el proceso de alta de un empleado...");
		Empleado empleado = new Empleado();

		System.out.println("Nombre: ");
		empleado.setNombre(scanner.nextLine());
		
		System.out.println("Apellidos: ");
		empleado.setApellidos(scanner.nextLine());
		
		System.out.println("DNI: ");
		empleado.setDni(scanner.nextLine());
		
		System.out.println("Telefono: ");
		empleado.setTelefono(scanner.nextLine());
		
		try {
			empleado.setUnidad(seleccionarUnidad());			
		} catch(Exception expection) {
			System.out.println("La unidad seleccionada no existe.");
			System.out.println(expection);
		}
		anyadirEmpleado(empleado);
		
	}
	
	private static Unidad seleccionarUnidad() {
		List<String> lista = Unidades.UNIDADES2;
		
		MostrarMenu.mostrarMenu("SELECCIONE UNA UNIDAD", lista);
		int opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		
		if(opcion >= 1 || opcion <= 10) {
			return Unidades.UNIDADES3.get(opcion-1);
		} else {
			throw new Error("Entrada incorrecta: la entrada no puede ser nula o vacía");
		}
	}

	@Override
	public String toString() {
		String resultado = "";
		for(Empleado empleado: empleados) {
			resultado += empleado + "\n";
		}
		
		return resultado;
	}
	
	
	
	
}
