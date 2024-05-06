package ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import usuarios.Empleados;
import usuarios.Estudiantes;
import util.EntradaValores;

public class Menus {
	
	private static Scanner scanner;
	private Empleados empleados;
	private Estudiantes estudiantes;
	
	public Menus() {
		super();
		scanner = new Scanner(System.in);
		empleados = Empleados.getInstancia();
		estudiantes = Estudiantes.getInstancia();
	}

	public Menus(Empleados empleados, Estudiantes estudiantes) {
		super();
		scanner = new Scanner(System.in);
		this.empleados = empleados;
		this.estudiantes = estudiantes;
	}


	public void mostrarMenuPrincipal() {
		List<String> opciones = Arrays.asList("GESTIÓN DE PERSONAL DEL HOSPITAL", "GESTIÓN DE ESTUDIANTES", "GESTIÓN DE MEDICINA", "GESTIÓN DE ENFERMERÍA", "GESTIÓN DE SOPORTE", "SALIR");
		int opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("MENU PRINCIPAL", opciones);
			System.out.print("Seleccione una opción: ");
			
			opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción: ", new int[]{1, 2, 3, 4, 5, 6});
			
			switch(opcion) {
			case 1:
				mostrarMenuGestionHospital();
				break;
			case 2:
				mostrarMenuGestionEstudiantes();
				break;
			case 5:
				return;
				
			}
			
				
	
		} while (!navegar);
	}

	public void mostrarMenuGestionHospital() {
		List<String> opciones = Arrays.asList("Dar de alta a personal", "Dar de baja a personal", "Modificar expediente", "Mostrar empleados", "Buscar empleados por DNI", "Asignar turno", "Volver");
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
		            	empleados.darDeBajaEmpleado();
		                break;
		            case 3:
		            	empleados.modificarEmpleado();
		                break;
		            case 4:
		            	System.out.println("EMPLEADOS DADOS DE ALTA");
		            	System.out.println(empleados.toString());
		            	break;
		            case 5:
		            	empleados.mostrarEmpleadoBuscadoPorDNI();
		            	break;
		            case 6:
		            	empleados.asignarTurno();
		            	break;
		            case 7:
		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}
			} else {
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

			
		} while(!navegar);
	}
	
	public void mostrarMenuGestionEstudiantes() {
		List<String> opciones = Arrays.asList("Dar de alta a estudiante", "Dar de baja a estudiante", "Volver");
		int opcion;
		boolean navegar = false;
		
		do {
			MostrarMenu.mostrarMenu("GESTIÓN DE ESTUDIANTES DEL HOSPITAL", opciones );
			System.out.print("Seleccione una opción: ");

			if(scanner.hasNext())  {
				opcion = scanner.nextInt();
				scanner.nextLine();
				
				switch (opcion) {
		            case 1:
		            	estudiantes.darDeAltaEstudiante();
//		            	empleados.darDeAltaEmpleado(scanner);
		                break;
		            case 2:
		            	empleados.darDeBajaEmpleado();
		                break;
		            case 3:
		            	empleados.modificarEmpleado();
		                break;
//		            case 4:
//		            	System.out.println("EMPLEADOS DADOS DE ALTA");
//		            	System.out.println(empleados.toString());
//		            	break;
//		            case 5:
//		            	empleados.buscarEmpleadoPorDNI();
//		            	break;
//		            case 6:
//		            	empleados.asignarTurno();
//		            	break;
//		            case 7:
//		            	return;
		            	
		            default:
		            	System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
				}
			} else {
				System.out.println("Opción inválida. Por favor, introduzca una opción del menu.");
			}

			
		} while(!navegar);
	}
}
