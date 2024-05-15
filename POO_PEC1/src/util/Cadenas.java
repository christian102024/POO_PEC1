package util;

/**
 * Esta clase proporciona métodos de utilidad para operaciones con cadenas.
 */
public class Cadenas {
	/**
     * Repite una cadena un número dado de veces.
     *
     * @param c La cadena que se repetirá.
     * @param numeroVeces El número de veces que se repetirá la cadena.
     * @return Una nueva cadena que resulta de repetir la cadena dada el número de veces especificado.
     */
	public static String repetirCaracter(String c, int numeroVeces) {
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < numeroVeces; i++) {
	        builder.append(c);
	    }
	    return builder.toString();
	}
}
