package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import model.Seguro;
import ui.MostrarMenu;
import util.EntradaValores;

public class Pacientes {
	private static Pacientes instancia;
	private List<Paciente> pacientes;
	
	public Pacientes() {
		super();
		this.pacientes = new ArrayList<Paciente>();
	}
	
	public static Pacientes getInstancia() {
		if(instancia == null) {
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
		
		if(paciente != null) {
			addPaciente(paciente);
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
		
		MostrarMenu.mostrarMenu("Seleccione el tipo de seguro", Arrays.asList(Seguro.SEGURIDAD_SOCIAL.toString(), Seguro.SEGURO_MEDICO.toString(), Seguro.NO_ASEGURADO.toString()));
		Integer opcion = EntradaValores.introducirNumeroEntero("Seleccione el tipo de seguro: ", new int[]{1, 2, 3});
		if(opcion != null) {
			paciente.setSeguro(Seguro.values()[opcion-1]);
		} else {
			return null;
		}
		
		String seguridadSocial = EntradaValores.introducirCadena("Número de seguridad social: ");
		if(seguridadSocial.equals("cancelar")) {
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
		
	}
	
	
	public Paciente buscarPacientePorDNI() {
		return pacientes.get(0);
	}
}
