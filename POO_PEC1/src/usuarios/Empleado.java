package usuarios;
import model.Horario;
import model.Turno;
import model.HorariosPorTurno;
import model.Unidad;
import usuarios.Usuario;

public class Empleado extends Usuario {
	private Unidad unidad;
	private Turno turno;
	private Horario horario;

	public Empleado(String nombre, String apellidos, String dni, String telefono, Unidad unidad) {
		super(nombre, apellidos, dni, telefono);
		this.unidad = unidad;
		this.turno = Turno.DIA;
		this.horario = HorariosPorTurno.HORARIO_DIA.getHorario();
	}
	
	public Empleado() {
		super();
		this.turno = Turno.DIA;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

	@Override
	public String toString() {
		return super.toString() + ", unidad: " + unidad.getValor() + ", turno: " + turno;
	}


	
	
	
	
}
