package zoo;

public abstract class SerVivo {

	private String nombre;
	private int edad;

	public SerVivo() {

	}

	public SerVivo(String nombre, int edad) {

		this.nombre = nombre;
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "SerVivo [Nombre=" + nombre + ", edad=" + edad + "]";
	}
}
