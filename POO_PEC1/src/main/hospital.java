package main;

import java.util.ArrayList;
import java.util.Arrays;

import ui.MostrarMenu;
import usuarios.Empleados;
import usuarios.Medico;

public class hospital {

	public static void main(String[] args) {
		
		ArrayList<String> opcionesMenu = new ArrayList<String>(Arrays.asList("Dar de alta a empleado"));
		MostrarMenu.mostrarMenu("MENU ADMINISTRACION", opcionesMenu);
		Empleados empleados = new Empleados();
		empleados.darDeAltaEmpleado();
		
		ArrayList<String> listaMuyLarga = new ArrayList<String>();
		
		for(int i = 0; i < 101; i++) {
			listaMuyLarga.add(String.valueOf(i) + "textoooooooooooooooooooopppppppppppppppppppp");
		}
		
		MostrarMenu.mostrarMenu("LISTA", listaMuyLarga);
	}
}
