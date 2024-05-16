package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

@SuppressWarnings("resource")
/**
 * Esta clase proporciona métodos para la entrada de valores desde la consola.
 */
public class EntradaValores {

	/**
	 * Permite al usuario introducir un número decimal desde la consola.
	 *
	 * @param mensaje El mensaje que se mostrará al usuario para solicitar la
	 *                entrada.
	 * @return El número decimal introducido por el usuario, o null si se introduce
	 *         "cancelar".
	 */
	public static Double introducirNumero(String mensaje) {
		boolean esValido = false;
		double numero = -1;

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);

			if (scanner.hasNextDouble()) {
				numero = scanner.nextDouble();
				esValido = true;
			} else if (scanner.hasNext()) {
				if (scanner.next().equals("cancelar"))
					return null;
			}

		} while (!esValido);

		return numero;
	}

	/**
	 * Permite al usuario introducir un número entero desde la consola.
	 *
	 * @param mensaje El mensaje que se mostrará al usuario para solicitar la
	 *                entrada.
	 * @return El número entero introducido por el usuario, o null si se introduce
	 *         "cancelar".
	 */
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
				if (scanner.next().equals("cancelar"))
					return null;
			}

		} while (!esValido);

		return numero;
	}

	/**
	 * Permite al usuario introducir un número entero desde la consola, con opciones
	 * válidas predefinidas.
	 *
	 * @param mensaje         El mensaje que se mostrará al usuario para solicitar
	 *                        la entrada.
	 * @param opcionesValidas Un array de enteros que representa las opciones
	 *                        válidas que el usuario puede introducir.
	 * @return El número entero introducido por el usuario, o null si se introduce
	 *         "cancelar" o -1 en caso de que ocurra un error.
	 */
	public static Integer introducirNumeroEntero(String mensaje, int[] opcionesValidas) {
		boolean esValido = false;
		int numero = -1;

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);

			if (scanner.hasNextInt()) {
				numero = scanner.nextInt();

				for (int opcion : opcionesValidas) {
					if (opcion == numero) {
						esValido = true;
						break;
					}
				}

				if (!esValido) {
					System.out.println("El número no es valido, por favor, introduzca una opción valida.");
				}

			} else if (scanner.hasNext()) {
				if (scanner.next().equals("cancelar"))
					return null;
			} else {
				System.out.println("El valor introducido no es un numero valido.");
				scanner.next();
			}

		} while (!esValido);

		return numero;
	}

	/**
	 * Pide introducir un valor de tipo String al usuario. Si el usuario no lo
	 * introduce, repetira la secuencia para que lo introduzca.
	 * 
	 * @param mensaje El mensaje para solicitar la cadena.
	 * @return El valor introducido por el usuario, o bien null si el usuario
	 *         introduce "cancelar"
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

			if (cadena.equals("cancelar"))
				return null;

		} while (!esValido);

		return cadena;
	}

	/**
	 * Pide introducir un valor de tipo String al usuario. Si el usuario no lo
	 * introduce, comprobará el valor previo. Si el valor previo no esta vacío, lo
	 * devolverá, si no, repetira la secuencia para que el usuario introduzca un
	 * valor nuevamente.
	 * 
	 * @param mensaje El mensaje para solicitar la cadena.
	 * @return El valor introducido por el usuario o en su defecto el valor previo.
	 *         Devuelve null si el usuario introduce "cancelar"
	 */
	public static String introducirCadena(String mensaje, String valorPrevio) {
		boolean esValido = false;

		String cadena = "";

		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print(mensaje);

			cadena = scanner.nextLine();

			if (cadena.equals("cancelar"))
				return null;

			if (!cadena.isEmpty()) {
				esValido = true;
			} else if (valorPrevio != null && !valorPrevio.equals("")) {
				cadena = valorPrevio;
				esValido = true;
			}

		} while (!esValido);

		return cadena;
	}

	/**
	 * Permite al usuario introducir un valor booleano ("si" o "no") desde la
	 * consola.
	 *
	 * @param mensaje El mensaje que se mostrará al usuario para solicitar la
	 *                entrada.
	 * @return true si el usuario introduce "si" o "s", false si introduce "no" o
	 *         "n".
	 */
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

	/**
     * Permite al usuario introducir una fecha (año, mes, día) desde la consola.
     *
     * @param mensaje El mensaje que se mostrará al usuario para solicitar la entrada.
     * @return La fecha introducida por el usuario como un objeto LocalDate.
     */
	public static LocalDate introducirFecha(String mensaje) {
		Scanner scanner = new Scanner(System.in);
		if (mensaje != null) {
			System.out.println(mensaje);
		}

		int anyo = -1;
		while (anyo == -1) {
			System.out.print("Introduzca el año: ");
			try {
				anyo = scanner.nextInt();
			} catch (Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}

			if (anyo < 2000) {
				anyo = -1;
				System.out.println("El año introducido no es válido!");
			}
		}

		int mes = -1;
		while (mes == -1) {
			System.out.print("Introduzca el mes: ");
			try {
				mes = scanner.nextInt();
			} catch (Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}

			if (mes < 1 || mes > 12) {
				mes = -1;
				System.out.println("El mes introducido no es válido!");
			}
		}

		int dia = -1;
		while (dia == -1) {
			System.out.print("Introduzca el día: ");
			try {
				dia = scanner.nextInt();
			} catch (Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}

			if (dia < 1 || dia > 31) {
				dia = -1;
				System.out.println("El día introducido no es válido!");
			}
		}

		return LocalDate.of(anyo, mes, dia);
	}

    /**
     * Permite al usuario introducir una hora (hora, minutos) desde la consola.
     *
     * @param mensaje El mensaje que se mostrará al usuario para solicitar la entrada.
     * @return La hora introducida por el usuario como un objeto LocalTime.
     */
	public static LocalTime introducirHora(String mensaje) {
		Scanner scanner = new Scanner(System.in);

		if (mensaje != null) {
			System.out.println(mensaje);
		}

		int hora = -1;
		while (hora == -1) {
			System.out.print("Introduzca la hora: ");
			try {
				hora = scanner.nextInt();
			} catch (Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}

			if (hora < 0 || hora > 24) {
				hora = -1;
				System.out.println("La hora introducida no es válida!");
			}
		}

		int minutos = -1;
		while (minutos == -1) {
			System.out.print("Introduzca los minutos: ");
			try {
				minutos = scanner.nextInt();
			} catch (Exception exception) {
				System.out.println("El valor introducido no es numérico!");
			}

			if (minutos < 0 || minutos > 60) {
				minutos = -1;
				System.out.println("El mes introducido no es válido!");
			}
		}

		return LocalTime.of(hora, minutos);
	}
	
    /**
     * Obtiene el primer y último día de la semana para una fecha dada.
     *
     * @param fecha la fecha para la cual se desea obtener el primer y último día de la semana
     * @return un array de dos elementos donde el primer elemento es el primer día de la semana
     *         y el segundo elemento es el último día de la semana
     */
    public static LocalDate[] obtenerFechaInicioYFinSemana(LocalDate fecha) {
        LocalDate[] inicioFinSemana = new LocalDate[2];

        // Calcula el primer día de la semana (lunes)
        LocalDate primerDiaSemana = fecha.with(DayOfWeek.MONDAY);
        inicioFinSemana[0] = primerDiaSemana;

        // Calcula el último día de la semana (domingo)
        LocalDate ultimoDiaSemana = fecha.with(DayOfWeek.SUNDAY);
        inicioFinSemana[1] = ultimoDiaSemana;

        return inicioFinSemana;
    }

}
