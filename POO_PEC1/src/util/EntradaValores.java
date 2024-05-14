package util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Scanner;

public class EntradaValores {
	
	public static double introducirNumero(String mensaje) {
		boolean esValido = false;
		double numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			
			if (scanner.hasNextDouble()) {
				numero = scanner.nextDouble();
				esValido = true;
			}
			
		} while (!esValido);
		
		return numero;
	}
	
	public static Integer introducirNumeroEntero(String mensaje) {
		boolean esValido = false;
		int numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			
			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				esValido = true;
			} else if (scanner.hasNext()) {
				if(scanner.next().equals("cancelar")) return null;
			} 
			
		} while (!esValido);
		
		return numero;
	}
	
	public static Integer introducirNumeroEntero(String mensaje, int[] opcionesValidas) {
		boolean esValido = false;
		int numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			
			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				
				for(int opcion: opcionesValidas) {
					if(opcion == numero) {
						esValido = true;
						break;
					}
				}
				
				if(!esValido) {
					System.out.println("El número no es valido, por favor, introduzca una opción valida.");
				}
				
			} else if (scanner.hasNext()) {
				if(scanner.next().equals("cancelar")) return null;
			} else {
				System.out.println("El valor introducido no es un numero valido.");
				scanner.next();
			}
			
		} while (!esValido);
		
		return numero;
	}
	
	/**
	 * Pide introducir un valor de tipo String al usuario. Si el usuario no lo introduce, repetira la secuencia para que lo introduzca.
	 * @param mensaje El mensaje para solicitar la cadena.
	 * @return El valor introducido por el usuario
	 */
	public static String introducirCadena(String mensaje) {
		boolean esValido = false;

		String cadena = "";
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			
			if (scanner.hasNext()) {
				cadena = scanner.nextLine();
				esValido = true;
			}
			
			if(cadena.equals("cancelar")) return null;
			
		} while (!esValido);
		
		return cadena;
	}
	
	/**
	 * Pide introducir un valor de tipo String al usuario. Si el usuario no lo introduce, comprobará el valor previo.
	 * Si el valor previo no esta vacío, lo devolverá, si no, repetira la secuencia para que el usuario introduzca un valor nuevamente.
	 * @param mensaje El mensaje para solicitar la cadena.
	 * @return El valor introducido por el usuario o en su defecto el valor previo.
	 */
	public static String introducirCadena(String mensaje, String valorPrevio) {
		boolean esValido = false;

		String cadena = "";
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);
			
			cadena = scanner.nextLine();
			if (!cadena.isEmpty()) {
				esValido = true;
			} else if (valorPrevio != null && !valorPrevio.equals("")) {
				cadena = valorPrevio;
				esValido = true;
			}
			
		} while (!esValido);
		
		return cadena;
	}
	
	public static boolean introducirValorBooleano(String mensaje) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print(mensaje);
		String respuesta = scanner.next().toLowerCase();
		
		boolean valorBooleano = false;
		switch (respuesta) {
		case "si":
			valorBooleano = true;
			break;
			
		case "s":
			valorBooleano = true;
			break;
			
		case "no":
			valorBooleano = false;
			break;
			
		case "n":
			valorBooleano = false;
			break;
		}
		
		return valorBooleano;
	}
	
	public static LocalDate introducirFecha(String mensaje) {
		Scanner scanner = new Scanner(System.in);
		if(mensaje != null) {
			System.out.print(mensaje);
		}
		
		int anyo = -1;
		while(anyo == -1) {
			System.out.print("Introduzca el año: ");
			try {
				anyo = scanner.nextInt();				
			} catch(Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}
			
			if(anyo < 2000) {
				anyo = -1;
				System.out.println("El año introducido no es válido!");
			}
		}
		
		int mes = -1;
		while(mes == -1) {
			System.out.print("Introduzca el mes: ");
			try {
				mes = scanner.nextInt();
			} catch(Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}
			
			if(mes < 1 || mes > 12) {
				mes = -1;
				System.out.println("El mes introducido no es válido!");
			}
		}
		
		int dia = -1;
		while(dia == -1) {
			System.out.print("Introduzca el día: ");
			try {
				dia = scanner.nextInt();				
			} catch(Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}
			
			if(dia < 1 || dia > 31) {
				dia = -1;
				System.out.println("El día introducido no es válido!");
			}
		}
		
		return LocalDate.of(anyo, mes, dia);
	}
	
	public static LocalTime introducirHora(String mensaje) {
		Scanner scanner = new Scanner(System.in);
		
		if(mensaje != null) {
			System.out.println(mensaje);
		}
		
		int hora = -1;
		while(hora == -1) {
			System.out.print("Introduzca la hora: ");
			try {
				hora = scanner.nextInt();				
			} catch(Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}
			
			if(hora < 0 || hora > 24) {
				hora = -1;
				System.out.println("La hora introducida no es válida!");
			}
		}
		
		int minutos = -1;
		while(minutos == -1) {
			System.out.print("Introduzca los minutos: ");
			try {
				minutos = scanner.nextInt();
			} catch(Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}
			
			if(minutos < 0 || minutos > 60) {
				minutos = -1;
				System.out.println("El mes introducido no es válido!");
			}
		}
		
		return LocalTime.of(hora, minutos);
	}
	
}
