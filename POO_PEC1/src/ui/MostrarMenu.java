package ui;

import java.util.ArrayList;

public class MostrarMenu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void mostrarMenu(ArrayList<String> listaDeOpciones, String titulo) {

	        // Imprimir bordes superiores del menú
		System.out.println("+--------------------------+");
        System.out.println("|      " + titulo + "      |");
        System.out.println("+--------------------------+");

	        // Usar forEach para iterar sobre los elementos del ArrayList
	        int indice = 1;
	        for (String opcion : listaDeOpciones) {
	            System.out.println("║ " + indice + ". " + opcion + espacios(20 - opcion.length()) + "║");
	            indice++;
	        }

	        // Imprimir borde inferior del menú
	        System.out.println("+--------------------------+");
	}
	
	 public static String espacios(int n) {
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < n; i++) {
	            sb.append(" ");
	        }
	        return sb.toString();
	    }

}
