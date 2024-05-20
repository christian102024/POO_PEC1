package util;

public class Comparador {

	/**
	 * Busca un valor en el array. Si el elemento se encuentra dentro del array, devuelve true. De no ser encontrado,
	 * devuevle false
	 * 
	 * @param <T> El tipo de dato del que se quiere buscar algo
	 * @param valor El valor a buscar
	 * @param array La lista de valores en la que se quiere buscar el valor especificado en el parametro anterior.
	 * @return Si el valor ha sido encontrado o no.
	 */
	public static <T> boolean buscarValorEnArray(T valor, T[] array) {
		for (T elemento : array) {
			if(elemento.equals(valor)) {
				return true;
			}
		}
		return false;
	}
}
