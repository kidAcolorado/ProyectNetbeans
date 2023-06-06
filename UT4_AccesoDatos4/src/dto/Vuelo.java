/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Kid A
 */
public class Vuelo {

    private int id;
    private String origen;
    private String destino;
    private String fecha;

    /**
     * Crea una instancia de Vuelo con el ID, origen, destino y fecha
     * proporcionados.
     *
     * @param id El ID del vuelo.
     * @param origen El origen del vuelo.
     * @param destino El destino del vuelo.
     * @param fecha La fecha del vuelo.
     */
    public Vuelo(int id, String origen, String destino, String fecha) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Vuelo{" + "id=" + id + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha + '}';
    }

}
