/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

/**
 *
 * @author Kid A
 */
public abstract class Alojamiento {

    protected String nombre;
    protected String ciudad;
    protected double precio;

    /**
     * Constructor de la clase Alojamiento.
     *
     * @param nombre El nombre del alojamiento.
     * @param ciudad La ciudad donde se encuentra el alojamiento.
     * @param precio El precio del alojamiento.
     */
    public Alojamiento(String nombre, String ciudad, double precio) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public abstract double reservar(int numDias);
}
