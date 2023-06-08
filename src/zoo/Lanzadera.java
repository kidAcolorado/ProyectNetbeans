package zoo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Lanzadera {

	

	public static void main(String[] args) {
		
		 // Declaración de variables
        ArrayList<SerVivo> listaSeresVivos = new ArrayList<>();
        ArrayList<Animal> listaAnimales = new ArrayList<>();
        
        // Instanciando
        Gestor g1 = new Gestor();

        Dni dni1 = new Dni(11111111, "y");
        Dueno du1 = new Dueno("Paca", 20, dni1);

        Dni dni2 = new Dni(22222222, "Q");
        Dueno du2 = new Dueno("Pepa", 50, dni2);

        Domestico d1 = new Domestico("Sigurd", 7, du1);
        Domestico d2 = new Domestico("Halley", 3, du2);

        Salvaje s1 = new Salvaje("Tigre: Shere Kang", 9);
        Salvaje s2 = new Salvaje("Triceratops: Polola", 4);
        
        //Poblando listas
        listaAnimales.add(d1);
        listaAnimales.add(d2);
        listaAnimales.add(s1);
        listaAnimales.add(s2);
        
        
        listaSeresVivos.add(du1);
        listaSeresVivos.add(du2);
        listaSeresVivos.add(d1);
        listaSeresVivos.add(d2);
        listaSeresVivos.add(s1);
        listaSeresVivos.add(s2);
        
        
        //Probando gestor
        System.out.println("MOSTRANDO LISTA DE ANIMALES TOTAL POR ARRAY DE ANIMALES");
        g1.MostrarAnimales(listaAnimales);
        System.out.println("");
        System.out.println("");
        
        System.out.println("MOSTRANDO LISTA DE ANIMALES TOTAL POR ARRAY DE SERES VIVOS");
        g1.mostrarAnimales(listaSeresVivos);
        System.out.println("");
        System.out.println("");
        
        
        System.out.println("MOSTRANDO LISTA DE ANIMALES QUE COINCIDEN CON NOMBRE - Sigurd");
        ArrayList<Animal> listaAnimalesPorNombre = g1.getAnimalByNombre("SiGuRd", listaAnimales);
        g1.MostrarAnimales(listaAnimalesPorNombre);
        

	
}