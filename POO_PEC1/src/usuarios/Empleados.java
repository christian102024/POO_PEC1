package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Servicio;
import model.Turno;
import model.Unidad;
import model.Unidades;
import ui.MostrarMenu;
import util.EntradaValores;
import util.Mensajes;

/**
 * Esta clase representa una colección de empleados.
 */
public class Empleados {
	private static Empleados instancia;
	private List<Empleado> empleados;

	/**
	 * Constructor por defecto de la clase Empleados.
	 */
	public Empleados() {
		super();
		this.empleados = new ArrayList<Empleado>();
	}

	/**
	 * Obtiene la instancia única de la clase Empleados utilizando el patrón
	 * Singleton.
	 *
	 * @return La instancia única de la clase Empleados.
	 */
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

	/**
	 * Inicia el proceso de alta de un empleado.
	 *
	 * @param scanner El objeto Scanner utilizado para la entrada de datos.
	 */
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

	/**
	 * Solicita al usuario los datos necesarios para crear o modificar un empleado.
	 * 
	 * @param scanner  El objeto Scanner utilizado para la entrada de datos.
	 * @param empleado El objeto Empleado al que se le asignarán los datos
	 *                 introducidos por el usuario.
	 * @return El objeto Empleado con los datos introducidos por el usuario, o null
	 *         si el proceso es cancelado.
	 */
	private Empleado pedirDatosEmpleado(Scanner scanner, Empleado empleado) {

		String nombre = EntradaValores.introducirCadena("Nombre: ", empleado.getNombre());
		if (nombre == null) {
			return null;
		}
		empleado.setNombre(nombre);

		String apellidos = EntradaValores.introducirCadena("Apellidos: ", empleado.getApellidos());
		if (apellidos == null) {
			return null;
		}
		empleado.setApellidos(apellidos);

		String dni = EntradaValores.introducirCadena("DNI: ", empleado.getDni());
		if (dni == null) {
			return null;
		}
		empleado.setDni(dni);

		String telefono = EntradaValores.introducirCadena("Teléfono: ", empleado.getTelefono());
		if (telefono == null) {
			return null;
		}
		empleado.setTelefono(telefono);

		Servicio servicio = seleccionarServicio();

		if (servicio != null) {
			Unidad unidad = seleccionarUnidad(servicio);
			if (unidad != null)
				empleado.setUnidad(unidad);
		}

		return empleado;
	}

	/**
	 * Solicita al usuario que seleccione un servicio de una lista.
	 * 
	 * @return El servicio seleccionado por el usuario, o null si se cancela el
	 *         proceso.
	 */
	private static Servicio seleccionarServicio() {
		List<String> listaServicios = Servicio.getValores();
		int opcion = -1;
		while (opcion < 1 || opcion > 4) {
			MostrarMenu.mostrarMenu("SELECCIONE UN SECTOR", listaServicios);
			opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción", new int[] { 1, 2, 3, 4 });

			if (opcion >= 1 || opcion <= 4) {
				return Servicio.values()[opcion - 1];
			}
		}

		return null;

	}

	/**
	 * Solicita al usuario que seleccione una unidad de una lista.
	 * 
	 * @param servicio El servicio del que se seleccionará la unidad.
	 * @return La unidad seleccionada por el usuario.
	 */
	private static Unidad seleccionarUnidad(Servicio servicio) {
		List<Unidad> listaUnidades = Unidades.getUnidadesDisponiblesPorServicio(servicio);
		List<String> lista = Unidades.getValoresUnidadesDisponiblesPorServicio(servicio);

		MostrarMenu.mostrarMenu("SELECCIONE UNA UNIDAD", lista);
		int opcion = EntradaValores.introducirNumeroEntero("Seleccione una opción",
				new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

		if (opcion >= 1 || opcion <= 10) {
			return listaUnidades.get(opcion - 1);
		} else {
			System.out.println("Entrada incorrecta: la entrada no puede ser nula o vacía");
			return null;
		}
	}

	/**
	 * Inicia el proceso de baja de un empleado, solicitando al usuario el DNI del empleado a eliminar.
	 */
	public void darDeBajaEmpleado() {
		String dni = EntradaValores.introducirCadena("Introduzca el DNI del empleado a eliminar: ");
		if(dni == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return;
		}
		darDeBajaEmpleado(dni);
	}

	/**
	 * Elimina un empleado de la lista de empleados utilizando su DNI.
	 *
	 * @param dni El DNI del empleado a eliminar.
	 */
	public void darDeBajaEmpleado(String dni) {
		int indice = buscarIndiceDeEmpleadoPorDNI(dni);
		empleados.remove(indice);
	}

	/**
	 * Busca un empleado por su número de DNI.
	 * 
	 * @return El objeto Empleado encontrado, o null si el proceso es cancelado.
	 */
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

	/**
	 * Busca un empleado por su número de DNI y muestra el resultado por consola.
	 */
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

	/**
	 * Muestra los empleados clasificados por unidad, mostrando sus datos por consola.
	 */
	public void mostrarEmpleadosClasificadosPorUnidad() {
		for (Unidad unidad : Unidad.values()) {

			List<Empleado> listaEmpleados = buscarEmpleadosPorUnidad(unidad);
			if (listaEmpleados.size() > 0) {
				System.out.println("EMPLEADOS UNIDAD " + unidad.getValor());
				imprimirEmpleados(listaEmpleados);
				System.out.println();
			}
		}
	}

	/**
	 * Busca los empleados que pertenecen a una unidad específica.
	 * 
	 * @param unidad La unidad a la que pertenecen los empleados.
	 * @return Una lista de empleados que pertenecen a la unidad especificada.
	 */
	public List<Empleado> buscarEmpleadosPorUnidad(Unidad unidad) {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();

		for (Empleado empleado : empleados) {
			if (empleado.getUnidad().equals(unidad)) {
				listaEmpleados.add(empleado);
			}
		}

		return listaEmpleados;
	}

	/**
	 * Imprime los datos de los empleados de una lista por consola.
	 * 
	 * @param listaEmpleados La lista de empleados cuyos datos se imprimirán.
	 */
	public void imprimirEmpleados(List<Empleado> listaEmpleados) {
		for (Empleado empleado : listaEmpleados) {
			System.out.println(empleado);
		}
	}

	/**
	 * Modifica los datos de un empleado existente.
	 */
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

	/**
	 * Asigna un turno a un empleado específico.
	 */
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

			if (dni.equals("cancelar")) {
				System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
				return;
			}
		} while (indice == -1);
		Empleado empleado = empleados.get(indice);

		int opcion = -1;

		if (empleado.getUnidad().equals(Unidad.URGENCIAS)) {
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
