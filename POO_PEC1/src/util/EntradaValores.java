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
				if(Arrays.asList(opcionesValidas).contains(numero)) {
					esValido = true;					
				} else {
					System.out.println("El numero no es valido, por favor, introduzca una opción valida.");
				}
			} else {
				System.out.println("El valor introducido no es un numero valido.");
				scanner.next();
			}
			
		} while (!esValido);
		
		scanner.close();
		return numero;
	}
	
}
