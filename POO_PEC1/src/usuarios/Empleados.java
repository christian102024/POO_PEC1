package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Servicio;
import model.Turno;
import model.Unidad;
import model.Unidades;
import ui.Menus;
import ui.MostrarMenu;
import util.EntradaValores;
import util.Mensajes;

public class Empleados {
	private static Empleados instancia;
	private List<Empleado> empleados;

	public Empleados() {
		super();
		this.empleados = new ArrayList<Empleado>();
	}

	public static Empleados getInstancia() {
		if (instancia == null) {
			instancia = new Empleados();
		}
		return instancia;
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void addEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
	}

	public void addEmpleado(Empleado empleado, int indice) {
		if (indice < empleados.size()) {
			this.empleados.remove(indice);
		}
		this.empleados.add(indice, empleado);
	}

	public void darDeAltaEmpleado(Scanner scanner) {
		System.out.println("Iniciando el proceso de alta de un empleado...");
		Empleado empleado = new Empleado();

		empleado = pedirDatosEmpleado(scanner, empleado);
		if (empleado != null) {
			addEmpleado(empleado);
		} else {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
		}

	}

	private Empleado pedirDatosEmpleado(Scanner scanner, Empleado empleado) {

		String nombre = EntradaValores.introducirCadena("Nombre: ", empleado.getNombre());
		if (nombre.equals("cancelar")) {
			return null;
		}
		empleado.setNombre(nombre);

		String apellidos = EntradaValores.introducirCadena("Apellidos: ", empleado.getApellidos());
		if (apellidos.equals("cancelar")) {
			return null;
		}
		empleado.setApellidos(apellidos);

		String dni = EntradaValores.introducirCadena("DNI: ", empleado.getDni());
		if (dni.equals("cancelar")) {
			return null;
		}
		empleado.setDni(dni);

		String telefono = EntradaValores.introducirCadena("Teléfono: ", empleado.getTelefono());
		if (telefono.equals("cancelar")) {
			return null;
		}
		empleado.setTelefono(telefono);
		
		Servicio servicio = seleccionarServicio();
		
		if(servicio != null) {
			Unidad unidad = seleccionarUnidad(servicio);
			empleado.setUnidad(unidad);
		}

		return empleado;
	}
	
	private static Servicio seleccionarServicio() {
		List<String> listaServicios = Servicio.getValores();
		int opcion = -1;
		while(opcion < 1 || opcion > 4) {
			MostrarMenu.mostrarMenu("SELECCIONE UN SECTOR", listaServicios);
			opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción", new int[] { 1, 2, 3, 4 });
			
			if (opcion >= 1 || opcion <= 4) {
				return Servicio.values()[opcion-1];
			}			
		}
		
		return null;
		
	}

	private static Unidad seleccionarUnidad(Servicio servicio) {
		List<Unidad> listaUnidades = Unidades.getUnidadesDisponiblesPorServicio(servicio);
		List<String> lista = Unidades.getValoresUnidadesDisponiblesPorServicio(servicio);
		

		MostrarMenu.mostrarMenu("SELECCIONE UNA UNIDAD", lista);
		int opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción",
				new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

		if (opcion >= 1 || opcion <= 10) {
			return listaUnidades.get(opcion-1);
		} else {
			throw new Error("Entrada incorrecta: la entrada no puede ser nula o vacía");
		}
	}

