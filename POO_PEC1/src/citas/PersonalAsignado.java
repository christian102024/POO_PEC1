package citas;

import model.Unidad;
import usuarios.Empleado;

public class PersonalAsignado {

	private Object personalAsignado;

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

	public boolean validarPersonalAsignado(Object personal) {

		if (personal instanceof Empleado) {
			if(((Empleado) personal).getUnidad().equals(Unidad.ENFERMERIA)) {
				return true;
			} else if (((Empleado) personal).getUnidad().equals(Unidad.MEDICINA)) {
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
