package util;

import java.util.Arrays;
import java.util.Scanner;

public class EntradaValores {
	
	public static double introducirNumero(String mensaje) {
		boolean esValido = false;
		double numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(mensaje);
			
			if (scanner.hasNextDouble()) {
				numero = scanner.nextDouble();
				esValido = true;
			}
			
		} while (!esValido);
		
		scanner.close();
		return numero;
	}
	
	public static int introducirNumeroEntero(String mensaje) {
		boolean esValido = false;
		int numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(mensaje);
			
			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				esValido = true;
			}
			
		} while (!esValido);
		
		scanner.close();
		return numero;
	}
	
	public static int introducirNumeroEntero(String mensaje, int[] opcionesValidas) {
		boolean esValido = false;
		int numero = -1;
		
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(mensaje);
			
			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();
				
				for(int opcion: opcionesValidas) {
					if(opcion == numero) {
						esValido = true;
						break;
					}
				}
				
				if(!esValido) {
					System.out.println("El numero no es valido, por favor, introduzca una opción valida.");
				}
				
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
			System.out.println(mensaje);
			
			if (scanner.hasNext()) {
				cadena = scanner.nextLine();
				esValido = true;
			}
			
		} while (!esValido);
		
		scanner.close();
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
			System.out.println(mensaje);
			
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
		
		System.out.println(mensaje);
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
	
}
