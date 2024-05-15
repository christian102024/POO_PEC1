package model;

import java.util.ArrayList;
import java.util.List;

import usuarios.Paciente;
import usuarios.Pacientes;
import util.EntradaValores;

public class Facturas {

	private static Facturas instancia;
	private List<Factura> listaFacturas;

	public Facturas() {
		super();
		this.listaFacturas = new ArrayList<Factura>();
	}
	
	public static Facturas getInstancia() {
		if(instancia == null) {
			instancia = new Facturas();
		}
		return instancia;
	}

	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	
	public void anyadirFactura(Factura factura) {
		listaFacturas.add(factura);
	}
	
	public void eliminarFactura(Factura factura) {
		listaFacturas.remove(factura);
	}
	
	public void generarFactura() {
		
		Pacientes pacientes = Pacientes.getInstancia();
		
		Factura factura = new Factura();
		Paciente paciente = pacientes.buscarPacientePorDNI();
		Double importe = 0.0;
		
		if(paciente == null) return;
		boolean pacienteValido = factura.setPaciente(paciente);
		
		if(!pacienteValido) return;
		
		importe = EntradaValores.introducirNumero("Introduzca el importe de la factura: ");
			
		if(importe == null) return;
		factura.setImporte(importe);
		
		anyadirFactura(factura);
		System.out.println(factura.toString());
	}

	@Override
	public String toString() {
		String facturas = "";
		
		for (Factura factura : listaFacturas) {
			facturas += factura;
		}
		
		if(listaFacturas.size() == 0) return "No hay facturas disponibles.";
		
		return facturas;
	}
	
	
}
