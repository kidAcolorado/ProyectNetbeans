/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Kid A
 */
public class Apartamento extends Alojamiento implements ServicioHabitaciones {

    private boolean tieneCocina;
    private boolean tieneSofaCama;
    private int numMaximoUsuarios;
    private boolean servicioHabitaciones = false;

    /**
     * Constructor de la clase Apartamento.
     *
     * @param nombre El nombre del apartamento.
     * @param ciudad La ciudad donde se encuentra el apartamento.
     * @param precio El precio del apartamento.
     * @param tieneCocina Indica si el apartamento tiene cocina.
     * @param tieneSofaCama Indica si el apartamento tiene sofá cama.
     * @param numMaximoUsuarios El número máximo de usuarios permitidos en el
     * apartamento.
     */
    public Apartamento(String nombre, String ciudad, double precio, boolean tieneCocina, boolean tieneSofaCama, int numMaximoUsuarios) {
        super(nombre, ciudad, precio);
        this.tieneCocina = tieneCocina;
        this.tieneSofaCama = tieneSofaCama;
        this.numMaximoUsuarios = numMaximoUsuarios;
    }

    public boolean isTieneCocina() {
        return tieneCocina;
    }

    public void setTieneCocina(boolean tieneCocina) {
        this.tieneCocina = tieneCocina;
    }

    public boolean isTieneSofaCama() {
        return tieneSofaCama;
    }

    public void setTieneSofaCama(boolean tieneSofaCama) {
        this.tieneSofaCama = tieneSofaCama;
    }

    public int getNumMaximoUsuarios() {
        return numMaximoUsuarios;
    }

    public void setNumMaximoUsuarios(int numMaximoUsuarios) {
        this.numMaximoUsuarios = numMaximoUsuarios;
    }

    public boolean isServicioHabitaciones() {
        return servicioHabitaciones;
    }

    public void setServicioHabitaciones(boolean servicioHabitaciones) {
        this.servicioHabitaciones = servicioHabitaciones;
    }

    /**
     * Calcula el costo total de una reserva en función del número de días
     * especificado.
     *
     * @param numDias Número de días de la reserva.
     * @return El costo total de la reserva.
     */
    @Override
    public double reservar(int numDias) {
        return numDias * precio;
    }

    /**
     * Solicita el servicio de habitaciones para el alojamiento. Establece el
     * estado del servicio de habitaciones como activo y muestra un mensaje
     * indicando que se ha solicitado el servicio.
     */
    @Override
    public void solicitarServicioHabitaciones() {
        servicioHabitaciones = true;
        System.out.println("SERVICIO DE HABITACIONES SOLICITADO");
    }
}
