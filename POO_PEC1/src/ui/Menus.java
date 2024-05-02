package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import usuarios.Empleados;

public class Menus {
	
	private static Scanner scanner;
	private static Empleados empleados;
	
	public Menus() {
		super();
		scanner = new Scanner(System.in);
		empleados = new Empleados();
	}


	public void mostrarMenuGestionHospital() {
		List<String> opciones = Arrays.asList("Dar de alta a personal", "Dar de baja a personal", "Modificar expediente", "Mostrar empleados", "Volver");
		int opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE PERSONAL DEL HOSPITAL", opciones );
			System.out.print("Seleccione una opción: ");

			if(scanner.hasNext())  {
				opcion = scanner.nextInt();
				scanner.nextLine();
				
				switch (opcion) {
		            case 1:
		            	empleados.darDeAltaEmpleado(scanner);
		                break;
		            case 2:
		            	navegar = true;
		                break;
		            case 3:
		            	navegar = true;
		                break;
		            case 4:
		            	System.out.println(empleados.toString());
		            	break;
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}
			} else {
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

			
		} while(!navegar);
	}
}
