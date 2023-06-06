/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Kid A
 */
public class Reserva {

    private Usuario usuario;
    private Alojamiento alojamiento;
    private int numDias;

    /**
     * Crea una instancia de Reserva con los datos proporcionados.
     *
     * @param usuario El usuario que realiza la reserva.
     * @param alojamiento El alojamiento reservado.
     * @param numDias El número de días de la reserva.
     */
    public Reserva(Usuario usuario, Alojamiento alojamiento, int numDias) {
        this.usuario = usuario;
        this.alojamiento = alojamiento;
        this.numDias = numDias;
    }

    public double getTotal() {
        return alojamiento.reservar(numDias);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public int getNumDias() {
        return numDias;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }
}
