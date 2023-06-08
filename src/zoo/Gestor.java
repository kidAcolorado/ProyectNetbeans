package zoo;



import java.util.List;
import java.util.ArrayList;

public class Gestor implements GestorInterfaz {

	public void MostrarAnimales(ArrayList<Animal> listaAnimales) { // supoiendo que recibimos una lista de
		// animales... y no de seres vivos

		for (Animal a : listaAnimales) {
			System.out.println(a.toString());
		}
	}

	public void mostrarAnimales(ArrayList<SerVivo> listaSeresVivos) {
		/*
		 * Sin suponer nada y cogiendo al lista total que tengamos incluyendo posibles
		 * personas(hay personas que son unos autenticos animales quien sabe??)
		 */

		for (SerVivo sv : listaSeresVivos) {
			if (sv instanceof Animal) {
				System.out.println(sv.toString());
			}
		}
	}

	@Override
	public ArrayList<Animal> getAnimalByNombre(String nombre, ArrayList<Animal> listaAnimales) {
		/*
		 * entendemos que no van a volver a meter dueños aquí... pero en ese caso
		 * haríamos lo mismo que en el caso anterior, usando instanceof
		 */

		ArrayList<Animal> listaAnimalesConNombreRecibidoPorParametros = new ArrayList<>();

		for (Animal a : listaAnimales) {

			if (a.getNombre().equalsIgnoreCase(nombre)) {
				listaAnimalesConNombreRecibidoPorParametros.add(a);
			}
		}

		return listaAnimalesConNombreRecibidoPorParametros;
	}

}
