package model;


/**
 * Enumeración que representa los diferentes horarios por turno en el centro médico.
 */
public enum HorariosPorTurno {
	HORARIO_DIA(new Horario(Horario.generarHorarioIntensivo(7, 15, 1))),
	HORARIO_TARDE(new Horario(Horario.generarHorarioIntensivo(16, 12, 1))),
	HORARIO_NOCHE(new Horario(Horario.generarHorarioIntensivo(24, 8, 1)));

	private final Horario horario;

	private HorariosPorTurno(Horario horario) {
		this.horario = horario;
	}

	public Horario getHorario() {
		return horario;
	}
}
