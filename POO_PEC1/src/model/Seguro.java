package model;

/**
 * Enumeración que representa los diferentes tipos de seguro médico.
 */
public enum Seguro {
	SEGURIDAD_SOCIAL,
	SEGURO_MEDICO,
	NO_ASEGURADO;
	
	/**
     * Devuelve una representación legible en forma de cadena del tipo de seguro.
     *
     * @return La representación legible del tipo de seguro.
     */
	@Override
    public String toString() {
        switch (this) {
            case SEGURIDAD_SOCIAL:
                return "Seguridad social";
            case SEGURO_MEDICO:
                return "Seguro médico";
            case NO_ASEGURADO:
            	return "Sin seguro";
            default:
                return super.toString();
        }
    }
}
