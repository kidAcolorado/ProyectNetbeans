/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Kid A
 */
public class Hotel extends Alojamiento implements ServicioHabitaciones {

    private int numEstrellas;
    private int numHabitaciones;
    private boolean servicioHabitaciones = false;

    /**
     * Crea una instancia de Hotel con el nombre, ciudad, precio, número de
     * estrellas y número de habitaciones especificados.
     *
     * @param nombre El nombre del hotel.
     * @param ciudad La ciudad en la que se encuentra el hotel.
     * @param precio El precio por noche del hotel.
     * @param numEstrellas El número de estrellas del hotel.
     * @param numHabitaciones El número de habitaciones disponibles en el hotel.
     */
    public Hotel(String nombre, String ciudad, double precio, int numEstrellas, int numHabitaciones) {
        super(nombre, ciudad, precio);
        this.numEstrellas = numEstrellas;
        this.numHabitaciones = numHabitaciones;
    }

    public int getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(int numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public boolean isServicioHabitaciones() {
        return servicioHabitaciones;
    }

    public void setServicioHabitaciones(boolean servicioHabitaciones) {
        this.servicioHabitaciones = servicioHabitaciones;
    }

    /**
     * Calcula el costo total de una reserva en el hotel.
     *
     * @param numDias El número de días de la reserva.
     * @return El costo total de la reserva.
     */
    @Override
    public double reservar(int numDias) {
        return numDias * precio;
    }

    /**
     * Solicita el servicio de habitaciones en el hotel. Establece el estado del
     * servicio de habitaciones como activo. Imprime un mensaje indicando que se
     * ha solicitado el servicio de habitaciones.
     */
    @Override
    public void solicitarServicioHabitaciones() {
        servicioHabitaciones = true;
        System.out.println("SERVICIO DE HABITACIONES SOLICITADO");
    }
}
