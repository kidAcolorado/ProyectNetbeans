package zoo;

public class Salvajes extends Animales{

	public Salvaje(String Nombre, int edad) {
		super(Nombre, edad);
	}

	@Override
	public String toString() {
		return super.toString() + " - Salvaje []";
	}


}
