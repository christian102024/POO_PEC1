package citas;

import model.Servicio;
import model.Unidad;
import model.Unidades;
import usuarios.Empleado;

/**
 * Representa al Personal Asignado al estudiante.
 */
public class PersonalAsignado {

	private Object personalAsignado;

    /**
     * Constructor de la clase PersonalAsignado.
     * 
     * @param personalAsignado El personal asignado.
     */
	public PersonalAsignado(Object personalAsignado) {
		super();
		this.personalAsignado = personalAsignado;
	}

	public Object getPersonalAsignado() {
		return personalAsignado;
	}

	public void setPersonalAsignado(Empleado empleado) {
		this.personalAsignado = empleado;
	}

	public void setPersonalAsignado(Cita cita) {
		this.personalAsignado = cita;
	}

	/**
     * Valida el tipo de personal asignado. El empleado debe pertener al servicio de Medicina o Enfermería.
     * 
     * @param personal El personal a validar.
     * @return true si el personal asignado es válido, false en caso contrario.
     */
	public boolean validarPersonalAsignado(Object personal) {

		if (personal instanceof Empleado) {
			Unidad unidad = ((Empleado) personal).getUnidad();
			if(Unidades.getServiciosPorUnidad(unidad).contains(Servicio.ENFERMERIA)) {
				return true;
			} else if (Unidades.getServiciosPorUnidad(unidad).contains(Servicio.MEDICINA)) {
				return true;
			} else {
				return false;
			}
			
		} else if (personal instanceof Cita) {
			return true;
		} else {
			return false;
		}
	}

}
