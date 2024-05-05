package model;

public enum Turno {
	DIA,
	NOCHE;
	
	@Override
    public String toString() {
        switch (this) {
            case DIA:
                return "Turno de Día";
            case NOCHE:
                return "Turno de Noche";
            default:
                return super.toString();
        }
    }
}
