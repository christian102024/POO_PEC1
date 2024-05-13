package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Seguro;
import ui.MostrarMenu;
import util.EntradaValores;
import util.Mensajes;

public class Pacientes {
	private static Pacientes instancia;
	private List<Paciente> pacientes;

	public Pacientes() {
		super();
		this.pacientes = new ArrayList<Paciente>();
	}

	public static Pacientes getInstancia() {
		if (instancia == null) {
			instancia = new Pacientes();
		}
		return instancia;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public void addPaciente(Paciente paciente) {
		this.pacientes.add(paciente);
	}

	public void darDeAltaPaciente() {
		Scanner scanner = new Scanner(System.in);
		Paciente paciente = new Paciente();

		paciente = pedirDatosPaciente(scanner, paciente);

		if (paciente != null) {
			addPaciente(paciente);
		} else {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
		}
	}

	private Paciente pedirDatosPaciente(Scanner scanner, Paciente paciente) {
		String nombre = EntradaValores.introducirCadena("Nombre: ", paciente.getNombre());
		if (nombre.equals("cancelar")) {
			return null;
		}
		paciente.setNombre(nombre);

		String apellidos = EntradaValores.introducirCadena("Apellidos: ", paciente.getApellidos());
		if (apellidos.equals("cancelar")) {
			return null;
		}
		paciente.setApellidos(apellidos);

		String dni = EntradaValores.introducirCadena("DNI: ", paciente.getDni());
		if (dni.equals("cancelar")) {
			return null;
		}
		paciente.setDni(dni);

		String telefono = EntradaValores.introducirCadena("Teléfono: ", paciente.getTelefono());
		if (telefono.equals("cancelar")) {
			return null;
		}
		paciente.setTelefono(telefono);

		MostrarMenu.mostrarMenu("Seleccione el tipo de seguro", Arrays.asList(Seguro.SEGURIDAD_SOCIAL.toString(),
				Seguro.SEGURO_MEDICO.toString(), Seguro.NO_ASEGURADO.toString()));
		Integer opcion = EntradaValores.introducirNumeroEntero("Seleccione el tipo de seguro: ", new int[] { 1, 2, 3 });
		if (opcion != null) {
			paciente.setSeguro(Seguro.values()[opcion - 1]);
		} else {
			return null;
		}

		String seguridadSocial = EntradaValores.introducirCadena("Número de seguridad social: ");
		if (seguridadSocial.equals("cancelar")) {
			return null;
		}
		paciente.setNumeroSeguridadSocial(seguridadSocial);

		return paciente;
	}

	public void darDeBajaPaciente() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduzca el DNI del paciente a eliminar: ");
		darDeBajaPaciente(scanner.nextLine());
	}

	public void darDeBajaPaciente(String dni) {
		Integer indice = buscarIndiceDePacientePorDNI(dni);
		if(indice == -1) {
			System.out.println("Paciente no encontrado.");
		} else if (indice == null) {
			System.out.println("Proceso cancelado.");
		} else {
			pacientes.remove(indice.intValue());
			System.out.println("Paciente eliminado");
		}
	}

	public Paciente buscarPacientePorDNI() {
		boolean continuar = true;

		while (continuar) {
			Integer indice = buscarIndiceDePacientePorDNI();
			if (indice == null)
				continuar = false;
			else if (indice == -1)
				System.out.println("Paciente no encontrado.");
			else
				return pacientes.get(indice);

		}
		return null;
	}

	public int buscarIndiceDePacientePorDNI() {
		Scanner scanner = new Scanner(System.in);
		String dni = EntradaValores.introducirCadena("Introduzca el DNI del paciente: ");

		if (dni.equals("cancelar")) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return -1;
		}

		return buscarIndiceDePacientePorDNI(dni);
	}

	public Integer buscarIndiceDePacientePorDNI(String dni) {
		if(dni == "cancelar") return null;
		
		for (int i = 0; i < pacientes.size(); i++) {
			Paciente paciente = pacientes.get(i);
			if (paciente.getDni().equals(dni)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		String resultadoConcatenado = "";
		for (Paciente paciente : pacientes) {
			resultadoConcatenado += paciente + "\n";
		}
		if (pacientes.size() == 0) {
			resultadoConcatenado += "No existen pacientes dados de alta.";
		}
		return resultadoConcatenado;
	}
	
	
}
