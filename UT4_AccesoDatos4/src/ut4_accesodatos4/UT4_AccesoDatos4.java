/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ut4_accesodatos4;

import java.util.InputMismatchException;

import java.sql.Connection;

import java.util.Scanner;

import dto.*;
import dao.*;
import java.util.ArrayList;

/**
 * Se desea desarrollar un sistema de reservas de vuelos que permita a los
 * usuarios buscar y reservar vuelos. Para ello, se necesita desarrollar un
 * programa en Java que permita conectarse a una base de datos MySQL que
 * contenga información de vuelos, pasajeros y reservas. El programa debe
 * permitir a los usuarios realizar las siguientes acciones: 1. Buscar vuelos
 * disponibles para una fecha y origen/destino determinado. 2. Mostrar
 * información detallada de un vuelo dado su id, incluyendo información sobre
 * los pasajeros que ya han realizado reservas en dicho vuelo. 3. Dar de alta un
 * pasajero. 4. Realizar una reserva en un vuelo seleccionado, indicando el
 * asiento y el id del pasajero (esto NUNCA será así en un software real pues
 * existirá un sistema de login). Para simplificar el ejercicio, solo podrán
 * reservar aquellos pasajeros que ya existan en base de datos. 5. Cancelar una
 * reserva existente, indicando el número de reserva. El programa debe asegurar
 * la integridad de los datos, evitando que se realicen reservas de asientos ya
 * ocupados.
 *
 * @author Kid A
 */
public class UT4_AccesoDatos4 {

    private static Scanner reader;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        reader = new Scanner(System.in);
        int opcionMenu = -1;

        viajesDAOimpl v1 = new viajesDAOimpl();

        v1.loadDriver();
        System.out.println("");

        v1.connect();
        System.out.println("");

        

        do {
            try {
                System.out.println("========== MENÚ PRINCIPAL ==========");
                System.out.println("<OPCIÓN 1> Buscar vuelos disponibles");
                System.out.println("<OPCIÓN 2> Mostrar información detallada de un vuelo");
                System.out.println("<OPCIÓN 3> Dar de alta un pasajero");
                System.out.println("<OPCIÓN 4> Realizar una reserva");
                System.out.println("<OPCIÓN 5> Cancelar una reserva existente");
                System.out.println("<OPCIÓN 0> Salir");
                System.out.print("Seleccione una opción: ");

                opcionMenu = reader.nextInt();
                reader.nextLine(); // Consumir el salto de línea después de leer la opción

                switch (opcionMenu) {
                    case 1:
                        opcionBuscarVuelosDisponibles(v1);
                        break;
                    case 2:
                        opcionMostrarInformacionDetalladaVuelo(v1);
                        break;
                    case 3:
                        opcionDarDeAltaPasajero(v1);
                        break;
                    case 4:
                        opcionRealizarReserva(v1);
                        break;
                    case 5:
                        opcionCancelarReserva(v1);
                        break;
                    case 0:
                        System.out.println("Gracias por usar nuestros servicios. Hasta la próxima.");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\n");
                System.out.println("ERROR: Debes ingresar un número válido");
                limpiarBuffer();
            }

        } while (opcionMenu != 0);

        v1.close();

    }

    /**
     * Método para limpiar el buffer del Scanner.
     */
    public static void limpiarBuffer() {
        reader.nextLine();
    }

    /**
     *
     * Opción del menú que busca vuelos disponibles en función de la fecha de
     * salida, ciudad de origen y ciudad de destino.
     *
     *
     *
     * @param viajes El objeto que implementa la interfaz viajesDAO para acceder
     * a la capa de persistencia.
     */
    public static void opcionBuscarVuelosDisponibles(viajesDAO viajes) {

        System.out.println("Introduce la fecha de salida del vuelo (YYYY-MM-DD):");
        String fecha = reader.nextLine();

        System.out.println("Introduce la ciudad de origen:");
        String origen = reader.nextLine();

        System.out.println("Introduce la ciudad de destino:");
        String destino = reader.nextLine();

        System.out.println("");

        viajes.consultaVuelosDisponibles(fecha, origen, destino);

    }

