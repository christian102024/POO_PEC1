package model;

import usuarios.Paciente;

public class Factura {

	private Paciente paciente;
	private double importe;
	
	public Factura(Paciente paciente, double importe) {
		super();
		this.paciente = paciente;
		this.importe = importe;
	}
	
	public Factura() {
		super();
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public boolean setPaciente(Paciente paciente) {
		if(comprobarPacienteNoTieneSeguridadSocial(paciente)) {
			this.paciente = paciente;
			return true;
		} else {
			System.out.println("El paciente no puede ser añadido a una factura debido a que tiene seguridad social.");
			return false;
		}
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}


	public boolean comprobarPacienteNoTieneSeguridadSocial(Paciente paciente) {
		if(paciente.getSeguro().equals(Seguro.NO_ASEGURADO) || paciente.getSeguro().equals(Seguro.SEGURO_MEDICO)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Factura [paciente=" + paciente + "], importe: " + importe + "€";
	}
	
	
	
}
