/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Kid A
 */
public class Pasajero {

    private int id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;

    /**
     * Crea una instancia de Pasajero con el nombre, apellido y fecha de
     * nacimiento proporcionados.
     * @param id
     * @param nombre
     * @param apellido
     * @param fechaNacimiento 
     */
    public Pasajero(int id, String nombre, String apellido, String fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

    /**
     * Crea una instancia de Pasajero con el nombre, apellido y fecha de
     * nacimiento proporcionados.
     *
     * 
     * @param nombre El nombre del pasajero.
     * @param apellido El apellido del pasajero.
     * @param fechaNacimiento La fecha de nacimiento del pasajero.
     */
    public Pasajero(String nombre, String apellido, String fechaNacimiento) {
       
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Pasajero{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento + '}';
    }

}
