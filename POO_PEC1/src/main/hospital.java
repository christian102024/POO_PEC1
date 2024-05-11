package main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import citas.Agenda;
import citas.Cita;
import model.Seguro;
import model.Unidad;
import ui.Menus;
import ui.MostrarMenu;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Estudiante;
import usuarios.Estudiantes;
import usuarios.Medico;
import usuarios.Paciente;
import usuarios.PersonalSanitario;

public class hospital {

	public static void main(String[] args) {
		
		Empleados empleados = Empleados.getInstancia();
		Empleado empleado = new Empleado("Christian", "Jurado Maique", "18098277W", "629932957", Unidad.GERENCIA);
		Paciente paciente = new Paciente("Pedro", "Plana", "18475122X", "645142541", Seguro.SEGURIDAD_SOCIAL, "AR1243451314", null);
		List<Cita> listaCitas = new ArrayList<Cita>();
		listaCitas.add(new Cita(paciente, empleado, LocalDateTime.now(), null, false));
		
		
		PersonalSanitario personalSanitario = new PersonalSanitario("María", "Latorre", "18098278W", "624142536", Unidad.MEDICINA, new Agenda(listaCitas));
		empleados.addEmpleado(empleado);
		empleados.addEmpleado(personalSanitario);
		
		Estudiantes estudiantes = new Estudiantes();
		estudiantes.addEstudiante(new Estudiante("Pedro", "Fraga", "141654214X", "645124521", empleado));
		
		Menus menus = new Menus(empleados, estudiantes);
		
		menus.mostrarMenuPrincipal();
		
		
	}
}	
