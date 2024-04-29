package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import usuarios.Empleados;

public class Menus {
	
	private static Scanner scanner;
	private static Empleados empleados;
	
	static {
		scanner = new Scanner(System.in);
		empleados = new Empleados();
	}

	public Menus() {
		super();
	}


	public static void mostrarMenuGestionHospital() {
		List<String> opciones = Arrays.asList("Dar de alta a personal", "Dar de baja a personal", "Modificar expediente", "Volver");
		int opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE PERSONAL DEL HOSPITAL", opciones );
			System.out.print("Seleccione una opción: ");
			if (scanner.hasNextInt()) {
				opcion = scanner.nextInt();
				scanner.nextLine();
				
				switch (opcion) {
                case 1:
                	empleados.darDeAltaEmpleado();
                	navegar = true;
                    break;
                case 2:
                	navegar = true;
                    break;
                case 3:
                	navegar = true;
                    break;
                case 4:
                	navegar = true;
                default:
                	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
            }
			} else {
				scanner.next();
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}
			
		} while(!navegar);
		
		
	}
}
