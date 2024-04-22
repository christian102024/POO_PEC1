package usuarios;

import java.util.ArrayList;
import java.util.List;

public class Empleados {
	private List<Empleado> empleados;

	public Empleados() {
		super();
		this.empleados = new ArrayList<Empleado>();
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public void darDeAltaEmpleado() {
		System.out.println("Iniciando el proceso de alta de un empleado...");
		
	}
	
	
}
