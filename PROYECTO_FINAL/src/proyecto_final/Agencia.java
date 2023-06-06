/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_final;

import java.util.ArrayList;

/**
 *
 * @author Kid A
 */
public class Agencia {

    private int codigo;
    private String ciudad;
    private ArrayList<Alojamiento> alojamientos;
    private ArrayList<Reserva> reservas;

    /**
     * Crea una instancia de la clase Agencia con el código y la ciudad
     * especificados.
     *
     * @param codigo el código de la agencia
     * @param ciudad la ciudad de la agencia
     */
    public Agencia(int codigo, String ciudad) {
        this.codigo = codigo;
        this.ciudad = ciudad;
        this.alojamientos = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(ArrayList<Alojamiento> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Busca los alojamientos disponibles en una determinada ciudad.
     *
     * @param ciudad la ciudad en la que se desea buscar alojamientos
     * @return una lista de alojamientos encontrados en la ciudad especificada
     */
    public ArrayList<Alojamiento> buscarAlojamiento(String ciudad) {
        ArrayList<Alojamiento> alojamientosEnCiudad = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad)) {
                alojamientosEnCiudad.add(alojamiento);
            }
        }
        return alojamientosEnCiudad;
    }

    /**
     * Busca los alojamientos disponibles en una determinada ciudad con un
     * precio inferior al especificado.
     *
     * @param ciudad la ciudad en la que se desea buscar alojamientos
     * @param precio el precio máximo que deben tener los alojamientos
     * encontrados
     * @return una lista de alojamientos encontrados en la ciudad especificada
     * con un precio inferior al indicado
     */
    public ArrayList<Alojamiento> buscarAlojamiento(String ciudad, double precio) {
        ArrayList<Alojamiento> alojamientosEnCiudadConPrecio = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getCiudad().equalsIgnoreCase(ciudad) && alojamiento.getPrecio() < precio) {
                alojamientosEnCiudadConPrecio.add(alojamiento);
            }
        }
        return alojamientosEnCiudadConPrecio;
    }

    /**
     * Busca las reservas que coinciden con el nombre del alojamiento y la
     * ciudad del alojamiento especificados.
     *
     * @param nombreAlojamiento el nombre del alojamiento para buscar reservas
     * @param ciudadAlojamiento la ciudad del alojamiento para buscar reservas
     * @return una lista de reservas que coinciden con el nombre del alojamiento
     * y la ciudad del alojamiento especificados
     */
    public ArrayList<Reserva> buscarReserva(String nombreAlojamiento, String ciudadAlojamiento) {
        ArrayList<Reserva> reservasConNombreAlojamientoYNombreCiudad = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getAlojamiento().getNombre().equalsIgnoreCase(nombreAlojamiento)
                    && r.getAlojamiento().getCiudad().equalsIgnoreCase(ciudadAlojamiento)) {
                reservasConNombreAlojamientoYNombreCiudad.add(r);
            }
        }
        return reservasConNombreAlojamientoYNombreCiudad;
    }

    /**
     * Busca las reservas que coinciden con el nombre del usuario especificado.
     *
     * @param nombre el nombre del usuario para buscar reservas
     * @return una lista de reservas que coinciden con el nombre del usuario
     * especificado
     */
    public ArrayList<Reserva> buscarReserva(String nombre) {
        ArrayList<Reserva> reservasConNombreUsuario = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getUsuario().getNombre().equalsIgnoreCase(nombre)) {
                reservasConNombreUsuario.add(r);
            }
        }
        return reservasConNombreUsuario;
    }

    /**
     * Realiza una reserva para un usuario en un alojamiento por un número de
     * días especificado.
     *
     * @param usuario el usuario que realiza la reserva
     * @param alojamiento el alojamiento en el que se realiza la reserva
     * @param numDias el número de días de la reserva
     * @return la reserva realizada
     */
    public Reserva hacerReserva(Usuario usuario, Alojamiento alojamiento, int numDias) {
        Reserva reserva = new Reserva(usuario, alojamiento, numDias);
        reservas.add(reserva);
        return reserva;
    }

}
