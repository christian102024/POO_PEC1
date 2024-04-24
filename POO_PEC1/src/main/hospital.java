package main;

import java.util.ArrayList;
import java.util.Arrays;

import ui.MostrarMenu;
import usuarios.Empleados;
import usuarios.Medico;

public class hospital {

	public static void main(String[] args) {
		
		ArrayList<String> opcionesMenu = new ArrayList<String>(Arrays.asList("Dar de alta a empleado"));
		MostrarMenu.mostrarMenu(opcionesMenu, "MENU ADMINISTRACION");
		Empleados empleados = new Empleados();
		empleados.darDeAltaEmpleado();
	}
}
