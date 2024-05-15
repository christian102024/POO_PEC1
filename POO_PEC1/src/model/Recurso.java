package model;

import java.time.LocalDateTime;

import usuarios.Empleado;
import util.FormatosFechas;

public class Recurso {

	private String nombre;
	private String descripcionProblema;
	private Empleado empleado;
	private LocalDateTime fechaAlta;
	
	public Recurso(String nombre, String descripcionProblema, Empleado empleado) {
		super();
		this.nombre = nombre;
		this.descripcionProblema = descripcionProblema;
		this.empleado = empleado;
		this.fechaAlta = LocalDateTime.now();
	}

	public Recurso() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcionProblema() {
		return descripcionProblema;
	}

	public void setDescripcionProblema(String descripcionProblema) {
		this.descripcionProblema = descripcionProblema;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Descripción del problema: " + descripcionProblema + ", Empleado=" + empleado
				+ ", Fecha de alta: " + fechaAlta.format(FormatosFechas.FORMATO_DIA_HORA.getFormatter());
	}
	
	
	
	
}
