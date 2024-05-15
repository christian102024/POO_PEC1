package util;

public class Comparador {

	public static <T> boolean buscarValorEnArray(T valor, T[] array) {
		for (T elemento : array) {
			if(elemento.equals(valor)) {
				return true;
			}
		}
		return false;
	}
}
