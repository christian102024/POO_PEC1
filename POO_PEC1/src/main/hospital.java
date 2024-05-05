package main;

import java.util.ArrayList;
import java.util.Arrays;

import model.Unidad;
import ui.Menus;
import ui.MostrarMenu;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Medico;

public class hospital {

	public static void main(String[] args) {
		
		ArrayList<String> opcionesMenu = new ArrayList<String>(Arrays.asList("Dar de alta a empleado"));
		Empleados empleados = new Empleados();
		empleados.addEmpleado(new Empleado("Christian", "Jurado Maique", "18098277W", "629932957", Unidad.GERENCIA));
		Menus menus = new Menus(empleados);
		
		menus.mostrarMenuGestionHospital();
		
		
	}
}	
