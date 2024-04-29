package model;

public enum Unidad {
	GERENCIA("Gerencia"),
	MEDICINA("Medicina"),
    ENFERMERIA("Enfermeria"),
    SOPORTE("Soporte");

	private final String valor;
	
	private Unidad(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
	
	
}
