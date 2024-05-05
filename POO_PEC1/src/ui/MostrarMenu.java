package ui;

import java.util.ArrayList;
import java.util.List;

public class MostrarMenu {
	
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

    public static String centrarTexto(String texto, int ancho) {
        int espaciosTotales = ancho - texto.length();
        int espaciosIzquierda = espaciosTotales / 2;
        int espaciosDerecha = espaciosTotales - espaciosIzquierda;
        return repetirCaracter(' ', espaciosIzquierda) + texto + repetirCaracter(' ', espaciosDerecha);
    }

    public static String repetirCaracter(char caracter, int veces) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < veces; i++) {
            sb.append(caracter);
        }
        return sb.toString();
    }

    public static void imprimirLinea(int longitud) {
        System.out.println("+" + repetirCaracter('-', longitud - 2) + "+");
    }

}
