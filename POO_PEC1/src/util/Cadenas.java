package util;

public class Cadenas {
	public static String repeatString(String c, int numeroVeces) {
	    StringBuilder builder = new StringBuilder();
	    for (int i = 0; i < numeroVeces; i++) {
	        builder.append(c);
	    }
	    return builder.toString();
	}
}
