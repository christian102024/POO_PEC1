package model;

public enum Seguro {
	SEGURIDAD_SOCIAL,
	SEGURO_MEDICO,
	NO_ASEGURADO;
	
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
