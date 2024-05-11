package citas;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import util.Cadenas;
import util.FormatosFechas;

public class Agenda {

	private List<Cita> listaCitas;

	public Agenda(List<Cita> listaCitas) {
		super();
		this.listaCitas = listaCitas;
	}

	public Agenda() {
		super();
		this.listaCitas = new ArrayList<Cita>();
	}

	public List<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(List<Cita> listaCitas) {
		this.listaCitas = listaCitas;
	}
	
	public void anyadirCita(Cita cita) {
		this.listaCitas.add(cita);
	}
	
	public void eliminarcita(Cita cita) {
		this.listaCitas.remove(cita);
	}

	@Override
	public String toString() {
		mostrarCitas();
		return "Agenda [listaCitas=" + listaCitas + "]";
	}
	
	public void mostrarCitas() {
		
		int longitudDefectoPaciente = longitudMayorNombrePaciente(listaCitas);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String columnaPaciente = Cadenas.repeatString("-", longitudDefectoPaciente);
		
		System.out.println("---------------------------------------------------------+"+ columnaPaciente  +"+");
	    System.out.println("|    Fecha    | Hora de Inicio | Hora de Fin | Reservado | Paciente |");
	    System.out.println("---------------------------------------------------------+"+ columnaPaciente + "+");System.out.println();
	    
	    // Imprimir cada cita en la lista
	    for (Cita cita : listaCitas) {
	        imprimirFilaCita(cita, formatter, longitudDefectoPaciente);
	    }

	    // Línea de cierre de la tabla
	    imprimirLineaCierre(longitudDefectoPaciente);
	}
	
	private int longitudMayorNombrePaciente(List<Cita> citas) {
		int longitudMayorDeNombre = 10;
		for (Cita cita : listaCitas) {
			String nombre = cita.getEmpleado().getNombre();
			String apellidos = cita.getEmpleado().getApellidos();
			
			String nombreCompleto = nombre + " " + apellidos;
			if(nombreCompleto.length() > longitudMayorDeNombre) {
				longitudMayorDeNombre = nombreCompleto.length();
			}
		}
		return longitudMayorDeNombre;
	}
	
	private void imprimirFilaCita(Cita cita, DateTimeFormatter formatter, int anchoNombreMaximo) {
		String dia = cita.getFechaInicio() != null ? cita.getFechaInicio().format(FormatosFechas.FORMATO_DIA.getFormatter()) : "-";
	    String fechaInicio = cita.getFechaInicio() != null ? cita.getFechaInicio().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
	    String fechaFin = cita.getFechaFin() != null ? cita.getFechaFin().format(FormatosFechas.FORMATO_HORA.getFormatter()) : "-";
	    String reservado = cita.isReservado() ? "Sí" : "No";
	    String nombrePaciente = cita.getPaciente() != null ? cita.getPaciente().getNombre() : "";

	    // Determinar el ancho de la columna para el nombre del paciente
	    int anchoColumnaPaciente = Math.max(anchoNombreMaximo, 12); // Mínimo ancho de columna de 12 caracteres
	    String formatoColumnaPaciente = "%-" + anchoColumnaPaciente + "s";

	    System.out.printf("| %-11s | %-14s | %-12s | %-9s | " + formatoColumnaPaciente + " |\n",
	    		dia, fechaInicio, fechaFin, reservado, nombrePaciente);
	}

	private void imprimirLineaCierre(int anchoNombreMaximo) {
	    String lineaCierre = "------------------------------------------------------------------";
	    int longitudAdicional = Math.max(0, anchoNombreMaximo - 8); // Determinar longitud adicional basada en el nombre del paciente
	    for (int i = 0; i < longitudAdicional; i++) {
	        lineaCierre += "-"; // Agregar guiones adicionales según la longitud del nombre del paciente
	    }
	    System.out.println(lineaCierre);
	}
}
