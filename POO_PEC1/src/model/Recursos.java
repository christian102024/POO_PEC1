package model;

import java.util.ArrayList;
import java.util.List;

import usuarios.Empleado;
import usuarios.Empleados;
import util.EntradaValores;
import util.Mensajes;

public class Recursos {

	private static Recursos instancia;
	private List<Recurso> recursos;

	public Recursos() {
		super();
		this.recursos = new ArrayList<Recurso>();
	}

	public static Recursos getInstancia() {
		if(instancia == null) {
			instancia = new Recursos();
		}
		return instancia;
	}

	public Recursos(List<Recurso> recursos) {
		super();
		this.recursos = recursos;
	}
	
	public List<Recurso> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<Recurso> recursos) {
		this.recursos = recursos;
	}
	
	public void addRecurso(Recurso recurso) {
		this.recursos.add(recurso);
	}
	
	public void removeRecurso(Recurso recurso) {
		this.recursos.remove(recurso);		
	}
	
	public void removeRecurso(int indice) {
		this.recursos.remove(indice);
	}
	
	public void darDeAltaRecurso() {
		Recurso recurso = pedirDatosRecurso();
		
		if(recurso != null) {
			addRecurso(recurso);
		} else {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
		}
	}
	
	public Recurso pedirDatosRecurso() {
		Recurso recurso = new Recurso();
		
		String nombre = EntradaValores.introducirCadena("Introduzca el nombre del recurso: ");
		if(nombre == null) return null;
		recurso.setNombre(nombre);
		
		String descripcion = EntradaValores.introducirCadena("Introduzca la descripción del problema o avería: ");
		if(descripcion == null) return null;
		recurso.setDescripcionProblema(descripcion);
		
		Empleado empleado = Empleados.getInstancia().buscarEmpleadoPorDNI();
		
		if(empleado == null) return null;
		Unidad unidad = empleado.getUnidad();
		
		if(unidad == null) {
			System.out.println("El empleado no tiene una unidad asignada.");
			return null;
		}
		
		List<Servicio> servicios = Unidades.getServiciosPorUnidad(unidad);
		
		if(servicios.contains(Servicio.SOPORTE)) {
			recurso.setEmpleado(empleado);
		} else {
			System.out.println("El empleado no pertenece al servicio de SOPORTE.");
			return null;
		}
		
		return recurso;
	}
	
	public void darDeBajaRecurso() {
		System.out.println(this);

		if(recursos.size() == 0) return;
		Integer indice = EntradaValores.introducirNumeroEntero("Introduzca el número del recurso a eliminar: ");
		
		if(indice == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return;
		}
		
		if(indice >= 0 && indice < recursos.size()) {
			removeRecurso(indice);
			System.out.println("Recurso eliminado satisfactoriamente");
		} else {
			System.out.println("El indice especificado no corresponde con ningún recurso dado de alta.");
		}
	}

	@Override
	public String toString() {
		String cadena = "";
		int indice = 0;
		
		for (Recurso recurso : recursos) {
			cadena += indice + ".\t" + recurso + "\n";
			indice++;
		}
		
		if(recursos.size() == 0) return "No hay recursos dañados disponibles.";
		
		return cadena;
	}
	
	
}