	public void darDeBajaEmpleado() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduzca el DNI del empleado a eliminar: ");
		darDeBajaEmpleado(scanner.nextLine());
	}

	public void darDeBajaEmpleado(String dni) {
		int indice = buscarIndiceDeEmpleadoPorDNI(dni);
		empleados.remove(indice);
	}

	public Empleado buscarEmpleadoPorDNI() {
		boolean continuar = true;

		while (continuar) {
			Integer indice = buscarIndiceDeEmpleadoPorDNI();
			if (indice == null) {
				continuar = false;
			} else if (indice == -1) {
				System.out.println("Empleado no encontrado.");
			} else {
				return empleados.get(indice);
			}
		}

		return null;
	}

	public void mostrarEmpleadoBuscadoPorDNI() {
		Integer indice = buscarIndiceDeEmpleadoPorDNI();
		if (indice == null) {
			return;
		} else if (indice == -1) {
			System.out.println("Empleado no encontrado.");
		} else {
			System.out.println(empleados.get(indice));
		}
	}

	/**
	 * 
	 * @param dni
	 * @return El índice del usuario en la lista de empleados
	 */
	public Integer buscarIndiceDeEmpleadoPorDNI() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduzca el DNI del empleado: ");
		String respuesta = scanner.nextLine();
		if (respuesta.equals("cancelar")) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return null;
		}
		return buscarIndiceDeEmpleadoPorDNI(respuesta);
	}

	/**
	 * 
	 * @param dni
	 * @return El índice del usuario en la lista de empleados
	 */
	public int buscarIndiceDeEmpleadoPorDNI(String dni) {
		for (int i = 0; i < empleados.size(); i++) {
			Empleado empleado = empleados.get(i);
			if (empleado.getDni().equals(dni)) {
				return i;
			}
		}

		return -1;
	}

	public void modificarEmpleado() {
		int indice = -1;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.print("Introduzca el dni del empleado al que quiere modificar: ");
			String dni = scanner.nextLine();
			indice = buscarIndiceDeEmpleadoPorDNI(dni);
			
			if (dni.equals("cancelar")) {
				System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
				return;
			}

			if (indice == -1) {
				System.out.println("DNI no encontrado.");
			}

		} while (indice == -1);

		Empleado empleado = empleados.get(indice);
		empleado = pedirDatosEmpleado(scanner, empleado);
		if (empleado != null) {
			addEmpleado(empleado);
		} else {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
		}

	}

	public void asignarTurno() {
		int indice = -1;
		do {
			System.out.print("Introduzca el dni del empleado al que quiere modificar el turno: ");
			Scanner scanner = new Scanner(System.in);
			String dni = scanner.nextLine();
			indice = buscarIndiceDeEmpleadoPorDNI(dni);

			if (indice == -1) {
				System.out.println("DNI no encontrado.");
			}

			if (dni.equals("cancel")) {
				System.out.println("PROCESO CANCELADO");
				return;
			}
		} while (indice == -1);
		Empleado empleado = empleados.get(indice);

		int opcion = -1;

		if (empleado.getUnidad().equals(Unidad.URGENCAS)) {
			MostrarMenu.mostrarMenu("Seleccione el turno para el empleado",
					Arrays.asList("Turno de día", "Turno de noche", "Cancelar"));
			opcion = EntradaValores.introducirNumeroEntero("Introduce el turno al que se quiere asignar el empleado",
					new int[] { 1, 2, 3 });

			switch (opcion) {
			case 1:
				empleado.setTurno(Turno.DIA);
				break;

			case 2:
				empleado.setTurno(Turno.NOCHE);
				break;
			default:
				break;
			}
		} else {
			MostrarMenu.mostrarMenu("Seleccione el turno para el empleado", Arrays.asList("Turno de día", "Cancelar"));
			System.out.println("El empleado no esta en una unidad que permita los turnos nocturnos.");
			opcion = EntradaValores.introducirNumeroEntero("Introduce el turno al que se quiere asignar el empleado: ",
					new int[] { 1, 2 });

			switch (opcion) {
			case 1:
				empleado.setTurno(Turno.DIA);
				break;

			default:
				break;
			}
		}

	}

	@Override
	public String toString() {
		String resultado = "";
		for (Empleado empleado : empleados) {
			resultado += empleado + "\n";
		}

		if (empleados.size() == 0) {
			resultado += "No existen empleados dados de alta.";
		}

		return resultado;
	}

}
