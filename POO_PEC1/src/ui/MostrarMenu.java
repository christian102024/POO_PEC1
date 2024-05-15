package ui;

import java.util.List;

/**
 * Clase para mostrar un menú en la consola.
 */
public class MostrarMenu {
	
    /**
     * Muestra un menú con el título y las opciones proporcionadas.
     * 
     * @param titulo          El título del menú.
     * @param listaDeOpciones La lista de opciones del menú.
     */
    public static void mostrarMenu(String titulo, List<String> listaDeOpciones) {
        int anchoMenu = calcularAnchoMenu(titulo, listaDeOpciones);

        // Imprimir bordes superiores del menú
        imprimirLinea(anchoMenu);
        System.out.println("| " + centrarTexto(titulo, anchoMenu - 4) + " |");
        imprimirLinea(anchoMenu);

        // Usar forEach para iterar sobre los elementos del ArrayList
        int indice = 1;
        for (String opcion : listaDeOpciones) {
            System.out.println("| " + indice + ". " + centrarTexto(opcion, anchoMenu - (String.valueOf(indice).length() + 6)) + " |");
            indice++;
        }

        // Imprimir borde inferior del menú
        imprimirLinea(anchoMenu);
    }

    /**
     * Calcula el ancho necesario para el menú.
     * 
     * @param titulo          El título del menú.
     * @param listaDeOpciones La lista de opciones del menú.
     * @return El ancho del menú.
     */
    public static int calcularAnchoMenu(String titulo, List<String> listaDeOpciones) {
        int maxLongitud = titulo.length();
        for (int i = 0; i < listaDeOpciones.size(); i++) {
        	String opcion = listaDeOpciones.get(i);
            if (opcion.length() > maxLongitud) {
                maxLongitud = opcion.length() + String.valueOf(i).length() + 5;
            }
        }
        return maxLongitud + 10;
    }

    /**
     * Centra el texto dentro del ancho especificado.
     * 
     * @param texto El texto a centrar.
     * @param ancho El ancho del espacio disponible.
     * @return El texto centrado.
     */
    public static String centrarTexto(String texto, int ancho) {
        int espaciosTotales = ancho - texto.length();
        int espaciosIzquierda = espaciosTotales / 2;
        int espaciosDerecha = espaciosTotales - espaciosIzquierda;
        return repetirCaracter(' ', espaciosIzquierda) + texto + repetirCaracter(' ', espaciosDerecha);
    }

    /**
     * Repite el carácter especificado el número de veces dado.
     * 
     * @param caracter El carácter a repetir.
     * @param veces    El número de veces que se debe repetir el carácter.
     * @return Una cadena que contiene el carácter repetido el número de veces especificado.
     */
    public static String repetirCaracter(char caracter, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(caracter);
        }
        return sb.toString();
    }
    
    /**
     * Imprime una línea horizontal.
     * 
     * @param longitud La longitud de la línea.
     */
    public static void imprimirLinea(int longitud) {
        System.out.println("+" + repetirCaracter('-', longitud - 2) + "+");
    }

}
