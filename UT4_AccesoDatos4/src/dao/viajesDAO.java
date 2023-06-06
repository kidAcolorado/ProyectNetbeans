/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;


import java.util.ArrayList;
import dto.Vuelo;
import dto.Pasajero;
import dto.Reserva;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Kid A
 */
public interface viajesDAO {

    public Connection getConn();

    //Métodos para la conexión:
    public boolean loadDriver();

    public boolean connect();

    public boolean close();

    //Métodos CRUD de Vuelo:
    public boolean insertVuelo(Vuelo vuelo);

    public ArrayList<Vuelo> getVuelos();

    public boolean updateVuelo(Vuelo vuelo);

    public boolean deleteVuelo(int id);

    //Métodos CRUD de Pasajero:
    public boolean insertPasajero(Pasajero pasajero);

    public ArrayList<Pasajero> getPasajeros();

    public boolean updatePasajero(Pasajero pasajero);

    public boolean deletePasajero(int id);

    //Métodos CRUD de Reserva:
    public boolean insertReserva(Reserva reserva);

    public ArrayList<Reserva> getReservas();

    public boolean updateReserva(Reserva reserva);

    public boolean deleteReserva(int id);

    //Métodos para otras consultas:
    public void consultaVuelosDisponibles(String fecha, String origen, String destino);
    
    public void consultaInformacionVuelo(int vueloId);
    
    public void consultaPasajerosReservasVuelo(int vueloId);
    
    public void cancelarReserva(int reservaId);
    
    public void checkAsientoYRealizarReserva(int vueloId, int pasajeroId, String asiento );

}
