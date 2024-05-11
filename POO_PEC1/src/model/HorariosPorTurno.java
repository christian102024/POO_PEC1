package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//private static final List<HorasConsulta> HORAS_CONSULTAS_DIA = new ArrayList<>();

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

	private static List<HoraConsulta> horarioDia() {
		LocalDateTime hoy = LocalDateTime.now();
		List<HoraConsulta> listahoras = new ArrayList<HoraConsulta>();

		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 7, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 8, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 8, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 9, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 9, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 10, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 11, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 12, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 13, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 13, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 14, 0)));
		listahoras.add(new HoraConsulta(LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 14, 0),
				LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), 15, 0)));

		return listahoras;
	}
}
