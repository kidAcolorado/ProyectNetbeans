package zoo;

public class Dueno extends SerVivo {

	private Dni dni;

	public Dueno() {
	}

	public Dueno(String nombre, int edad, Dni dni) {
		super(nombre, edad);
		this.dni = dni;
	}

	public Dni getDni() {
		return dni;
	}

	public void setDni(Dni dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return super.toString() + " - Dueno [dni=" + dni + "]";
	}

}
