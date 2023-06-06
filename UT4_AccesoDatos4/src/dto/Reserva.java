/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Kid A
 */
public class Reserva {

    private int id;
    private int idVuelo;
    private int idPasajero;
    private String asiento;

    /**
     * Crea una instancia de Reserva con el ID, ID del vuelo, ID del pasajero y
     * número de asiento proporcionados.
     *
     * @param id El ID de la reserva.
     * @param idVuelo El ID del vuelo asociado a la reserva.
     * @param idPasajero El ID del pasajero asociado a la reserva.
     * @param asiento El número de asiento reservado.
     */
    public Reserva(int id, int idVuelo, int idPasajero, String asiento) {
        this.id = id;
        this.idVuelo = idVuelo;
        this.idPasajero = idPasajero;
        this.asiento = asiento;
    }

    /**
     * Crea una instancia de Reserva con el ID del vuelo, ID del pasajero y
     * número de asiento proporcionados.
     *
     * @param idVuelo El ID del vuelo asociado a la reserva.
     * @param idPasajero El ID del pasajero asociado a la reserva.
     * @param asiento El número de asiento reservado.
     */
    public Reserva(int idVuelo, int idPasajero, String asiento) {
        this.idVuelo = idVuelo;
        this.idPasajero = idPasajero;
        this.asiento = asiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", idVuelo=" + idVuelo + ", idPasajero=" + idPasajero + ", asiento=" + asiento + '}';
    }

}
