package citas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import model.Unidad;
import usuarios.Empleado;
import usuarios.Empleados;
import usuarios.Paciente;
import usuarios.Pacientes;
import usuarios.PersonalSanitario;
import util.EntradaValores;

public class GestionarAgenda {

	public enum TipoPersonal {ENFERMERO, MEDICO}
	
	
	public static void añadirCita(TipoPersonal tipoPersonal) {
		Empleados empleados = Empleados.getInstancia();
		Pacientes pacientes = Pacientes.getInstancia();
		
		
		Empleado empleado = empleados.buscarEmpleadoPorDNI();
		if (empleado != null) {
			PersonalSanitario personalSanitario = comprobarEmpleadoEs(tipoPersonal, empleado);

			if (personalSanitario != null) {
				Agenda agenda = personalSanitario.getAgenda();
				LocalDate fecha = EntradaValores
						.introducirFecha("Introduzca la fecha en la que quiere dar de alta la cita: ");
				MostrarAgenda.mostrarAgenda(agenda, fecha);
				LocalTime horaInicio = EntradaValores.introducirHora("Introduzca la hora de inicio: ");
				LocalTime horaFin = EntradaValores.introducirHora("Introduzca la hora de fin: ");
				Paciente paciente = pacientes.buscarPacientePorDNI();

				if (paciente == null) {
					System.out.println("Paciente no encontrado.");
				} else {

					LocalDateTime fechaHoraInicio = LocalDateTime.of(fecha, horaInicio);
					LocalDateTime fechaHoraFin = LocalDateTime.of(fecha, horaFin);

					boolean citaDisponible = agenda.comprobarCitaEstaDisponible(fecha, fechaHoraInicio,
							fechaHoraFin);

					if (citaDisponible) {
						agenda.anyadirCita(fecha, new Cita(paciente, fechaHoraInicio, fechaHoraFin, true));
						System.out.println("Cita registrada correctamente!");
					} else {
						System.out.println("La hora seleccionada no esta disponible; Proceso cancelado.");
					}
				}

			}
		} else {
			System.out.println("Proceso cancelado.");
		}
	}
	
	private static PersonalSanitario comprobarEmpleadoEs(TipoPersonal tipoPersonal, Empleado empleado) {
		if (tipoPersonal.equals(TipoPersonal.ENFERMERO)) {
			return PersonalSanitario.comprobarEmpleadoEsEnfermero(empleado, new Unidad[]{Unidad.CARDIOVASCULAR, Unidad.DIABETES, Unidad.CONSULTAS_EXTERNAS});
		} else {
			return PersonalSanitario.comprobarEmpleadoEsMedico(empleado);
		}
	}
}
