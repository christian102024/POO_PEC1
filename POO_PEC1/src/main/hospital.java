package main;

import java.util.ArrayList;
import java.util.Arrays;

import model.Unidad;
import ui.Menus;
import ui.MostrarMenu;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Estudiante;
import usuarios.Estudiantes;
import usuarios.Medico;

public class hospital {

	public static void main(String[] args) {
		
		ArrayList<String> opcionesMenu = new ArrayList<String>(Arrays.asList("Dar de alta a empleado"));
		Empleados empleados = Empleados.getInstancia();
		Empleado empleado = new Empleado("Christian", "Jurado Maique", "18098277W", "629932957", Unidad.GERENCIA);
		empleados.addEmpleado(empleado);
		
		Estudiantes estudiantes = new Estudiantes();
		estudiantes.addEstudiante(new Estudiante("Pedro", "Fraga", "141654214X", "645124521", empleado));
		
		Menus menus = new Menus(empleados, estudiantes);
		
		menus.mostrarMenuPrincipal();
		
		
	}
}	
