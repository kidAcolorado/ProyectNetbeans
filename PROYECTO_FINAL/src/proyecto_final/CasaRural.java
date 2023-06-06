/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Kid A
 */
public class CasaRural extends Alojamiento {

    private boolean tieneJardin;
    private boolean tieneBarbacoa;
    private boolean tienePiscina;
    private int numHabitaciones;

    /**
     * Crea una nueva instancia de CasaRural con los datos proporcionados.
     *
     * @param nombre El nombre de la casa rural.
     * @param ciudad La ciudad donde se encuentra la casa rural.
     * @param precio El precio por día de la casa rural.
     * @param tieneJardin Indica si la casa rural tiene jardín.
     * @param tieneBarbacoa Indica si la casa rural tiene barbacoa.
     * @param tienePiscina Indica si la casa rural tiene piscina.
     * @param numHabitaciones El número de habitaciones de la casa rural.
     */
    public CasaRural(String nombre, String ciudad, double precio, boolean tieneJardin, boolean tieneBarbacoa, boolean tienePiscina, int numHabitaciones) {
        super(nombre, ciudad, precio);
        this.tieneJardin = tieneJardin;
        this.tieneBarbacoa = tieneBarbacoa;
        this.tienePiscina = tienePiscina;
        this.numHabitaciones = numHabitaciones;
    }

    public boolean isTieneJardin() {
        return tieneJardin;
    }

    public void setTieneJardin(boolean tieneJardin) {
        this.tieneJardin = tieneJardin;
    }

    public boolean isTieneBarbacoa() {
        return tieneBarbacoa;
    }

    public void setTieneBarbacoa(boolean tieneBarbacoa) {
        this.tieneBarbacoa = tieneBarbacoa;
    }

    public boolean isTienePiscina() {
        return tienePiscina;
    }

    public void setTienePiscina(boolean tienePiscina) {
        this.tienePiscina = tienePiscina;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    /**
     * Calcula el costo total de la reserva de la casa rural para el número de
     * días especificado.
     *
     * @param numDias El número de días de la reserva.
     * @return El costo total de la reserva.
     */
    @Override
    public double reservar(int numDias) {
        return numDias * precio;
    }
}
