package usuarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import campus.Habitacion;
import campus.Habitaciones;
import model.Expediente;
import model.Seguro;
import ui.MostrarMenu;
import util.Cadenas;
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

	public Integer buscarIndiceDePacientePorDNI() {
		String dni = EntradaValores.introducirCadena("Introduzca el DNI del paciente: ");

		if (dni == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO.getMensaje());
			return null;
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
	
	public void ingresarPaciente() {
		Paciente paciente = buscarPacientePorDNI();
		Habitacion habitacion = buscarHabitacionPorNumero();
		paciente = addTratamiento(paciente);
		
		ingresarPaciente(paciente, habitacion);
		System.out.println("Paciente ingresado correctamente");
	}
	
	public void ingresarPaciente(Paciente paciente, Habitacion habitacion) {
		if(paciente != null && habitacion != null) {
			habitacion.setOcupada(true);
			habitacion.setPaciente(paciente);
			
			Habitaciones habitaciones = Habitaciones.getInstancia();
			habitaciones.setHabitaciones(actualizarHabitacion(paciente, habitacion, habitaciones));
		}
	}
	
	public void darAltaPacienteIngresado() {
		Paciente paciente = buscarPacientePorDNI();
		Habitaciones habitaciones = Habitaciones.getInstancia();
		if(paciente != null) {
			int indice = habitaciones.buscarIndiceHabitacionPorPaciente(paciente);
			
			if(indice != -1) {
				Habitacion habitacion = habitaciones.getHabitaciones().get(indice);
				habitacion.setOcupada(false);
				habitacion.setPaciente(null);
				System.out.println("Paciente dado de alta; Habitación desocupada.");
			}
		} else {
			return;
		}
		
		
		
	}
	
	public Habitacion buscarHabitacionPorNumero() {
		boolean encontrada = false;
		Habitacion habitacionEncontrada = null;
		
		while(!encontrada) {
			Integer numeroHabitacion = EntradaValores.introducirNumeroEntero("Introduzca el número de habitación: ");
			
			Habitaciones habitaciones = Habitaciones.getInstancia();
			for (int i = 0; i < habitaciones.getHabitaciones().size(); i++) {
				
				Habitacion habitacion = habitaciones.getHabitaciones().get(i);
				
				if(habitacion.getNumeroHabitacion() == (numeroHabitacion) && habitacion.isOcupada() == false) {
					habitacionEncontrada = habitacion;
					encontrada = true;
				} else if(habitacion.getNumeroHabitacion() == (numeroHabitacion)) {
					System.out.println("La habitación esta ocupada");
					i = habitaciones.getHabitaciones().size();
				}
			}
			if(!encontrada) System.out.println("Habitación no encontrada");			
		}
		
		return habitacionEncontrada;
	}
	
	public List<Habitacion> actualizarHabitacion(Paciente paciente, Habitacion habitacion, Habitaciones habitaciones) {
		List<Habitacion> listaHabitaciones = habitaciones.getHabitaciones();
		
		int indice = habitaciones.buscarHabitacionPorNumero(habitacion.getNumeroHabitacion());
		
		if(indice != -1) {
			listaHabitaciones.set(indice, habitacion);
		}
		
		return listaHabitaciones;
	}
	
	public Paciente addTratamiento(Paciente paciente) {
		boolean tratamiento = EntradaValores.introducirValorBooleano("¿Introducir procedimiento médico? (S/N): ");
		
		if(tratamiento) {
			paciente.getExpedientes().add(pedirDatosProcedimiento());
			System.out.println("Tratamiento");
		} else {
			return null;
		}
		
		return paciente;
	}
	
	private Expediente pedirDatosProcedimiento() {
		Expediente expediente = new Expediente(null, null, null);
		String procedimiento = EntradaValores.introducirCadena("Escriba el procedimiento médico: ");
		expediente.setProcedimiento(procedimiento);
		return expediente;
		
	}
	
	public void actualizarExpedientePaciente() {
		Integer indice = buscarIndiceDePacientePorDNI();
		
		if(indice != null && indice != -1) {
			Paciente paciente = pacientes.get(indice);
			Expediente expediente = pedirDatosExpediente();
			paciente.anyadirExpediente(expediente);
		} 
	}
	
	public Expediente pedirDatosExpediente() {
		Expediente expediente = new Expediente();
		String informe = EntradaValores.introducirCadena("Informe: ");
		if(informe == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setInforme(informe);
		
		String procedimiento = EntradaValores.introducirCadena("Procedimiento: ");
		if(procedimiento == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setProcedimiento(procedimiento);
		
		String diagnostico = EntradaValores.introducirCadena("Diagnostico: ");
		if(diagnostico == null) {
			System.out.println(Mensajes.PROCESO_CANCELADO);
			return null;
		}
		expediente.setDiagnostico(diagnostico);
		
		return expediente;
		
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