    /**
     * Opción del menú que muestra la información detallada de un vuelo,
     * incluyendo los datos del vuelo y los pasajeros que han realizado reservas
     * en él.
     *
     *
     * @param viajes
     */
    public static void opcionMostrarInformacionDetalladaVuelo(viajesDAO viajes) {
        int vueloId;

        try {
            System.out.println("Introduce el id del vuelo");
            vueloId = reader.nextInt();
            limpiarBuffer();

            viajes.consultaInformacionVuelo(vueloId);
            viajes.consultaPasajerosReservasVuelo(vueloId);
        } catch (InputMismatchException e) {
            // Manejo de la excepción InputMismatchException (entrada inválida)
            System.out.println("Error al introducir el id del vuelo: entrada inválida.");
        }

    }

    /**
     * Opción del menú que realiza el proceso de dar de alta a un pasajero en el
     * sistema.
     *
     *
     * @param viajes La implementación de la interfaz ViajesDAO para interactuar
     * con la base de datos.
     */
    public static void opcionDarDeAltaPasajero(viajesDAO viajes) {
        Pasajero p1;

        p1 = crearPasajeroPidiendoDatos();

        viajes.insertPasajero(p1);

    }

    /**
     * Opción del Menú que realiza una reserva de vuelo.
     *
     *
     * @param viajes El objeto viajesDAO utilizado para realizar la reserva.
     *
     */
    public static void opcionRealizarReserva(viajesDAO viajes) {

        Reserva r1;

        r1 = crearReservaPidiendoDatos();

        viajes.checkAsientoYRealizarReserva(r1.getIdVuelo(), r1.getIdPasajero(), r1.getAsiento());

    }

    /**
     * Opción del menú para cancelar una reserva existente.
     *
     *
     * @param viajes El objeto que implementa la interfaz viajesDAO para acceder
     * a los datos de las reservas.
     */
    public static void opcionCancelarReserva(viajesDAO viajes) {

        int numeroReserva;
        ArrayList<Reserva> listaReservas;
        
        System.out.println("Estas son las reservas realizadas: ");
        System.out.println("");
        
        listaReservas = viajes.getReservas();
        mostrarListaReservaa(listaReservas);
        System.out.println("");
        

        System.out.println("Introduce el número de la Reserva que quiere cancelar");
        numeroReserva = reader.nextInt();
        limpiarBuffer();

        viajes.cancelarReserva(numeroReserva);

    }
    
    /**
     * Método que muestra las diferenes reservas de una lista de reservas
     * @param listaReservas 
     */
    private static void mostrarListaReservaa(ArrayList<Reserva> listaReservas){
        
        for( Reserva r : listaReservas){
            System.out.println(r.toString());
            
        }
        
    }

    /**
     * Solicita los datos de un pasajero al usuario y crea un objeto Pasajero
     * con los datos ingresados.
     *
     * @return El objeto Pasajero creado con los datos ingresados por el
     * usuario.
     */
    public static Pasajero crearPasajeroPidiendoDatos() {
        Pasajero p1;
        String nombre, apellido, fechaNacimiento;

        System.out.println("Introduce nombre de pasajero");
        nombre = reader.nextLine();

        System.out.println("Introduce apellido/s");
        apellido = reader.nextLine();

        System.out.println("Introduce fecha de nacimiento (YYYY-MM-DD)");
        fechaNacimiento = reader.nextLine();

        p1 = new Pasajero(nombre, apellido, fechaNacimiento);

        return p1;
    }

    /**
     * Crea un objeto Reserva solicitando los datos al usuario.
     *
     * @return El objeto Reserva creado con los datos ingresados por el usuario.
     */
    public static Reserva crearReservaPidiendoDatos() {
        Reserva r1;
        String asiento;
        int idVuelo, idPasajero;

        System.out.println("Introduce el id del vuelo");
        idVuelo = reader.nextInt();
        limpiarBuffer();

        System.out.println("Introduce el id del pasajero");
        idPasajero = reader.nextInt();
        limpiarBuffer();

        System.out.println("Introduce el asiento");
        asiento = reader.nextLine();

        r1 = new Reserva(idVuelo, idPasajero, asiento);

        return r1;
    }
}
