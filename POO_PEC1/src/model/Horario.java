package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Horario {

	private List<HoraConsulta> listaHorasConsultas;

	public Horario(List<HoraConsulta> horario) {
		super();
		this.listaHorasConsultas = horario;
	}
	
	public List<HoraConsulta> getListaHorasConsultas() {
		return listaHorasConsultas;
	}

	public void setListaHorasConsultas(List<HoraConsulta> listaHorasConsultas) {
		this.listaHorasConsultas = listaHorasConsultas;
	}

	public static List<HoraConsulta> generarHorarioIntensivo(int horaEntrada, int horaSalida, int duracionConsulta) {
	    LocalDateTime hoy = LocalDateTime.now();
	    List<HoraConsulta> listahoras = new ArrayList<>();

	    for (int i = horaEntrada; i < horaSalida; i++) {
	        LocalDateTime horaInicio = LocalDateTime.of(hoy.getYear(), hoy.getMonth(), hoy.getDayOfMonth(), i, 0);
	        LocalDateTime horaFin = horaInicio.plusHours(duracionConsulta);
	        listahoras.add(new HoraConsulta(horaInicio, horaFin));
	    }

	    return listahoras;
	}
}
